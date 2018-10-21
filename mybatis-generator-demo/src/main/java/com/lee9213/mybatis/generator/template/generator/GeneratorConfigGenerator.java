package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.properties.TemplateProperties;
import com.lee9213.mybatis.generator.util.Constant;

import java.io.File;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 19:38
 */
public class GeneratorConfigGenerator implements Generator {

    private Configuration configuration;

    public GeneratorConfigGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void generator() throws Exception {
        TemplateProperties template = configuration.getTemplateProperties();
        String generatorConfigXml = configuration.getPathInfo().getGeneratorPath() + File.separator + Constant.GENERATOR_NAME;
        try {
            configuration.getTemplateEngine().writer(configuration.getConfigurationMap(), template.getGenerator(), generatorConfigXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
