package com.lee9213.mybatis.generator.config.properties;

import com.lee9213.mybatis.generator.config.sql.converts.ITypeConvert;
import com.lee9213.mybatis.generator.config.sql.converts.MySqlTypeConvert;
import com.lee9213.mybatis.generator.config.sql.enums.DbType;
import com.lee9213.mybatis.generator.config.sql.query.IDbQuery;
import com.lee9213.mybatis.generator.config.sql.query.MySqlQuery;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>数据库配置信息</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 20:54
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "datasource")
@Data
public class DataSourceProperties {

    /**
     * 数据库信息查询
     */
    private IDbQuery dbQuery = new MySqlQuery();
    /**
     * 数据库类型
     */
    private DbType dbType = DbType.MYSQL;
    /**
     * PostgreSQL schemaName
     */
    private String schemaName;
    /**
     * 类型转换
     */
    private ITypeConvert typeConvert = new MySqlTypeConvert();
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
}
