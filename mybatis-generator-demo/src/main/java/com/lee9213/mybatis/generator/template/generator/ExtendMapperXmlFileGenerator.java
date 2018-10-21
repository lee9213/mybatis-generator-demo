package com.lee9213.mybatis.generator.template.generator;

import com.google.common.base.Strings;
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
 * @date 2018-10-21 20:05
 */
public class ExtendMapperXmlFileGenerator extends AbstractFileGenerator {

    public ExtendMapperXmlFileGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        PathInfo pathInfo = configuration.getPathInfo();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        Map<String, Object> objectMap = configuration.getConfigurationMap();
        objectMap.put("table", tableInfo);
        // 生成扩展xml
        if (!Strings.isNullOrEmpty(tableInfo.getExtendMapperXmlName()) && !Strings.isNullOrEmpty(pathInfo.getExtendMapperXmlPath())) {
            String extendMapperXmlFile = String.format((pathInfo.getExtendMapperXmlPath() + File.separator + tableInfo.getExtendMapperXmlName() + Constant.XML_SUFFIX), tableInfo.getEntityName());
            if (isCreate(FileType.XML, extendMapperXmlFile)) {
                configuration.getTemplateEngine().writer(objectMap, templateProperties.getExtendMapperXml(), extendMapperXmlFile);
            }
        }
    }
}
