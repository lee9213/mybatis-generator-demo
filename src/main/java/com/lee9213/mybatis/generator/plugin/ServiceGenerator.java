//package com.lee9213.mybatis.generator.plugin;
//
//import com.lee9213.mybatis.generator.config.TemplateConfiguration;
//import com.lee9213.mybatis.generator.config.po.TableInfo;
//import com.lee9213.mybatis.generator.config.rules.FileType;
//import com.lee9213.mybatis.generator.engine.AbstractTemplateEngine;
//import com.lee9213.mybatis.generator.util.Constant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.util.List;
//
///**
// * @author libo
// * @version 1.0
// * @date 2017/12/1 11:09
// */
//public class ServiceGenerator implements BaseGenerator {
//
//    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    public void genertor(AbstractTemplateEngine templateEngine) {
//
//
//
//        if (null != tableInfo.getServiceName() && null != pathInfo.get(Constant.SERVICE_PATH)) {
//            String serviceFile = String.format((pathInfo.get(Constant.SERVICE_PATH) + File.separator + tableInfo.getServiceName() + suffixJavaOrKt()), entityName);
//            if (isCreate(FileType.SERVICE, serviceFile)) {
//                writer(objectMap, templateFilePath(template.getService()), serviceFile);
//            }
//        }
//
//
//
//        List<TableInfo> tableInfoList = templateEngine.getConfigBuilder().getTableInfoList();
//        TemplateConfiguration template = templateEngine.getConfigBuilder().getTemplate();
//        String outputDir = "E:\\code\\mine\\mybatis-generator\\mybatis-generator-demo\\src\\main\\resources\\generatorConfig.xml";
//        try {
//            templateEngine.writer(templateEngine.getObjectMap(tableInfoList), templateEngine.templateFilePath(template.getGenerator()), outputDir);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
