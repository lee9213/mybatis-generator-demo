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
 * @date 2018-10-21 19:46
 */
public class ServiceFileGenerator extends AbstractFileGenerator {

    public ServiceFileGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        PathInfo pathInfo = configuration.getPathInfo();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        Map<String, Object> objectMap = configuration.getConfigurationMap();
        objectMap.put("table", tableInfo);
        // 生成Service
        if (null != tableInfo.getServiceName() && null != pathInfo.getServicePath()) {
            String serviceFile = String.format((pathInfo.getServicePath() + File.separator + tableInfo.getServiceName() + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (isCreate(FileType.SERVICE, serviceFile)) {
                configuration.getTemplateEngine().writer(objectMap, templateProperties.getService(), serviceFile);
            }
        }
    }
}
