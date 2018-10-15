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
///**
// * @author libo
// * @version 1.0
// * @date 2017/12/1 11:28
// */
//public class ControllerGenerator extends BaseGenerator {
//
//    private static Logger LOGGER = LoggerFactory.getLogger(ControllerGenerator.class);
//
//    public ControllerGenerator(String serviceVm, String targetProject, String packageName) {
//        super(serviceVm, targetProject, packageName);
//    }
//
//    public void generatorController(String tableName) {
//        try {
//            String ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
//            String targetProject = super.targetProject + "/src/main/java/";
//            File file = new File(targetProject);
//            if (!file.exists()) {
//                file.mkdir();
//            }
//            tableName = StringUtil.lineToHump(tableName);
//            String controllerDirectory = targetProject + packageName.replaceAll("\\.", "/") + "/controller/";
//            file = new File(controllerDirectory);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            // 生成Controller
//            String controllerFile = controllerDirectory + tableName + "Controller.java";
//            file = new File(controllerFile);
//            if(file.exists()) return;
//            VelocityContext context = new VelocityContext();
//            context.put("package_name", packageName);
//            context.put("model", tableName);
//            context.put("ctime", ctime);
//            VelocityUtil.generate(serviceVm, controllerFile, context);
//            LOGGER.info(controllerFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
