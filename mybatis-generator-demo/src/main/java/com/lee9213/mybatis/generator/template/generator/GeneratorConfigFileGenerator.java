package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.util.SpringContextUtil;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.FileUtil;

import java.io.File;

/**
 * <p>GeneratorConfig.xml生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 19:38
 */
public class GeneratorConfigFileGenerator implements FileGenerator {

    @Override
    public void generator() throws Exception {
        Configuration configuration = (Configuration) SpringContextUtil.getBean("configuration");

        String generatorPath = configuration.getPathInfo().getGeneratorPath();
        FileUtil.mkdir(generatorPath);

        String generatorConfigXml = generatorPath + File.separator + Constant.GENERATOR_NAME;
        try {
            configuration.getTemplateEngine().writer(configuration.getConfigurationMap(), configuration.getTemplateProperties().getGenerator(), generatorConfigXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
