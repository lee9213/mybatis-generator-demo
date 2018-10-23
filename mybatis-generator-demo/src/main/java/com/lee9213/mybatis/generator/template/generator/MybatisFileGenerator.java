package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.util.ApplicationContextUtil;
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
 * <p>Mybatis文件输出</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 19:40
 */
public class MybatisFileGenerator implements FileGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void generator() throws Exception {
        Configuration configuration = (Configuration) ApplicationContextUtil.getBean("configuration");
        // 生成entity、mapper、mapper.xml
        String generatorConfigXml = configuration.getPathInfo().getGeneratorPath() + File.separator + Constant.GENERATOR_NAME;
        List<String> warnings = new ArrayList<>();
        File configFile = new File(generatorConfigXml);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        org.mybatis.generator.config.Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(configuration.getGlobalProperties().isFileOverride());
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            logger.info(warning);
        }
    }
}
