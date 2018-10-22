package com.lee9213.mybatis.generator.util;

import com.google.common.collect.Lists;
import com.lee9213.mybatis.generator.config.properties.DataSourceProperties;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-19 23:46
 */
public final class JDBCUtil {

    /**
     * 获取数据库关键字列表
     *
     * @param dataSourceProperties
     * @return
     */
    public static List<String> getKeywordList(DataSourceProperties dataSourceProperties) {
        String sql = "SELECT WORD FROM INFORMATION_SCHEMA.KEYWORDS";
        try (Connection connection = dataSourceProperties.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet results = preparedStatement.executeQuery()) {
            DatabaseMetaData metaData = connection.getMetaData();
            String version = metaData.getDatabaseProductVersion();
            List<String> keywordList = Lists.newArrayList();
            while (results.next()) {
                keywordList.add(results.getString("WORD"));
            }
            return keywordList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Lists.newArrayList();
        }
    }
}
