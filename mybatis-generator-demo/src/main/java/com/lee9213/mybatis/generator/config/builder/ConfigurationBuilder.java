package com.lee9213.mybatis.generator.config.builder;

import com.lee9213.mybatis.generator.config.domain.PackageInfo;
import com.lee9213.mybatis.generator.config.domain.PathInfo;
import com.lee9213.mybatis.generator.config.handler.PackageConfigurationHandler;
import com.lee9213.mybatis.generator.config.handler.StrategyConfigurationHandler;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.config.properties.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 配置汇总 传递给文件生成工具
 * </p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-14 0:21
 */
@Data
@Accessors(chain = true)
public class ConfigurationBuilder {

    /**
     * 模板路径配置信息
     */
    private TemplateProperties templateProperties;
    /**
     * 数据库配置
     */
    private DataSourceProperties dataSourceProperties;
    /**
     * 策略配置
     */
    private StrategyProperties strategyProperties;
    /**
     * 全局配置信息
     */
    private GlobalProperties globalProperties;

    /**
     * 包配置
     */
    private PackageProperties packageProperties;

    /**
     * 数据库表信息
     */
    private List<TableInfo> tableInfoList;
    /**
     * 包配置详情
     */
    private PackageInfo packageInfo;
    /**
     * 路径配置信息
     */
    private PathInfo pathInfo;


    /**
     * <p>
     * 在构造器中处理配置
     * </p>
     *
     * @param packageProperties    包配置
     * @param dataSourceProperties 数据源配置
     * @param strategyProperties   表配置
     * @param templateProperties   模板配置
     * @param globalProperties     全局配置
     */
    public ConfigurationBuilder(GlobalProperties globalProperties, TemplateProperties templateProperties,
                                PackageProperties packageProperties, DataSourceProperties dataSourceProperties,
                                StrategyProperties strategyProperties) {
        // 全局配置
        if (null == globalProperties) {
            this.globalProperties = new GlobalProperties();
        } else {
            this.globalProperties = globalProperties;
        }
        // 模板配置
        if (null == templateProperties) {
            this.templateProperties = new TemplateProperties();
        } else {
            this.templateProperties = templateProperties;
        }

        // 包配置
        if (null == packageProperties) {
            this.packageProperties = new PackageProperties();
        } else {
            this.packageProperties = packageProperties;
        }
        new PackageConfigurationHandler().handler(this);

        // 数据库配置
        this.dataSourceProperties = dataSourceProperties;

        // 策略配置
        if (null == strategyProperties) {
            this.strategyProperties = new StrategyProperties();
        } else {
            this.strategyProperties = strategyProperties;
        }
        new StrategyConfigurationHandler().handler(this);
    }
}
