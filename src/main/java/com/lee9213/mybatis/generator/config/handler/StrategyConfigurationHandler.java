package com.lee9213.mybatis.generator.config.handler;

import com.lee9213.mybatis.generator.config.DataSourceConfiguration;
import com.lee9213.mybatis.generator.config.GlobalConfiguration;
import com.lee9213.mybatis.generator.config.StrategyConfiguration;
import com.lee9213.mybatis.generator.config.builder.ConfigurationBuilder;
import com.lee9213.mybatis.generator.config.po.TableInfo;
import com.lee9213.mybatis.generator.query.IDbQuery;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.NamingStrategy;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author libo
 * @version 1.0
 * @date 2018/10/15 16:12
 */
public class StrategyConfigurationHandler implements ConfigurationHandler {
    @Override
    public void handler(ConfigurationBuilder configBuilder) {
        List<TableInfo> tableInfoList = getTableInfo(configBuilder);
        configBuilder.setTableInfoList(tableInfoList);
    }

    /**
     * <p>
     * 获取所有的数据库表信息
     * </p>
     */
    private List<TableInfo> getTableInfo(ConfigurationBuilder configBuilder) {
        StrategyConfiguration strategyConfiguration = configBuilder.getStrategyConfiguration();
        DataSourceConfiguration dataSourceConfiguration = configBuilder.getDataSourceConfiguration();
        boolean isInclude = (null != strategyConfiguration.getInclude() && strategyConfiguration.getInclude().length > 0);
        boolean isExclude = (null != strategyConfiguration.getExclude() && strategyConfiguration.getExclude().length > 0);
        if (isInclude && isExclude) {
            throw new RuntimeException("<strategy> 标签中 <include> 与 <exclude> 只能配置一项！");
        }
        //所有的表信息
        List<TableInfo> tableList = new ArrayList<>();

        //需要反向生成或排除的表信息
        List<TableInfo> includeTableList = new ArrayList<>();
        List<TableInfo> excludeTableList = new ArrayList<>();

        //不存在的表名
        Set<String> notExistTables = new HashSet<>();
        IDbQuery dbQuery = dataSourceConfiguration.getDbQuery();
        Connection connection = dataSourceConfiguration.getConn();
        PreparedStatement preparedStatement = null;
        try {
            String tablesSql = dbQuery.tablesSql();
            preparedStatement = connection.prepareStatement(tablesSql);
            ResultSet results = preparedStatement.executeQuery();
            TableInfo tableInfo;
            while (results.next()) {
                String tableName = results.getString(dbQuery.tableName());
                if (StringUtils.isNotEmpty(tableName)) {
                    String tableComment = results.getString(dbQuery.tableComment());
                    if (strategyConfiguration.isSkipView() && "VIEW".equals(tableComment)) {
                        // 跳过视图
                        continue;
                    }
                    tableInfo = new TableInfo();
                    tableInfo.setName(tableName);
                    tableInfo.setComment(tableComment);
                    if (isInclude) {
                        for (String includeTable : strategyConfiguration.getInclude()) {
                            // 忽略大小写等于 或 正则 true
                            if (tableNameMatches(includeTable, tableName)) {
                                includeTableList.add(tableInfo);
                            } else {
                                notExistTables.add(includeTable);
                            }
                        }
                    } else if (isExclude) {
                        for (String excludeTable : strategyConfiguration.getExclude()) {
                            // 忽略大小写等于 或 正则 true
                            if (tableNameMatches(excludeTable, tableName)) {
                                excludeTableList.add(tableInfo);
                            } else {
                                notExistTables.add(excludeTable);
                            }
                        }
                    }
                    tableList.add(tableInfo);
                } else {
                    System.err.println("当前数据库为空！！！");
                }
            }

            // 将已经存在的表移除，获取配置中数据库不存在的表
            for (TableInfo tabInfo : tableList) {
                notExistTables.remove(tabInfo.getName());
            }
            if (notExistTables.size() > 0) {
                System.err.println("表 " + notExistTables + " 在数据库中不存在！！！");
            }

            // 需要反向生成的表信息
            if (isExclude) {
                tableList.removeAll(excludeTableList);
                includeTableList = tableList;
            }
            if (!isInclude && !isExclude) {
                includeTableList = tableList;
            }
            /**
             * 性能优化，只处理需执行表字段 github issues/219
             */
//            includeTableList.forEach(ti -> convertTableFields(ti, config.getColumnNaming()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return processTable(includeTableList, configBuilder);
    }


    /**
     * <p>
     * 表名匹配
     * </p>
     *
     * @param setTableName 设置表名
     * @param dbTableName  数据库表单
     * @return
     */
    private boolean tableNameMatches(String setTableName, String dbTableName) {
        return setTableName.equals(dbTableName)
                || StringUtils.matches(setTableName, dbTableName);
    }

    /**
     * <p>
     * 处理表/字段名称
     * </p>
     *
     * @param name
     * @param strategy
     * @param prefix
     * @return 根据策略返回处理后的名称
     */
    private String processName(String name, NamingStrategy strategy, String[] prefix) {
        boolean removePrefix = false;
        if (prefix != null && prefix.length >= 1) {
            removePrefix = true;
        }
        String propertyName;
        if (removePrefix) {
            if (strategy == NamingStrategy.underline_to_camel) {
                // 删除前缀、下划线转驼峰
                propertyName = NamingStrategy.removePrefixAndCamel(name, prefix);
            } else {
                // 删除前缀
                propertyName = NamingStrategy.removePrefix(name, prefix);
            }
        } else if (strategy == NamingStrategy.underline_to_camel) {
            // 下划线转驼峰
            propertyName = NamingStrategy.underlineToCamel(name);
        } else {
            // 不处理
            propertyName = name;
        }
        return propertyName;
    }


    /**
     * <p>
     * 处理表对应的类名称
     * </P>
     *
     * @param tableList            表名称
     * @param configurationBuilder 所有配置
     * @return 补充完整信息后的表
     */
    private List<TableInfo> processTable(List<TableInfo> tableList, ConfigurationBuilder configurationBuilder) {
        NamingStrategy strategy = configurationBuilder.getStrategyConfiguration().getNaming();
        StrategyConfiguration strategyConfiguration = configurationBuilder.getStrategyConfiguration();
        GlobalConfiguration globalConfiguration = configurationBuilder.getGlobalConfiguration();
        String[] tablePrefix = strategyConfiguration.getTablePrefix();
        for (TableInfo tableInfo : tableList) {
            String entityName = NamingStrategy.capitalFirst(processName(tableInfo.getName(), strategy, tablePrefix));
            if (StringUtils.isNotEmpty(globalConfiguration.getEntityName())) {
                tableInfo.setConvert(true);
                tableInfo.setEntityName(String.format(globalConfiguration.getEntityName(), entityName));
            } else {
                tableInfo.setEntityName(strategyConfiguration, entityName);
            }
            if (StringUtils.isNotEmpty(globalConfiguration.getMapperName())) {
                tableInfo.setMapperName(String.format(globalConfiguration.getMapperName(), entityName));
            } else {
                tableInfo.setMapperName(entityName + Constant.MAPPER);
            }
            if (StringUtils.isNotEmpty(globalConfiguration.getXmlName())) {
                tableInfo.setXmlName(String.format(globalConfiguration.getXmlName(), entityName));
            } else {
                tableInfo.setXmlName(entityName + Constant.MAPPER);
            }
            if (StringUtils.isNotEmpty(globalConfiguration.getServiceName())) {
                tableInfo.setServiceName(String.format(globalConfiguration.getServiceName(), entityName));
            } else {
                tableInfo.setServiceName("I" + entityName + Constant.SERVICE);
            }
            if (StringUtils.isNotEmpty(globalConfiguration.getServiceImplName())) {
                tableInfo.setServiceImplName(String.format(globalConfiguration.getServiceImplName(), entityName));
            } else {
                tableInfo.setServiceImplName(entityName + Constant.SERVICE_IMPL);
            }
            if (StringUtils.isNotEmpty(globalConfiguration.getControllerName())) {
                tableInfo.setControllerName(String.format(globalConfiguration.getControllerName(), entityName));
            } else {
                tableInfo.setControllerName(entityName + Constant.CONTROLLER);
            }
            // 检测导入包
            checkImportPackages(tableInfo, configurationBuilder);
        }
        return tableList;
    }

    /**
     * <p>
     * 检测导入包
     * </p>
     *
     * @param tableInfo
     */
    private void checkImportPackages(TableInfo tableInfo, ConfigurationBuilder configurationBuilder) {
        StrategyConfiguration strategyConfiguration = configurationBuilder.getStrategyConfiguration();
        if (StringUtils.isNotEmpty(strategyConfiguration.getSuperEntityClass())) {
            // 自定义父类
            tableInfo.getImportPackages().add(strategyConfiguration.getSuperEntityClass());
        } /*else if (globalConfig.isActiveRecord()) {
            // 无父类开启 AR 模式
            tableInfo.getImportPackages().add(com.baomidou.mybatisplus.extension.activerecord.Model.class.getCanonicalName());
        }
        if (null != globalConfig.getIdType()) {
            // 指定需要 IdType 场景
            tableInfo.getImportPackages().add(com.baomidou.mybatisplus.annotation.IdType.class.getCanonicalName());
            tableInfo.getImportPackages().add(com.baomidou.mybatisplus.annotation.TableId.class.getCanonicalName());
        }
        if (StringUtils.isNotEmpty(strategyConfig.getVersionFieldName())) {
            tableInfo.getFields().forEach(f -> {
                if (strategyConfig.getVersionFieldName().equals(f.getName())) {
                    tableInfo.getImportPackages().add(com.baomidou.mybatisplus.annotation.Version.class.getCanonicalName());
                }
            });
        }*/
    }


//    /**
//     * <p>
//     * 开放表信息、预留子类重写
//     * </p>
//     *
//     * @param config 配置信息
//     * @return
//     */
//    protected List<TableInfo> getAllTableInfoList(ConfigurationBuilder config) {
//        return config.getTableInfoList();
//    }
//
//    /**
//     * <p>
//     * 预处理配置
//     * </p>
//     *
//     * @param config 总配置信息
//     * @return 解析数据结果集
//     */
//    protected ConfigurationBuilder pretreatmentConfigBuilder(ConfigurationBuilder config) {
//        /**
//         * 表信息列表
//         */
//        List<TableInfo> tableList = this.getAllTableInfoList(config);
//
//        for (TableInfo tableInfo : tableList) {
////            /* ---------- 添加导入包 ---------- */
////            if (config.getGlobalConfig().isActiveRecord()) {
////                // 开启 ActiveRecord 模式
////                tableInfo.setImportPackages(Model.class.getCanonicalName());
////            }
//            if (tableInfo.isConvert()) {
//                // 表注解
//                tableInfo.setImportPackages(TableName.class.getCanonicalName());
//            }
////            if (config.getStrategyConfig().getLogicDeleteFieldName() != null && tableInfo.isLogicDelete(config.getStrategyConfig().getLogicDeleteFieldName())) {
////                // 逻辑删除注解
////                tableInfo.setImportPackages(TableLogic.class.getCanonicalName());
////            }
////            if (StringUtils.isNotEmpty(config.getStrategyConfig().getVersionFieldName())) {
////                // 乐观锁注解
////                tableInfo.setImportPackages(Version.class.getCanonicalName());
////            }
////            if (StringUtils.isNotEmpty(config.getSuperEntityClass())) {
////                // 父实体
////                tableInfo.setImportPackages(config.getSuperEntityClass());
////            } else {
////                tableInfo.setImportPackages(Serializable.class.getCanonicalName());
////            }
////            // Boolean类型is前缀处理
////            if (config.getStrategyConfig().isEntityBooleanColumnRemoveIsPrefix()) {
////                tableInfo.getFields().stream().filter(field -> "boolean".equalsIgnoreCase(field.getPropertyType()))
////                        .filter(field -> field.getPropertyName().startsWith("is"))
////                        .forEach(field -> field.setPropertyName(config.getStrategyConfig(),
////                                StringUtils.removePrefixAfterPrefixToLower(field.getPropertyName(), 2)));
////            }
//        }
//        return config.setTableInfoList(tableList);
//    }
}