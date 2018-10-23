package com.lee9213.mybatis.generator.config.sql.converts;

import com.lee9213.mybatis.generator.config.properties.GlobalProperties;
import com.lee9213.mybatis.generator.config.sql.rules.IColumnType;

/**
 * <p>类型转换</p>
 *
 * @author lee9213@163.com
 * @date 2018/10/18 17:19
 */
public interface ITypeConvert {

    IColumnType processTypeConvert(GlobalProperties globalProperties, String fieldType);

}
