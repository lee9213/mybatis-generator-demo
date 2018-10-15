package com.lee9213.mybatis.generator.plugin.generator;

import com.lee9213.mybatis.generator.config.TemplateConfiguration;
import com.lee9213.mybatis.generator.config.builder.ConfigurationBuilder;
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
        ConfigurationBuilder configBuilder = templateEngine.getConfigBuilder();
        TemplateConfiguration template = configBuilder.getTemplateConfiguration();
        String generatorConfigXml = configBuilder.getPathInfo().get(Constant.GENERATOR_PATH) + File.separator + Constant.GENERATOR_NAME;
        try {
            templateEngine.writer(getObjectMap(configBuilder), templateEngine.templateFilePath(template.getGenerator()), generatorConfigXml);
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
    public Map<String, Object> getObjectMap(ConfigurationBuilder configBuilder) {
        Map<String, Object> objectMap = new HashMap<>(8);
        objectMap.put("datasource", configBuilder.getDataSourceConfiguration());
        objectMap.put("global", configBuilder.getGlobalConfiguration());
        objectMap.put("package", configBuilder.getPackageInfo());
        objectMap.put("strategy", configBuilder.getStrategyConfiguration());
        objectMap.put("templates", configBuilder.getTemplateConfiguration());
        objectMap.put("last_insert_id_tables", new HashMap<>(1));
        objectMap.put("tables", configBuilder.getTableInfoList());
        return objectMap;
    }
}
