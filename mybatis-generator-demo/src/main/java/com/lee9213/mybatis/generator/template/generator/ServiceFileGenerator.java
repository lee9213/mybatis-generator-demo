package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.util.ApplicationContextUtil;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.FileUtil;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.io.File;
import java.util.Map;

/**
 * <p>Service生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 19:46
 */
public class ServiceFileGenerator extends AbstractFileGenerator {

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        Configuration configuration = (Configuration) ApplicationContextUtil.getBean("configuration");
        String serviceName = tableInfo.getServiceName();
        String servicePath = configuration.getPathInfo().getServicePath();

        // 生成Service
        if (StringUtils.isNotEmpty(serviceName) && StringUtils.isNotEmpty(servicePath)) {
            FileUtil.mkdir(servicePath);

            String serviceFile = String.format((servicePath + File.separator + serviceName + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (!FileUtil.exists(serviceFile) || configuration.getGlobalProperties().isFileOverride()) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getService(), serviceFile);
            }
        }
    }
}
