//package com.lee9213.mybatis.generator.plugin;
//
//import com.lee9213.utility.other.StringUtil;
//import org.apache.velocity.VelocityContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
///**
// * @author libo
// * @version 1.0
// * @date 2017/12/1 11:09
// */
//public class ServiceImplGenerator extends BaseGenerator {
//
//    private static Logger LOGGER = LoggerFactory.getLogger(ServiceImplGenerator.class);
//
//    public ServiceImplGenerator(String serviceVm, String targetProject, String packageName) {
//        super(serviceVm, targetProject, packageName);
//    }
//
//    public void generatorServiceImpl(String tableName) {
//        String ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
//        String targetProject = super.targetProject + "/src/main/java/";
//        try {
//            File file = new File(targetProject);
//            if (!file.exists()) {
//                file.mkdir();
//            }
//            tableName = StringUtil.lineToHump(tableName);
//            String serviceDirectory = targetProject + packageName.replaceAll("\\.", "/") + "/service/";
//            file = new File(serviceDirectory);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            // // 生成serviceImpl
//            String serviceImplDirectory = serviceDirectory + "impl/";
//            file = new File(serviceImplDirectory);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            String serviceImplFile = serviceImplDirectory + tableName + "ServiceImpl.java";
//            file = new File(serviceImplFile);
//            if(file.exists()) return;
//            VelocityContext context = new VelocityContext();
//            context.put("package_name", packageName);
//            context.put("model", tableName);
//            context.put("mapper", StringUtil.toLowerCaseFirstOne(tableName));
//            context.put("ctime", ctime);
//            VelocityUtil.generate(serviceVm, serviceImplFile, context);
//            LOGGER.info(serviceImplFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
