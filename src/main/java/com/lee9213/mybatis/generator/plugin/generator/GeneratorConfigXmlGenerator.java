package com.lee9213.mybatis.generator.plugin.generator;

import com.lee9213.mybatis.generator.config.TemplateConfiguration;
import com.lee9213.mybatis.generator.config.builder.ConfigBuilder;
import com.lee9213.mybatis.generator.config.po.TableInfo;
import com.lee9213.mybatis.generator.engine.AbstractTemplateEngine;
import com.lee9213.mybatis.generator.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 生成GenertorConfig.xml
 * </p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-14 21:16
 */
public class GeneratorConfigXmlGenerator implements BaseGenerator {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void genertor(AbstractTemplateEngine templateEngine) {
        ConfigBuilder configBuilder = templateEngine.getConfigBuilder();
        List<TableInfo> tableInfoList = configBuilder.getTableInfoList();
        TemplateConfiguration template = configBuilder.getTemplate();
        String generatorConfigXml = configBuilder.getPathInfo().get(Constant.GENERATOR_PATH) + File.separator + Constant.GENERATOR_NAME;
        try {
            templateEngine.writer(templateEngine.getObjectMap(tableInfoList), templateEngine.templateFilePath(template.getGenerator()), generatorConfigXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
