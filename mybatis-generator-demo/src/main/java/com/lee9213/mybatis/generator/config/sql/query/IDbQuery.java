package com.lee9213.mybatis.generator.config.sql.query;


import com.lee9213.mybatis.generator.config.domain.DbType;
import com.lee9213.mybatis.generator.config.properties.StrategyProperties;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * 表数据查询接口
 * </p>
 *
 * @author lee9213@163.com
 * @since 2018-01-16
 */
public interface IDbQuery {


    /**
     * 数据库类型
     */
    DbType dbType();


    /**
     * 表信息查询 SQL
     */
    String tablesSql(StrategyProperties strategyProperties);


    /**
     * 表字段信息查询 SQL
     */
    String tableFieldsSql();


    /**
     * 表名称
     */
    String tableName();


    /**
     * 表注释
     */
    String tableComment();


    /**
     * 字段名称
     */
    String fieldName();


    /**
     * 字段类型
     */
    String fieldType();


    /**
     * 字段注释
     */
    String fieldComment();


    /**
     * 主键字段
     */
    String fieldKey();


    /**
     * <p>
     * 判断主键是否为identity，目前仅对mysql进行检查
     * </p>
     *
     * @param results ResultSet
     * @return 主键是否为identity
     * @throws SQLException
     */
    boolean isKeyIdentity(ResultSet results) throws SQLException;


    /**
     * 自定义字段名称
     */
    String[] fieldCustom();
}
