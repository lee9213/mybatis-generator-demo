package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.FileUtil;
import com.lee9213.mybatis.generator.util.SpringContextUtil;
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
public class ClientFileGenerator extends AbstractFileGenerator {

    @Override
    protected void doGenerator(TableInfo tableInfo) throws Exception {
        Configuration configuration = (Configuration) SpringContextUtil.getBean("configuration");
        String clientName = tableInfo.getClientName();
        String clientPath = configuration.getPathInfo().getClientPath();

        // 生成VO
        if (StringUtils.isNotEmpty(clientName) && StringUtils.isNotEmpty(clientPath)) {
            FileUtil.mkdir(clientPath);

            String clientFile = String.format((clientPath + File.separator + clientName + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (!FileUtil.exists(clientFile) || configuration.getGlobalProperties().isFileOverride()) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);
                configuration.getTemplateEngine().writer(objectMap, configuration.getTemplateProperties().getClient(), clientFile);
            }
        }
    }
}
