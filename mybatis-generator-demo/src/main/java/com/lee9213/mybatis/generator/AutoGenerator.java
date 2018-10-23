package com.lee9213.mybatis.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.properties.*;
import com.lee9213.mybatis.generator.template.engine.TemplateEngine;
import com.lee9213.mybatis.generator.template.generator.*;
import com.lee9213.mybatis.generator.util.ApplicationContextUtil;
import com.lee9213.mybatis.generator.util.StringUtils;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.io.IOException;
import java.util.Map;

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
     * 模板引擎
     */
    private TemplateEngine templateEngine;

    /**
     * 生成代码
     */
    public void execute() {
        try {
            logger.info("==========================准备生成文件...==========================");
            logger.debug("==========================配置准备中...==========================");
            GlobalProperties globalProperties = (GlobalProperties) ApplicationContextUtil.getBean("globalProperties");
            TemplateProperties templateProperties = (TemplateProperties) ApplicationContextUtil.getBean("templateProperties");
            PackageProperties packageProperties = (PackageProperties) ApplicationContextUtil.getBean("packageProperties");
            DataSourceProperties dataSourceProperties = (DataSourceProperties) ApplicationContextUtil.getBean("dataSourceProperties");
            StrategyProperties strategyProperties = (StrategyProperties) ApplicationContextUtil.getBean("strategyProperties");
            // 初始化配置
            Configuration configuration = new Configuration(globalProperties, templateProperties, packageProperties, dataSourceProperties, strategyProperties, templateEngine);
            logger.debug("==========================配置准备完成...==========================");
            // 将配置注入容器中
            DefaultListableBeanFactory autowireCapableBeanFactory = (DefaultListableBeanFactory) ApplicationContextUtil.getApplicationContext().getAutowireCapableBeanFactory();
            autowireCapableBeanFactory.registerSingleton("configuration", configuration);

            // 生成GeneratorConfig.xml
            GeneratorConfigFileGenerator generatorConfigXmlGenerator = new GeneratorConfigFileGenerator();
            generatorConfigXmlGenerator.generator();

            // 生成entity、mapper、mapper.xml
            MybatisFileGenerator mybatisGenerator = new MybatisFileGenerator();
            mybatisGenerator.generator();


            GeneratorFileChain generatorFileChain = new GeneratorFileChain();
            // 添加生成Entity
            generatorFileChain.addGenerator(new EntityFileGenerator());
            // 添加生成ExtendMapper.xml
            generatorFileChain.addGenerator(new ExtendMapperXmlFileGenerator());
            // 添加生成VO
            generatorFileChain.addGenerator(new VoFileGenerator());
            // 添加生成Service
            generatorFileChain.addGenerator(new ServiceFileGenerator());
            // 添加生成ServiceImpl
            generatorFileChain.addGenerator(new ServiceImplFileGenerator());
            // 添加生成Controller
            generatorFileChain.addGenerator(new ControllerFileGenerator());

            // 添加扩展生成器
            Map<String, Object> extendGenerators = ApplicationContextUtil.getBeansWithAnnotation(ExtendGenerator.class);
            extendGenerators.values().forEach(object -> {
                if (object instanceof AbstractFileGenerator) {
                    generatorFileChain.addGenerator((AbstractFileGenerator) object);
                }
            });

            // 执行生成
            generatorFileChain.generator();

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
