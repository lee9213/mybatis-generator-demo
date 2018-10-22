package com.lee9213.mybatis.generator.config.parser;

import com.google.common.collect.Lists;
import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableField;
import com.lee9213.mybatis.generator.config.properties.DataSourceProperties;
import com.lee9213.mybatis.generator.config.properties.GlobalProperties;
import com.lee9213.mybatis.generator.config.properties.StrategyProperties;
import com.lee9213.mybatis.generator.config.sql.query.IDbQuery;
import com.lee9213.mybatis.generator.util.JDBCUtil;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * <p>表字段解析</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-18 21:11
 */
public class TableFieldParser implements Parser {


    private Configuration configuration;
    public TableFieldParser(Configuration configuration) {
        this.configuration = configuration;
    }
    @Override
    public void parser() {
        DataSourceProperties dataSourceProperties = configuration.getDataSourceProperties();
        StrategyProperties strategyProperties = configuration.getStrategyProperties();
        GlobalProperties globalProperties = configuration.getGlobalProperties();
        IDbQuery dbQuery = dataSourceProperties.getDbQuery();
        configuration.getTableInfoList().forEach(tableInfo -> {
            String fieldSql = String.format(dbQuery.tableFieldsSql(), tableInfo.getName());
            try (Connection connection = JDBCUtil.getConnection(dataSourceProperties.getUrl(),dataSourceProperties.getUsername(),dataSourceProperties.getPassword());
                 PreparedStatement preparedStatement = connection.prepareStatement(fieldSql);
                 ResultSet results = preparedStatement.executeQuery()) {
                List<TableField> fieldList = Lists.newArrayList();
                List<TableField> commonFieldList = Lists.newArrayList();
                TableField field;
                while (results.next()) {
                    field = new TableField();
                    String key = results.getString(dbQuery.fieldKey());
                    boolean isId = StringUtils.isNotEmpty(key) && "PRI".equals(key.toUpperCase());

                    // 处理ID
                    if (isId) {
                        field.setKeyFlag(true);
                        if (dbQuery.isKeyIdentity(results)) {
                            field.setKeyIdentityFlag(true);
                        }
                    } else {
                        field.setKeyFlag(false);
                    }
                    // 处理其它信息
                    field.setName(results.getString(dbQuery.fieldName()));
                    String type = results.getString(dbQuery.fieldType());
                    field.setColumnType(dataSourceProperties.getTypeConvert().processTypeConvert(globalProperties, type));
                    if (type.indexOf("(") != -1) {
                        type = type.substring(0, type.indexOf("("));
                    }
                    type = type.toUpperCase();
                    field.setType(type.equalsIgnoreCase("int") ? "INTEGER" : type);
                    field.setPropertyName(StringUtils.processName(field.getName(), strategyProperties.getUnderlineToCamelColumnNames(), strategyProperties.getFieldPrefix()));
                    field.setComment(results.getString(dbQuery.fieldComment()));
                    field.setKeywordFlag(configuration.getKeywordList().contains(field.getName().toUpperCase()));

                    if(field.getName().equalsIgnoreCase("is_delete")){
                        tableInfo.setIsLogicDelete(true);
                    }

                    if (strategyProperties.includeSuperEntityColumns(field.getName())) {
                        // 跳过公共字段
                        commonFieldList.add(field);
                        continue;
                    }

                    // 收集导入包信息
                    if (null != field.getColumnType() && null != field.getColumnType().getPkg()) {
                        tableInfo.getImportPackages().add(field.getColumnType().getPkg());
                    }
//                    if (field.isKeyFlag()) {
//                        // 主键
//                        if (field.isConvert() || field.isKeyIdentityFlag()) {
//                            tableInfo.getImportPackages().add(TableId.class.getCanonicalName());
//                        }
//                        // 自增
//                        if (field.isKeyIdentityFlag()) {
//                            tableInfo.getImportPackages().add(IdType.class.getCanonicalName());
//                        }
//                    } else if (field.isConvert()) {
//                        // 普通字段
//                        tableInfo.getImportPackages().add(TableField.class.getCanonicalName());
//                    }
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
