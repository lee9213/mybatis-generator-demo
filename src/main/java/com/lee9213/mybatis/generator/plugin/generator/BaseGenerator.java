package com.lee9213.mybatis.generator.plugin.generator;

import com.lee9213.mybatis.generator.config.builder.ConfigurationBuilder;
import com.lee9213.mybatis.generator.engine.AbstractTemplateEngine;

import java.util.Map;

/**
 * @author libo
 * @version 1.0
 * @date 2017/12/1 11:13
 */
public interface BaseGenerator {

    /**
     * 生成文件
     *
     * @param templateEngine 模板类型
     */
    void generator(AbstractTemplateEngine templateEngine);

    /**
     * <p>
     * 渲染对象 MAP 信息
     * </p>
     *
     * @param configBuilder 配置对象
     * @return
     */
    Map<String, Object> getObjectMap(ConfigurationBuilder configBuilder);

}
