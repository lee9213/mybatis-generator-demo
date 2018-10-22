package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.io.File;
import java.util.Map;

/**
 * <p>Controller生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 20:07
 */
public class ControllerFileGenerator extends AbstractFileGenerator {

    public ControllerFileGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        String controllerName = tableInfo.getControllerName();
        String controllerPath = configuration.getPathInfo().getControllerPath();
        // 生成Controller
        if (StringUtils.isNotEmpty(controllerName) && StringUtils.isNotEmpty(controllerPath)) {
            String controllerFile = String.format((controllerPath + File.separator + controllerName + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (isCreate(controllerFile)) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getController(), controllerFile);
            }
        }
    }
}
