package com.lee9213.mybatis.generator.extend.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.template.generator.AbstractFileGenerator;
import com.lee9213.mybatis.generator.template.generator.ExtendGenerator;
import com.lee9213.mybatis.generator.util.ApplicationContextUtil;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.FileUtil;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.util.Map;

/**
 * @author libo
 * @date 2018/10/23 11:43
 */
@ExtendGenerator
public class JumaGenerator extends AbstractFileGenerator {

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        Configuration configuration = (Configuration) ApplicationContextUtil.getBean("configuration");

        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(configuration.getPathInfo().getEntityPath());

        // 生成dubbo.xml
        if (StringUtils.isNotEmpty(pathBuilder.toString())) {
            FileUtil.mkdir(pathBuilder.toString());

            pathBuilder.append("dubbo").append(Constant.XML_SUFFIX);
            String file = pathBuilder.toString();
            if (!FileUtil.exists(file)) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, "/templates/juma/dubbo.xml.ftl", file);
            }
        }
    }
}
