package com.lee9213.mybatis.generator.config.parser;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableField;
import com.lee9213.mybatis.generator.config.properties.DataSourceProperties;
import com.lee9213.mybatis.generator.config.properties.GlobalProperties;
import com.lee9213.mybatis.generator.config.properties.StrategyProperties;
import com.lee9213.mybatis.generator.config.sql.query.IDbQuery;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-18 21:11
 */
public class TableFieldParser implements Parser {


    @Override
    public void parser(Configuration configuration) {
        DataSourceProperties dataSourceProperties = configuration.getDataSourceProperties();
        StrategyProperties strategyProperties = configuration.getStrategyProperties();
        GlobalProperties globalProperties = configuration.getGlobalProperties();
        IDbQuery dbQuery = dataSourceProperties.getDbQuery();
        configuration.getTableInfoList().forEach(tableInfo -> {
            String fieldSql = String.format(dbQuery.tableFieldsSql(), tableInfo.getName());
            try (Connection connection = dataSourceProperties.getConn();
                 PreparedStatement preparedStatement = connection.prepareStatement(fieldSql);
                 ResultSet results = preparedStatement.executeQuery()) {
                List<TableField> fieldList = Lists.newArrayList();
                List<TableField> commonFieldList = Lists.newArrayList();
                TableField field;
                while (results.next()) {
                    field = new TableField();
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
                    String type = results.getString(dbQuery.fieldType());
                    if (type.indexOf("(") != -1) {
                        type = type.substring(0, type.indexOf("("));
                    }
                    type = type.toUpperCase();
                    field.setType(type.equalsIgnoreCase("int") ? "INTEGER" : type);
                    field.setPropertyName(strategyProperties, StringUtils.processName(field.getName(), strategyProperties.getUnderlineToCamelColumnNames(), strategyProperties.getFieldPrefix()));
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
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
