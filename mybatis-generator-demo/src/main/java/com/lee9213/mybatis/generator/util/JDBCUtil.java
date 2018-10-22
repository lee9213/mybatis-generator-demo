package com.lee9213.mybatis.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-19 23:46
 */
public final class JDBCUtil {

    /**
     * 创建数据库连接对象
     *
     * @return Connection
     */
    public static Connection getConnection(String url, String username, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
