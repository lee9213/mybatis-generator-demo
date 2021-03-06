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
 * <p>ServiceImpl生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 20:06
 */
public class ServiceImplFileGenerator extends AbstractFileGenerator {

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        Configuration configuration = (Configuration) ApplicationContextUtil.getBean("configuration");
        String serviceImplName = tableInfo.getServiceImplName();
        String serviceImplPath = configuration.getPathInfo().getServiceImplPath();

        // 生成ServiceImpl
        if (StringUtils.isNotEmpty(serviceImplName) && StringUtils.isNotEmpty(serviceImplPath)) {
            FileUtil.mkdir(serviceImplPath);

            String implFile = String.format((serviceImplPath + File.separator + serviceImplName + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (!FileUtil.exists(implFile) || configuration.getGlobalProperties().isFileOverride()) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getServiceImpl(), implFile);
            }
        }
    }
}
