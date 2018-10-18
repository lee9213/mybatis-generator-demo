package com.lee9213.mybatis.generator.engine.generator;

import com.lee9213.mybatis.generator.config.properties.TemplateProperties;
import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.engine.AbstractTemplateEngine;
import com.lee9213.mybatis.generator.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
        Configuration configBuilder = templateEngine.getConfiguration();
        TemplateProperties template = configBuilder.getTemplateProperties();
        String generatorConfigXml = configBuilder.getPathInfo().getGeneratorPath() + File.separator + Constant.GENERATOR_NAME;
        try {
            templateEngine.doWriter(getObjectMap(configBuilder), templateEngine.templateFilePath(template.getGenerator()), generatorConfigXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * 渲染对象 MAP 信息
     * </p>
     *
     * @param configBuilder 配置对象
     * @return
     */
    @Override
    public Map<String, Object> getObjectMap(Configuration configBuilder) {
        Map<String, Object> objectMap = new HashMap<>(8);
        objectMap.put("datasource", configBuilder.getDataSourceProperties());
        objectMap.put("global", configBuilder.getGlobalProperties());
        objectMap.put("package", configBuilder.getPackageInfo());
        objectMap.put("strategy", configBuilder.getStrategyProperties());
        objectMap.put("templates", configBuilder.getTemplateProperties());
        objectMap.put("last_insert_id_tables", new HashMap<>(1));
        objectMap.put("tables", configBuilder.getTableInfoList());
        return objectMap;
    }
}
