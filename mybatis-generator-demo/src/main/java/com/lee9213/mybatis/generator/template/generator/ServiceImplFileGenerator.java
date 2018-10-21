package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.PathInfo;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.config.properties.TemplateProperties;
import com.lee9213.mybatis.generator.config.rules.FileType;
import com.lee9213.mybatis.generator.util.Constant;

import java.io.File;
import java.util.Map;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 20:06
 */
public class ServiceImplFileGenerator extends AbstractFileGenerator {

    public ServiceImplFileGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        PathInfo pathInfo = configuration.getPathInfo();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        Map<String, Object> objectMap = configuration.getConfigurationMap();
        objectMap.put("table", tableInfo);
        // 生成ServiceImpl
        if (null != tableInfo.getServiceImplName() && null != pathInfo.getServiceImplPath()) {
            String implFile = String.format((pathInfo.getServiceImplPath() + File.separator + tableInfo.getServiceImplName() + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (isCreate(FileType.SERVICE_IMPL, implFile)) {
                configuration.getTemplateEngine().writer(objectMap, templateProperties.getServiceImpl(), implFile);
            }
        }
    }
}
