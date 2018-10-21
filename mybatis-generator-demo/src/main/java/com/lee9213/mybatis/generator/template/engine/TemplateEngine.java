package com.lee9213.mybatis.generator.template.engine;


import java.util.Map;


/**
 * <p>
 * 模板引擎抽象类
 * </p>
 *
 * @author libo
 * @since 2018-10-10 20:52
 */
public interface TemplateEngine {

    /**
     * <p>
     * 将模板写成文件
     * </p>
     *
     * @param objectMap    渲染对象 MAP 信息
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     */
    void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception;


}
