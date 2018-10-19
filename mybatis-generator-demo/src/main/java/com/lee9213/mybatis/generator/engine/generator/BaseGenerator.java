package com.lee9213.mybatis.generator.engine.generator;

import com.lee9213.mybatis.generator.engine.AbstractTemplateEngine;

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
    void generator(AbstractTemplateEngine templateEngine) throws Exception;

}
