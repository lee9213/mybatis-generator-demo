package com.lee9213.mybatis.generator.template.generator;

import java.io.File;
import java.util.Map;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.util.ApplicationContextUtil;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.FileUtil;
import com.lee9213.mybatis.generator.util.StringUtils;

/**
 * <p>VO生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 20:06
 */
public class ConvertFileGenerator extends AbstractFileGenerator {

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        Configuration configuration = (Configuration) ApplicationContextUtil.getBean("configuration");
        String convertName = tableInfo.getConvertName();
        String convertPath = configuration.getPathInfo().getConvertPath();

        // 生成VO
        if (StringUtils.isNotEmpty(convertName) && StringUtils.isNotEmpty(convertPath)) {
            FileUtil.mkdir(convertPath);

            String convertFile = String.format((convertPath + File.separator + convertName + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (!FileUtil.exists(convertFile) || configuration.getGlobalProperties().isFileOverride()) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getConvert(), convertFile);
            }
        }
    }
}
