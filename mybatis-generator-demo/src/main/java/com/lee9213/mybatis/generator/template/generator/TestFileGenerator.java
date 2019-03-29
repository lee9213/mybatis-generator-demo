package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.util.SpringContextUtil;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.FileUtil;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.io.File;
import java.util.Map;

/**
 * @author libo
 * @date 2018/11/8 12:27
 */
public class TestFileGenerator extends AbstractFileGenerator {
    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        Configuration configuration = (Configuration) SpringContextUtil.getBean("configuration");
        String testName = tableInfo.getTestName();
        String testPath = configuration.getPathInfo().getTestPath();

        // 生成Test
        if (StringUtils.isNotEmpty(testName) && StringUtils.isNotEmpty(testPath)) {
            FileUtil.mkdir(testPath);

            String implFile = String.format((testPath + File.separator + testName + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (!FileUtil.exists(implFile) || configuration.getGlobalProperties().isFileOverride()) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getTest(), implFile);
            }
        }
    }
}
