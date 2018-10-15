package com.lee9213.mybatis.generator.config;

import com.lee9213.mybatis.generator.config.po.DbType;
import com.lee9213.mybatis.generator.query.IDbQuery;
import com.lee9213.mybatis.generator.query.MySqlQuery;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 20:54
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "datasource")
@Data
public class DataSourceConfiguration extends AbstractConfiguration {

    /**
     * 数据库信息查询
     */
    private IDbQuery dbQuery;
    /**
     * 数据库类型
     */
    private DbType dbType = DbType.MYSQL;
    /**
     * PostgreSQL schemaName
     */
    private String schemaName;
//    /**
//     * 类型转换
//     */
//    private ITypeConvert typeConvert;
    /**
     * 驱动连接的URL
     */
    private String url;
    /**
     * 驱动名称
     */
    private String driverName = "org.gjt.mm.mysql.Driver";
    /**
     * 数据库连接用户名
     */
    private String username = "root";
    /**
     * 数据库连接密码
     */
    private String password = "123456";

    public IDbQuery getDbQuery() {
        if (null == dbQuery) {
            switch (getDbType()) {
//                case ORACLE:
//                    dbQuery = new OracleQuery();
//                    break;
//                case SQL_SERVER:
//                    dbQuery = new SqlServerQuery();
//                    break;
//                case POSTGRE_SQL:
//                    dbQuery = new PostgreSqlQuery();
//                    break;
//                case DB2:
//                    dbQuery = new DB2Query();
//                    break;
//                case MARIADB:
//                    dbQuery = new MariadbQuery();
//                    break;
                default:
                    // 默认 MYSQL
                    dbQuery = new MySqlQuery();
                    break;
            }
        }
        return dbQuery;
    }

    /**
     * 判断数据库类型
     *
     * @return 类型枚举值
     */
    public DbType getDbType() {
        if (null == dbType) {
            if (driverName.contains("mysql")) {
                dbType = DbType.MYSQL;
            } else if (driverName.contains("oracle")) {
                dbType = DbType.ORACLE;
            } else if (driverName.contains("postgresql")) {
                dbType = DbType.POSTGRE_SQL;
            } else if (driverName.contains("db2")) {
                dbType = DbType.DB2;
            } else if (driverName.contains("mariadb")) {
                dbType = DbType.MARIADB;
            } else {
                throw new RuntimeException("Unknown type of database!");
            }
        }
        return dbType;
    }
    /**
     * 创建数据库连接对象
     *
     * @return Connection
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
