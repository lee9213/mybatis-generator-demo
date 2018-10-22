package com.lee9213.mybatis.generator.config.sql.rules;

/**
 * <p>获取实体类字段属性类信息接口</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 14:04
 */
public interface IColumnType {

    /**
     * <p>
     * 获取字段类型
     * </p>
     *
     * @return 字段类型
     */
    String getType();

    /**
     * <p>
     * 获取字段类型完整名
     * </p>
     *
     * @return 字段类型完整名
     */
    String getPkg();
}
