package com.lee9213.mybatis.generator.config;

import com.google.common.collect.Maps;
import com.lee9213.mybatis.generator.config.domain.PackageInfo;
import com.lee9213.mybatis.generator.config.domain.PathInfo;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.config.handler.*;
import com.lee9213.mybatis.generator.config.properties.*;
import com.lee9213.mybatis.generator.template.engine.FreemarkerTemplateEngine;
import com.lee9213.mybatis.generator.template.engine.TemplateEngine;
import com.lee9213.mybatis.generator.util.ApplicationContextUtil;
import com.lee9213.mybatis.generator.util.StringUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>配置汇总 传递给文件生成工具</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-14 0:21
 */
@Data
@Accessors(chain = true)
public class Configuration {

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
     * 模板配置
     */
    private TemplateEngine templateEngine;

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
     * 自定义配置
     */
    private Map<String, Object> customConfigMap = Maps.newHashMap();

    public Configuration(GlobalProperties globalProperties, TemplateProperties templateProperties,
                         PackageProperties packageProperties, DataSourceProperties dataSourceProperties,
                         StrategyProperties strategyProperties) {
        this(globalProperties, templateProperties, packageProperties, dataSourceProperties, strategyProperties, null);
    }

    /**
     * <p>
     * 初始化配置信息
     * </p>
     *
     * @param packageProperties    包配置
     * @param dataSourceProperties 数据源配置
     * @param strategyProperties   表配置
     * @param templateProperties   模板配置
     * @param globalProperties     全局配置
     * @param templateEngine       模板引擎
     */
    public Configuration(GlobalProperties globalProperties, TemplateProperties templateProperties,
                         PackageProperties packageProperties, DataSourceProperties dataSourceProperties,
                         StrategyProperties strategyProperties, TemplateEngine templateEngine) {
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

        // 数据库配置
        if (null == dataSourceProperties) {
            throw new RuntimeException("数据库配置错误");
        }
        this.dataSourceProperties = dataSourceProperties;

        // 策略配置
        if (null == strategyProperties) {
            this.strategyProperties = new StrategyProperties();
        } else {
            this.strategyProperties = strategyProperties;
        }

        if (templateEngine == null) {
            this.templateEngine = new FreemarkerTemplateEngine();
        } else {
            this.templateEngine = templateEngine;
        }

        ConfigHandlerChain interceptorChain = new ConfigHandlerChain();
        interceptorChain.addHandler(new PackageInfoHandler());
        interceptorChain.addHandler(new PathInfoHandler());
        interceptorChain.addHandler(new TableInfoHandler());
        interceptorChain.addHandler(new TableFieldHandler());
        // 添加扩展配置处理器
        Map<String, Object> handlerMap = ApplicationContextUtil.getBeansWithAnnotation(ExtendHandler.class);
        handlerMap.values().forEach(handler -> {
            if (handler instanceof Handler) {
                interceptorChain.addHandler((Handler) handler);
            }
        });
        interceptorChain.handler(this);

        this.tableInfoList.forEach(tableInfo -> checkImportPackages(tableInfo));
    }

    /**
     * <p>
     * 检测导入包
     * </p>
     *
     * @param tableInfo
     */
    private void checkImportPackages(TableInfo tableInfo) {
        if (StringUtils.isNotEmpty(tableInfo.getSuperEntityClass())) {
            // 自定义父类
            tableInfo.getImportPackages().add(tableInfo.getSuperEntityClass());
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

    /**
     * <p>
     * 渲染对象 MAP 信息
     * </p>
     *
     * @return
     */
    public Map<String, Object> getConfigurationMap() {
        Map<String, Object> objectMap = new HashMap<>(8);
        objectMap.put("datasource", this.dataSourceProperties);
        objectMap.put("global", this.globalProperties);
        objectMap.put("strategy", this.strategyProperties);
        objectMap.put("templates", this.templateProperties);
        objectMap.put("tables", this.tableInfoList);
        objectMap.put("package", this.packageInfo);
        objectMap.put("custom", customConfigMap);
        return objectMap;
    }
}
