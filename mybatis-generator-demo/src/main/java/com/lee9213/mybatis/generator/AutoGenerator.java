package com.lee9213.mybatis.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.properties.*;
import com.lee9213.mybatis.generator.engine.AbstractTemplateEngine;
import com.lee9213.mybatis.generator.engine.FreemarkerTemplateEngine;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
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
    private AbstractTemplateEngine templateEngine;

    /**
     * 生成代码
     */
    public void execute() {
        logger.info("==========================准备生成文件...==========================");
        // 初始化配置
        Configuration configuration = new Configuration(globalProperties, templateProperties, packageProperties, dataSourceProperties, strategyProperties);
        if (null == templateEngine) {
            // 采用 Freemarker 引擎 【 默认 】
            templateEngine = new FreemarkerTemplateEngine();
        }

        // 模板引擎初始化执行文件输出
        templateEngine.init(configuration).writer().open();
        logger.info("==========================文件生成完成！！！==========================");
    }
}
