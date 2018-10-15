package com.lee9213.mybatis.generator.config.builder;

import com.lee9213.mybatis.generator.config.*;
import com.lee9213.mybatis.generator.config.handler.PackageConfigurationHandler;
import com.lee9213.mybatis.generator.config.handler.StrategyConfigurationHandler;
import com.lee9213.mybatis.generator.config.po.TableInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

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
    private TemplateConfiguration templateConfiguration;
    /**
     * 数据库配置
     */
    private DataSourceConfiguration dataSourceConfiguration;
    /**
     * 策略配置
     */
    private StrategyConfiguration strategyConfiguration;
    /**
     * 全局配置信息
     */
    private GlobalConfiguration globalConfiguration;

    /**
     * 包配置
     */
    private PackageConfiguration packageConfiguration;

    /**
     * 数据库表信息
     */
    private List<TableInfo> tableInfoList;
    /**
     * 包配置详情
     */
    private Map<String, String> packageInfo;
    /**
     * 路径配置信息
     */
    private Map<String, String> pathInfo;


    /**
     * <p>
     * 在构造器中处理配置
     * </p>
     *
     * @param packageConfiguration    包配置
     * @param dataSourceConfiguration 数据源配置
     * @param strategyConfiguration   表配置
     * @param templateConfiguration   模板配置
     * @param globalConfiguration     全局配置
     */
    public ConfigurationBuilder(GlobalConfiguration globalConfiguration, TemplateConfiguration templateConfiguration,
                                PackageConfiguration packageConfiguration, DataSourceConfiguration dataSourceConfiguration,
                                StrategyConfiguration strategyConfiguration) {
        // 全局配置
        if (null == globalConfiguration) {
            this.globalConfiguration = new GlobalConfiguration();
        } else {
            this.globalConfiguration = globalConfiguration;
        }
        // 模板配置
        if (null == templateConfiguration) {
            this.templateConfiguration = new TemplateConfiguration();
        } else {
            this.templateConfiguration = templateConfiguration;
        }

        // 包配置
        if (null == packageConfiguration) {
            this.packageConfiguration = new PackageConfiguration();
        } else {
            this.packageConfiguration = packageConfiguration;
        }
        new PackageConfigurationHandler().handler(this);

        // 数据库配置
        this.dataSourceConfiguration = dataSourceConfiguration;

        // 策略配置
        if (null == strategyConfiguration) {
            this.strategyConfiguration = new StrategyConfiguration();
        } else {
            this.strategyConfiguration = strategyConfiguration;
        }
        new StrategyConfigurationHandler().handler(this);
    }
}
