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


import com.google.common.collect.Maps;
import com.lee9213.mybatis.generator.config.StrategyConfiguration;
import com.lee9213.mybatis.generator.config.TemplateConfiguration;
import com.lee9213.mybatis.generator.config.builder.ConfigurationBuilder;
import com.lee9213.mybatis.generator.config.po.TableInfo;
import com.lee9213.mybatis.generator.config.rules.FileType;
import com.lee9213.mybatis.generator.plugin.generator.GeneratorConfigXmlGenerator;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.PackageHelper;
import com.lee9213.mybatis.generator.util.StringPool;
import com.lee9213.mybatis.generator.util.StringUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private ConfigurationBuilder configBuilder;


    /**
     * <p>
     * 模板引擎初始化
     * </p>
     */
    public AbstractTemplateEngine init(ConfigurationBuilder configBuilder) {
        this.configBuilder = configBuilder;
        return this;
    }
    /**
     * <p>
     * 输出 java xml 文件
     * </p>
     */
    public AbstractTemplateEngine batchOutput() {
        try {
            logger.info("========== 开始生成GeneratorConfig.xml ==========");
            GeneratorConfigXmlGenerator generatorConfigXmlGenerator = new GeneratorConfigXmlGenerator();
            generatorConfigXmlGenerator.generator(this);
            logger.info("========== 结束生成GeneratorConfig.xml ==========");

            logger.info("========== 开始运行MybatisGenerator ==========");
            Map<String, String> pathInfo = getConfigBuilder().getPathInfo();
            String generatorConfigXml = pathInfo.get(Constant.GENERATOR_PATH) + File.separator + Constant.GENERATOR_NAME;
            List<String> warnings = new ArrayList<>();
            File configFile = new File(generatorConfigXml);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            for (String warning : warnings) {
                logger.info(warning);
            }
            logger.info("========== 结束运行MybatisGenerator ==========");

            logger.info("========== 开始生成Service、ServiceImpl、Controller ==========");
            TemplateConfiguration templateConfiguration = getConfigBuilder().getTemplateConfiguration();
            List<TableInfo> tableInfoList = getConfigBuilder().getTableInfoList();
            for (TableInfo tableInfo : tableInfoList) {
                Map<String, Object> objectMap = getObjectMap(tableInfo);

                String entityName = tableInfo.getEntityName();
                // 生成Service
                if (null != tableInfo.getServiceName() && null != pathInfo.get(Constant.SERVICE_PATH)) {
                    String serviceFile = String.format((pathInfo.get(Constant.SERVICE_PATH) + File.separator + tableInfo.getServiceName() + Constant.JAVA_SUFFIX), entityName);
                    if (isCreate(FileType.SERVICE, serviceFile)) {
                        writer(objectMap, templateFilePath(templateConfiguration.getService()), serviceFile);
                    }
                }

                // 生成ServiceImpl
                if (null != tableInfo.getServiceImplName() && null != pathInfo.get(Constant.SERVICE_IMPL_PATH)) {
                    String implFile = String.format((pathInfo.get(Constant.SERVICE_IMPL_PATH) + File.separator + tableInfo.getServiceImplName() + Constant.JAVA_SUFFIX), entityName);
                    if (isCreate(FileType.SERVICE_IMPL, implFile)) {
                        writer(objectMap, templateFilePath(templateConfiguration.getServiceImpl()), implFile);
                    }
                }

                // 生成Controller
                if (null != tableInfo.getControllerName() && null != pathInfo.get(Constant.CONTROLLER_PATH)) {
                    String controllerFile = String.format((pathInfo.get(Constant.CONTROLLER_PATH) + File.separator + tableInfo.getControllerName() + Constant.JAVA_SUFFIX), entityName);
                    if (isCreate(FileType.CONTROLLER, controllerFile)) {
                        writer(objectMap, templateFilePath(templateConfiguration.getController()), controllerFile);
                    }
                }
            }

            logger.info("========== 结束生成Service、ServiceImpl、Controller ==========");



//            List<TableInfo> tableInfoList = getConfigBuilder().getTableInfoList();
//            for (TableInfo tableInfo : tableInfoList) {
//                Map<String, Object> objectMap = getObjectMap(tableInfo);
//                Map<String, String> pathInfo = getConfigBuilder().getPathInfo();
//                TemplateConfiguration template = getConfigBuilder().getTemplate();
////                // 自定义内容
//                InjectionConfig injectionConfig = getConfigBuilder().getInjectionConfig();
//                if (null != injectionConfig) {
//                    injectionConfig.initMap();
//                    objectMap.put("cfg", injectionConfig.getMap());
//                    List<FileOutConfig> focList = injectionConfig.getFileOutConfigList();
//                    if (CollectionUtils.isNotEmpty(focList)) {
//                        for (FileOutConfig foc : focList) {
//                            if (isCreate(FileType.OTHER, foc.outputFile(tableInfo))) {
//                                writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
//                            }
//                        }
//                    }
//                }
                // Mp.java
//                String entityName = tableInfo.getEntityName();
//                if (null != entityName && null != pathInfo.get(Constant.ENTITY_PATH)) {
//                    String entityFile = String.format((pathInfo.get(Constant.ENTITY_PATH) + File.separator + "%s" + suffixJavaOrKt()), entityName);
//                    if (isCreate(FileType.ENTITY, entityFile)) {
//                        writer(objectMap, templateFilePath(template.getEntity(getConfigBuilder().getGlobalConfig().isKotlin())), entityFile);
//                    }
//                }
                // MpMapper.java
//                if (null != tableInfo.getMapperName() && null != pathInfo.get(Constant.MAPPER_PATH)) {
//                    String mapperFile = String.format((pathInfo.get(Constant.MAPPER_PATH) + File.separator + tableInfo.getMapperName() + suffixJavaOrKt()), entityName);
//                    if (isCreate(FileType.MAPPER, mapperFile)) {
//                        writer(objectMap, templateFilePath(template.getMapper()), mapperFile);
//                    }
//                }
                // MpMapper.xml
//                if (null != tableInfo.getXmlName() && null != pathInfo.get(Constant.XML_PATH)) {
//                    String xmlFile = String.format((pathInfo.get(Constant.XML_PATH) + File.separator + tableInfo.getXmlName() + Constant.XML_SUFFIX), entityName);
//                    if (isCreate(FileType.XML, xmlFile)) {
//                        writer(objectMap, templateFilePath(template.getXml()), xmlFile);
//                    }
//                }
//            }
        } catch (Exception e) {
            logger.error("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }

    protected Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> objectMap = Maps.newHashMapWithExpectedSize(8);
        objectMap.put("table", tableInfo);
        objectMap.put("datasource", configBuilder.getDataSourceConfiguration());
        objectMap.put("global", configBuilder.getGlobalConfiguration());
        objectMap.put("package", configBuilder.getPackageInfo());
        objectMap.put("templates", configBuilder.getTemplateConfiguration());

        StrategyConfiguration strategyConfiguration = configBuilder.getStrategyConfiguration();
        objectMap.put("strategy", strategyConfiguration);
        if (strategyConfiguration.isControllerMappingHyphenStyle()) {
            objectMap.put("controllerMappingHyphen", StringUtils.camelToHyphen(tableInfo.getEntityPath()));
        }else{
            objectMap.put("controllerMappingHyphen", "");
        }
        return objectMap;
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
    public abstract void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception;

    /**
     * <p>
     * 处理输出目录
     * </p>
     */
    public AbstractTemplateEngine mkdirs() {
        getConfigBuilder().getPathInfo().forEach((key, value) -> {
            File dir = new File(value);
            if (!dir.exists()) {
                boolean result = dir.mkdirs();
                if (result) {
                    logger.debug("创建目录： [" + value + "]");
                }
            }
        });
        return this;
    }


    /**
     * <p>
     * 打开输出目录
     * </p>
     */
    public void open() {
        String outDir = getConfigBuilder().getGlobalConfiguration().getOutputDir();
        if (getConfigBuilder().getGlobalConfiguration().isOpen()
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
        return classPath.substring(classPath.lastIndexOf(StringPool.DOT) + 1);
    }


    /**
     * <p>
     * 模板真实文件路径
     * </p>
     *
     * @param filePath 文件路径
     * @return
     */
    public abstract String templateFilePath(String filePath);


    /**
     * 检测文件是否存在
     *
     * @return 是否
     */
    protected boolean isCreate(FileType fileType, String filePath) {
        ConfigurationBuilder cb = getConfigBuilder();
        // 自定义判断
//        InjectionConfig ic = cb.getInjectionConfig();
//        if (null != ic && null != ic.getFileCreate()) {
//            return ic.getFileCreate().isCreate(cb, fileType, filePath);
//        }
        // 全局判断【默认】
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            PackageHelper.mkDir(file.getParentFile());
        }
        return !exist || getConfigBuilder().getGlobalConfiguration().isFileOverride();
    }
}
