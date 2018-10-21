package com.lee9213.mybatis.generator.config.sql.query;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * 表数据查询抽象类
 * </p>
 *
 * @author lee9213@163.com
 * @since 2018-01-16
 */
public abstract class AbstractDbQuery implements IDbQuery {


    @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return false;
    }


    @Override
    public String[] fieldCustom() {
        return null;
    }
}
