package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.StringUtils;

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
        String serviceName = tableInfo.getServiceName();
        String servicePath = configuration.getPathInfo().getServicePath();
        // 生成Service
        if (StringUtils.isNotEmpty(serviceName) && StringUtils.isNotEmpty(servicePath)) {
            String serviceFile = String.format((servicePath + File.separator + serviceName + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (isCreate(serviceFile)) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getService(), serviceFile);
            }
        }
    }
}
