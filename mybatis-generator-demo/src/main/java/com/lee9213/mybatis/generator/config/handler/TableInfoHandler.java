package com.lee9213.mybatis.generator.config.handler;

import com.google.common.collect.Lists;
import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.config.properties.DataSourceProperties;
import com.lee9213.mybatis.generator.config.properties.GlobalProperties;
import com.lee9213.mybatis.generator.config.properties.StrategyProperties;
import com.lee9213.mybatis.generator.config.sql.query.IDbQuery;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.JDBCUtil;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>表信息解析</p>
 *
 * @author libo
 * @date 2018/10/18 11:16
 */
public class TableInfoHandler implements Handler {


    @Override
    public void handler(Configuration configuration) {
        StrategyProperties strategyProperties = configuration.getStrategyProperties();
        DataSourceProperties dataSourceConfiguration = configuration.getDataSourceProperties();
        ArrayList<String> includeTableList = Lists.newArrayList(strategyProperties.getIncludeTables());
        //所有的表信息
        List<TableInfo> tableList = new ArrayList<>();
        IDbQuery dbQuery = dataSourceConfiguration.getDbQuery();
        String tablesSql = dbQuery.tablesSql(strategyProperties.getIncludeTables(),strategyProperties.getIncludeTablePrefixs(),strategyProperties.getExcludeTables());
        try (Connection connection = JDBCUtil.getConnection(dataSourceConfiguration.getUrl(),dataSourceConfiguration.getUsername(),dataSourceConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(tablesSql);
             ResultSet results = preparedStatement.executeQuery()) {
            TableInfo tableInfo;
            while (results.next()) {
                tableInfo = resultToTableInfo(results,configuration);
                if (tableInfo != null) {
                    tableList.add(tableInfo);
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
        configuration.setTableInfoList(tableList);
    }

    public TableInfo resultToTableInfo(ResultSet results,Configuration configuration) throws SQLException {
        StrategyProperties strategyProperties = configuration.getStrategyProperties();
        DataSourceProperties dataSourceConfiguration = configuration.getDataSourceProperties();
        GlobalProperties globalProperties = configuration.getGlobalProperties();
        IDbQuery dbQuery = dataSourceConfiguration.getDbQuery();
        String tableName = results.getString(dbQuery.tableName());
        if (StringUtils.isNotEmpty(tableName)) {
            String tableComment = results.getString(dbQuery.tableComment());
            if ("VIEW".equals(tableComment)) {
                // 跳过视图
                return null;
            }
            TableInfo tableInfo = new TableInfo();
            tableInfo.setName(tableName);
            tableInfo.setComment(tableComment);

            String entityName = StringUtils.capitalFirst(StringUtils.processName(tableInfo.getName(),
                    strategyProperties.getUnderlineToCamelTableName(), strategyProperties.getTablePrefix()));

            if (StringUtils.isNotEmpty(globalProperties.getEntityName())) {
                tableInfo.setEntityName(String.format(globalProperties.getEntityName(), entityName));
            } else {
                tableInfo.setEntityName(entityName);
            }
            if (StringUtils.isNotEmpty(globalProperties.getVoName())) {
                tableInfo.setVoName(String.format(globalProperties.getVoName(), entityName));
            } else {
                tableInfo.setVoName(entityName + Constant.VO);
            }
            if (StringUtils.isNotEmpty(globalProperties.getMapperName())) {
                tableInfo.setMapperName(String.format(globalProperties.getMapperName(), entityName));
            } else {
                tableInfo.setMapperName(entityName + Constant.MAPPER);
            }
            if (StringUtils.isNotEmpty(globalProperties.getMapperXmlName())) {
                tableInfo.setMapperXmlName(String.format(globalProperties.getMapperXmlName(), entityName));
            } else {
                tableInfo.setMapperXmlName(entityName + Constant.MAPPER_XML);
            }
            if (StringUtils.isNotEmpty(globalProperties.getExtendMapperXmlName())) {
                tableInfo.setExtendMapperXmlName(String.format(globalProperties.getExtendMapperXmlName(), entityName));
            } else {
                tableInfo.setExtendMapperXmlName(entityName + Constant.EXTEND_MAPPER_XML);
            }

            if (StringUtils.isNotEmpty(globalProperties.getServiceName())) {
                tableInfo.setServiceName(String.format(globalProperties.getServiceName(), entityName));
            } else {
                tableInfo.setServiceName(entityName + Constant.SERVICE);
            }
            if (StringUtils.isNotEmpty(globalProperties.getServiceImplName())) {
                tableInfo.setServiceImplName(String.format(globalProperties.getServiceImplName(), entityName));
            } else {
                tableInfo.setServiceImplName(entityName + Constant.SERVICE_IMPL);
            }
            if (StringUtils.isNotEmpty(globalProperties.getControllerName())) {
                tableInfo.setControllerName(String.format(globalProperties.getControllerName(), entityName));
            } else {
                tableInfo.setControllerName(entityName + Constant.CONTROLLER);
            }

            return tableInfo;
        } else {
            System.err.println("当前数据库为空！！！");
            return null;
        }
    }
}
