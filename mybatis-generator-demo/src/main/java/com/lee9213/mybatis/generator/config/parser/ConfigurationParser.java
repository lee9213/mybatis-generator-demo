package com.lee9213.mybatis.generator.config.parser;

import com.lee9213.mybatis.generator.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>配置解析</p>
 *
 * @author lee9213@163.com
 * @date 2018/10/18 10:32
 */
public class ConfigurationParser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Configuration configuration;

    public ConfigurationParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parser() {
        // TODO 优化解析
        PackageInfoParser packageInfoParser = new PackageInfoParser(configuration);
        packageInfoParser.parser();

        PathInfoParser pathInfoParser = new PathInfoParser(configuration);
        pathInfoParser.parser();

        TableInfoParser tableInfoParser = new TableInfoParser(configuration);
        tableInfoParser.parser();

        TableFieldParser tableFieldParser = new TableFieldParser(configuration);
        tableFieldParser.parser();

    }
}
