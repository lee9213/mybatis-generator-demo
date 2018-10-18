package com.lee9213.mybatis.generator.config.parser;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableField;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.config.properties.DataSourceProperties;
import com.lee9213.mybatis.generator.config.properties.GlobalProperties;
import com.lee9213.mybatis.generator.config.properties.StrategyProperties;
import com.lee9213.mybatis.generator.config.sql.query.IDbQuery;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.NamingStrategy;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author libo
 * @date 2018/10/18 11:16
 */
public class TableInfoParser implements Parser {
    @Override
    public void parser(Configuration configuration) {
        StrategyProperties strategyProperties = configuration.getStrategyProperties();
        DataSourceProperties dataSourceConfiguration = configuration.getDataSourceProperties();
        ArrayList<String> includeTableList = Lists.newArrayList(strategyProperties.getIncludeTables());

        //所有的表信息
        List<TableInfo> tableList = new ArrayList<>();
        IDbQuery dbQuery = dataSourceConfiguration.getDbQuery();
        String tablesSql = dbQuery.tablesSql(strategyProperties);
        try (Connection connection = dataSourceConfiguration.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement(tablesSql);
             ResultSet results = preparedStatement.executeQuery()) {
            TableInfo tableInfo;
            while (results.next()) {
                String tableName = results.getString(dbQuery.tableName());
                if (StringUtils.isNotEmpty(tableName)) {
                    String tableComment = results.getString(dbQuery.tableComment());
                    if (strategyProperties.isSkipView() && "VIEW".equals(tableComment)) {
                        // 跳过视图
                        continue;
                    }
                    tableInfo = new TableInfo();
                    tableInfo.setName(tableName);
                    tableInfo.setComment(tableComment);
                    setTableFieldList(connection,dbQuery,configuration,tableInfo);
                    tableList.add(tableInfo);
                } else {
                    System.err.println("当前数据库为空！！！");
                }
            }

            // 将已经存在的表移除，获取配置中数据库不存在的表
            for (TableInfo tabInfo : tableList) {
                includeTableList.remove(tabInfo.getName());
            }
            if (includeTableList.size() > 0) {
                System.err.println("表 " + includeTableList + " 在数据库中不存在！！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<TableInfo> tableInfoList = processTable(tableList, configuration);
        configuration.setTableInfoList(tableInfoList);
    }

    public void setTableFieldList(Connection connection, IDbQuery dbQuery, Configuration configuration, TableInfo tableInfo) throws SQLException {
        DataSourceProperties dataSourceProperties = configuration.getDataSourceProperties();
        StrategyProperties strategyProperties = configuration.getStrategyProperties();
        GlobalProperties globalProperties = configuration.getGlobalProperties();

        String fieldSql = String.format(dbQuery.tableFieldsSql(), dataSourceProperties.getSchemaName(), tableInfo.getName());
        PreparedStatement fieldPreparedStatement = connection.prepareStatement(fieldSql);
        ResultSet results = fieldPreparedStatement.executeQuery();
        try {
            List<TableField> fieldList = Lists.newArrayList();
            List<TableField> commonFieldList = Lists.newArrayList();
            while (results.next()) {
                TableField field = new TableField();
                String key = results.getString(dbQuery.fieldKey());
                // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
                boolean isId = !Strings.isNullOrEmpty(key) && "PRI".equals(key.toUpperCase());

                // 处理ID
                if (isId) {
                    field.setKeyFlag(true);
                    if (dbQuery.isKeyIdentity(results)) {
                        field.setKeyIdentityFlag(true);
                    }
                } else {
                    field.setKeyFlag(false);
                }
                // 自定义字段查询
                String[] fcs = dbQuery.fieldCustom();
                if (null != fcs) {
                    Map<String, Object> customMap = new HashMap<>();
                    for (String fc : fcs) {
                        customMap.put(fc, results.getObject(fc));
                    }
                    field.setCustomMap(customMap);
                }
                // 处理其它信息
                field.setName(results.getString(dbQuery.fieldName()));
                field.setType(results.getString(dbQuery.fieldType()));
                field.setPropertyName(strategyProperties, processName(field.getName(), strategyProperties.getColumnNaming(),strategyProperties.getFieldPrefix()));
                field.setColumnType(dataSourceProperties.getTypeConvert().processTypeConvert(globalProperties, field.getType()));
                field.setComment(results.getString(dbQuery.fieldComment()));
                if (strategyProperties.includeSuperEntityColumns(field.getName())) {
                    // 跳过公共字段
                    commonFieldList.add(field);
                    continue;
                }

                fieldList.add(field);
            }
            tableInfo.setFields(fieldList);
            tableInfo.setCommonFields(commonFieldList);
        } catch (SQLException e) {
            System.err.println("SQL Exception：" + e.getMessage());
        }
    }

    /**
     * <p>
     * 处理表对应的类名称
     * </P>
     *
     * @param tableList 表名称
     * @return 补充完整信息后的表
     */
    private List<TableInfo> processTable(List<TableInfo> tableList, Configuration configuration) {
        NamingStrategy strategy = configuration.getStrategyProperties().getNaming();
        StrategyProperties strategyConfiguration = configuration.getStrategyProperties();
        GlobalProperties globalConfiguration = configuration.getGlobalProperties();
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
            checkImportPackages(tableInfo, configuration);
        }
        return tableList;
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
     * 检测导入包
     * </p>
     *
     * @param tableInfo
     */
    private void checkImportPackages(TableInfo tableInfo, Configuration configuration) {
        StrategyProperties strategyConfiguration = configuration.getStrategyProperties();
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
}
