package com.lee9213.mybatis.generator.extend.config;

import org.mybatis.generator.config.ModelType;

/**
 * @author libo
 * @date 2018/10/17 17:48
 */
public class Context extends org.mybatis.generator.config.Context {

    private JavaServiceGeneratorConfiguration javaServiceGeneratorConfiguration;

    public Context(ModelType defaultModelType) {
        super(defaultModelType);
    }
}
