package com.lee9213.mybatis.generator.engine.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.properties.TemplateProperties;
import com.lee9213.mybatis.generator.engine.AbstractTemplateEngine;
import com.lee9213.mybatis.generator.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * <p>
 * 生成generatorConfig.xml
 * </p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-14 21:16
 */
public class GeneratorConfigXmlGenerator implements BaseGenerator {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 生成文件
     *
     * @param templateEngine 模板类型
     */
    @Override
    public void generator(AbstractTemplateEngine templateEngine) {
        Configuration configuration = templateEngine.getConfiguration();
        TemplateProperties template = configuration.getTemplateProperties();
        String generatorConfigXml = configuration.getPathInfo().getGeneratorPath() + File.separator + Constant.GENERATOR_NAME;
        try {
            templateEngine.doWriter(configuration.getConfigurationMap(), template.getGenerator(), generatorConfigXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
