package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.FileUtil;
import com.lee9213.mybatis.generator.util.StringUtils;

import java.io.File;
import java.util.Map;

/**
 * <p>VO生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 20:06
 */
public class VoFileGenerator extends AbstractFileGenerator {

    public VoFileGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        String voName = tableInfo.getVoName();
        String voPath = configuration.getPathInfo().getVoPath();
        // 生成VO
        if (StringUtils.isNotEmpty(voName) && StringUtils.isNotEmpty(voPath)) {
            FileUtil.mkdir(voPath);

            String voFile = String.format((voPath + File.separator + voName + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (isCreate(voFile)) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getVo(), voFile);
            }
        }
    }
}
