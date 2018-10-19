/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.lee9213.mybatis.generator.engine;


import com.google.common.base.Strings;
import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.PathInfo;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.config.properties.TemplateProperties;
import com.lee9213.mybatis.generator.config.rules.FileType;
import com.lee9213.mybatis.generator.engine.generator.GeneratorConfigXmlGenerator;
import com.lee9213.mybatis.generator.engine.generator.MybatisGenerator;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.StringUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 模板引擎抽象类
 * </p>
 *
 * @author hubin
 * @since 2018-01-10
 */
@Data
@Accessors(chain = true)
public abstract class AbstractTemplateEngine {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractTemplateEngine.class);
    /**
     * 配置信息
     */
    private Configuration configuration;


    /**
     * <p>
     * 模板引擎初始化
     * </p>
     */
    public AbstractTemplateEngine init(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
    /**
     * <p>
     * 输出 java xml 文件
     * </p>
     */
    public AbstractTemplateEngine writer() {
        try {
            // 生成GeneratorConfig.xml
            GeneratorConfigXmlGenerator generatorConfigXmlGenerator = new GeneratorConfigXmlGenerator();
            generatorConfigXmlGenerator.generator(this);

            // 生成entity、mapper、mapper.xml
            MybatisGenerator mybatisGenerator = new MybatisGenerator();
            mybatisGenerator.generator(this);


            // 生成ExtendMapperXML、Vo、Service、ServiceImpl、Controller
            List<TableInfo> tableInfoList = configuration.getTableInfoList();
            for (TableInfo tableInfo : tableInfoList) {
                Map<String, Object> objectMap = configuration.getConfigurationMap();
                objectMap.put("table", tableInfo);


                extendMapperXmlGenerator(configuration, tableInfo, objectMap);
                voGenerator(configuration,tableInfo,objectMap);
                serviceGenerator(configuration,tableInfo,objectMap);
                serviceImplGenerator(configuration,tableInfo,objectMap);
                controllerGenerator(configuration,tableInfo,objectMap);
            }
        } catch (Exception e) {
            logger.error("无法创建文件，请检查配置信息！");
            e.printStackTrace();
        }
        return this;
    }
    private void extendMapperXmlGenerator(Configuration configuration, TableInfo tableInfo, Map<String, Object> objectMap) throws Exception {
        PathInfo pathInfo = configuration.getPathInfo();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        // 生成扩展xml
        if (!Strings.isNullOrEmpty(tableInfo.getExtendMapperXmlName()) && !Strings.isNullOrEmpty(pathInfo.getExtendMapperXmlPath())) {
            String extendMapperXmlFile = String.format((pathInfo.getExtendMapperXmlPath() + File.separator + tableInfo.getExtendMapperXmlName() + Constant.XML_SUFFIX), tableInfo.getEntityName());
            if (isCreate(FileType.XML, extendMapperXmlFile)) {
                doWriter(objectMap, templateProperties.getExtendMapperXml(), extendMapperXmlFile);
            }
        }
    }
    private void voGenerator(Configuration configuration, TableInfo tableInfo, Map<String, Object> objectMap) throws Exception {
        PathInfo pathInfo = configuration.getPathInfo();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        // 生成VO
        if (null != tableInfo.getVoName() && null != pathInfo.getVoPath()) {
            String voFile = String.format((pathInfo.getVoPath() + File.separator + tableInfo.getVoName() + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (isCreate(FileType.VO, voFile)) {
                doWriter(objectMap, templateProperties.getVo(), voFile);
            }
        }
    }
    private void serviceGenerator(Configuration configuration, TableInfo tableInfo, Map<String, Object> objectMap) throws Exception {
        PathInfo pathInfo = configuration.getPathInfo();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        // 生成Service
        if (null != tableInfo.getServiceName() && null != pathInfo.getServicePath()) {
            String serviceFile = String.format((pathInfo.getServicePath() + File.separator + tableInfo.getServiceName() + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (isCreate(FileType.SERVICE, serviceFile)) {
                doWriter(objectMap, templateProperties.getService(), serviceFile);
            }
        }
    }
    private void serviceImplGenerator(Configuration configuration, TableInfo tableInfo, Map<String, Object> objectMap) throws Exception {
        PathInfo pathInfo = configuration.getPathInfo();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        // 生成ServiceImpl
        if (null != tableInfo.getServiceImplName() && null != pathInfo.getServiceImplPath()) {
            String implFile = String.format((pathInfo.getServiceImplPath() + File.separator + tableInfo.getServiceImplName() + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (isCreate(FileType.SERVICE_IMPL, implFile)) {
                doWriter(objectMap, templateProperties.getServiceImpl(), implFile);
            }
        }
    }
    private void controllerGenerator(Configuration configuration, TableInfo tableInfo, Map<String, Object> objectMap) throws Exception {
        PathInfo pathInfo = configuration.getPathInfo();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        // 生成Controller
        if (null != tableInfo.getControllerName() && null != pathInfo.getControllerPath()) {
            String controllerFile = String.format((pathInfo.getControllerPath() + File.separator + tableInfo.getControllerName() + Constant.JAVA_SUFFIX), tableInfo.getEntityName());
            if (isCreate(FileType.CONTROLLER, controllerFile)) {
                doWriter(objectMap, templateProperties.getController(), controllerFile);
            }
        }
    }

    /**
     * <p>
     * 将模板转化成为文件
     * </p>
     *
     * @param objectMap    渲染对象 MAP 信息
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     */
    public abstract void doWriter(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception;


    /**
     * <p>
     * 打开输出目录
     * </p>
     */
    public void open() {
        String outDir = configuration.getGlobalProperties().getOutputDir();
        if (configuration.getGlobalProperties().isOpen()
            && StringUtils.isNotEmpty(outDir)) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + outDir);
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + outDir);
                    } else {
                        logger.debug("文件输出目录:" + outDir);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取类名
     *
     * @param classPath
     * @return
     */
    private String getSuperClassName(String classPath) {
        if (StringUtils.isEmpty(classPath)) {
            return null;
        }
        return classPath.substring(classPath.lastIndexOf(Constant.DOT) + 1);
    }


    /**
     * 检测文件是否存在
     *
     * @return 是否
     */
    protected boolean isCreate(FileType fileType, String filePath) {
        Configuration cb = configuration;
        // 自定义判断
//        InjectionConfig ic = cb.getInjectionConfig();
//        if (null != ic && null != ic.getFileCreate()) {
//            return ic.getFileCreate().isCreate(cb, fileType, filePath);
//        }
        // 全局判断【默认】
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            mkDir(file.getParentFile());
        }
        return !exist || configuration.getGlobalProperties().isFileOverride();
    }
    /**
     * <p>
     * 新建文件目录
     * </p>
     *
     * @param file 文件
     */
    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }
}
