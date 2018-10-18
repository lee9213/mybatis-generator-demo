package com.lee9213.mybatis.generator.config.converts;

import com.lee9213.mybatis.generator.config.properties.GlobalProperties;
import com.lee9213.mybatis.generator.config.rules.IColumnType;

/**
 * @author libo
 * @date 2018/10/18 17:19
 */
public interface ITypeConvert {

    IColumnType processTypeConvert(GlobalProperties globalProperties, String fieldType);

}
