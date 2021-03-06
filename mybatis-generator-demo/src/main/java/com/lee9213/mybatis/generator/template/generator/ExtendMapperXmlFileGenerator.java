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
 * <p>ExtendMapper.xml生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 20:05
 */
public class ExtendMapperXmlFileGenerator extends AbstractFileGenerator {

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        Configuration configuration = (Configuration) ApplicationContextUtil.getBean("configuration");
        String extendMapperXmlName = tableInfo.getExtendMapperXmlName();
        String extendMapperXmlPath = configuration.getPathInfo().getExtendMapperXmlPath();

        // 生成扩展xml
        if (StringUtils.isNotEmpty(extendMapperXmlName) && StringUtils.isNotEmpty(extendMapperXmlPath)) {
            FileUtil.mkdir(extendMapperXmlPath);

            String extendMapperXmlFile = String.format(extendMapperXmlPath + File.separator + extendMapperXmlName + Constant.XML_SUFFIX, tableInfo.getEntityName());
            if (!FileUtil.exists(extendMapperXmlFile) || configuration.getGlobalProperties().isFileOverride()) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getExtendMapperXml(), extendMapperXmlFile);
            }
        }
    }
}
