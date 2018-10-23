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
 * @author libo
 * @date 2018/10/23 10:40
 */
public class EntityFileGenerator extends AbstractFileGenerator {

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        Configuration configuration = (Configuration) ApplicationContextUtil.getBean("configuration");
        String entityName = tableInfo.getEntityName();
        String entityPath = configuration.getPathInfo().getEntityPath();

        // 生成Entity
        if (StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(entityPath)) {
            FileUtil.mkdir(entityPath);

            String entityFile = String.format((entityPath + File.separator + entityName + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (!FileUtil.exists(entityFile) || configuration.getGlobalProperties().isFileOverride()) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getEntity(), entityFile);
            }
        }
    }
}
