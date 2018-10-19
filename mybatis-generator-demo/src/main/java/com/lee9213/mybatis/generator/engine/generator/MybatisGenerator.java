package com.lee9213.mybatis.generator.engine.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.PathInfo;
import com.lee9213.mybatis.generator.engine.AbstractTemplateEngine;
import com.lee9213.mybatis.generator.util.Constant;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author libo
 * @date 2018/10/18 10:13
 */
public class MybatisGenerator implements BaseGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void generator(AbstractTemplateEngine templateEngine) throws Exception {
        Configuration configBuilder = templateEngine.getConfiguration();
        // 生成entity、mapper、mapper.xml
        PathInfo pathInfo = configBuilder.getPathInfo();
        String generatorConfigXml = pathInfo.getGeneratorPath() + File.separator + Constant.GENERATOR_NAME;
        List<String> warnings = new ArrayList<>();
        File configFile = new File(generatorConfigXml);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        org.mybatis.generator.config.Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(configBuilder.getGlobalProperties().isFileOverride());
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            logger.info(warning);
        }
    }
}
