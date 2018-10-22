package com.lee9213.mybatis.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.properties.*;
import com.lee9213.mybatis.generator.template.engine.TemplateEngine;
import com.lee9213.mybatis.generator.template.generator.*;
import com.lee9213.mybatis.generator.util.StringUtils;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>自动生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-14 0:07
 */
@Setter
@Accessors(chain = true)
public class AutoGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 数据源配置
     */
    private DataSourceProperties dataSourceProperties;
    /**
     * 数据库表配置
     */
    private StrategyProperties strategyProperties;
    /**
     * 包 相关配置
     */
    private PackageProperties packageProperties;
    /**
     * 模板 相关配置
     */
    private TemplateProperties templateProperties;
    /**
     * 全局 相关配置
     */
    private GlobalProperties globalProperties;
    /**
     * 模板引擎
     */
    private TemplateEngine templateEngine;

    /**
     * 生成代码
     */
    public void execute() {
        try {
            logger.info("==========================准备生成文件...==========================");
            // 初始化配置
            Configuration configuration = new Configuration(globalProperties, templateProperties, packageProperties, dataSourceProperties, strategyProperties, templateEngine);

            // 生成GeneratorConfig.xml
            GeneratorConfigFileGenerator generatorConfigXmlGenerator = new GeneratorConfigFileGenerator(configuration);
            generatorConfigXmlGenerator.generator();

            // 生成entity、mapper、mapper.xml
            MybatisFileGenerator mybatisGenerator = new MybatisFileGenerator(configuration);
            mybatisGenerator.generator();

            ExtendMapperXmlFileGenerator extendMapperXmlFileGenerator = new ExtendMapperXmlFileGenerator(configuration);
            extendMapperXmlFileGenerator.generator();

            VoFileGenerator voFileGenerator = new VoFileGenerator(configuration);
            voFileGenerator.generator();

            ServiceFileGenerator serviceFileGenerator = new ServiceFileGenerator(configuration);
            serviceFileGenerator.generator();

            ServiceImplFileGenerator serviceImplFileGenerator = new ServiceImplFileGenerator(configuration);
            serviceImplFileGenerator.generator();

            ControllerFileGenerator controllerFileGenerator = new ControllerFileGenerator(configuration);
            controllerFileGenerator.generator();


            // 模板引擎初始化执行文件输出
            logger.info("==========================文件生成完成！！！==========================");
        } catch (Exception ex) {
            logger.error("无法创建文件，请检查配置信息！");
            ex.printStackTrace();
        }
    }

    /**
     * <p>
     * 打开输出目录
     * </p>
     */
    public void open(Configuration configuration) {
        String outDir = configuration.getGlobalProperties().getOutputDir();
        if (configuration.getGlobalProperties().isOpen()
                && StringUtils.isNotEmpty(outDir)) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + outDir);
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + outDir);
                    } else {
                        logger.debug("文件输出目录:" + outDir);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
