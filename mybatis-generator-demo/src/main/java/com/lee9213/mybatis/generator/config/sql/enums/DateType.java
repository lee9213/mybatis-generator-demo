package com.lee9213.mybatis.generator.config.sql.enums;

/**
 * <p>
 * 数据库时间类型 到 实体类时间类型 对应策略
 * </p>
 *
 * @author lee9213@163.com
 * @date 2018/10/22 11:26
 */
public enum DateType {
    /**
     * 只使用 java.util.date 代替
     */
    ONLY_DATE,
    /**
     * 使用 java.sql 包下的
     */
    SQL_PACK,
    /**
     * 使用 java.time 包下的
     * java8 新的时间类型
     */
    TIME_PACK
}