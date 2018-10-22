package com.lee9213.mybatis.generator.config.parser;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.config.properties.StrategyProperties;
import com.lee9213.mybatis.generator.util.StringUtils;
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

        configuration.getTableInfoList().forEach(tableInfo -> checkImportPackages(tableInfo));

    }

    /**
     * <p>
     * 检测导入包
     * </p>
     *
     * @param tableInfo
     */
    private void checkImportPackages(TableInfo tableInfo) {
        StrategyProperties strategyProperties = configuration.getStrategyProperties();
        if (!tableInfo.getIsLogicDelete()) {
            strategyProperties.setSuperEntityClass(null);
        }
        if (StringUtils.isNotEmpty(strategyProperties.getSuperEntityClass()) && tableInfo.getIsLogicDelete()) {
            // 自定义父类
            tableInfo.getImportPackages().add(strategyProperties.getSuperEntityClass());
        } /*else if (globalConfig.isActiveRecord()) {
            // 无父类开启 AR 模式
            tableInfo.getImportPackages().add(com.baomidou.mybatisplus.extension.activerecord.Model.class.getCanonicalName());
        }
        if (null != globalConfig.getIdType()) {
            // 指定需要 IdType 场景
            tableInfo.getImportPackages().add(com.baomidou.mybatisplus.annotation.IdType.class.getCanonicalName());
            tableInfo.getImportPackages().add(com.baomidou.mybatisplus.annotation.TableId.class.getCanonicalName());
        }
        */
    }
}
