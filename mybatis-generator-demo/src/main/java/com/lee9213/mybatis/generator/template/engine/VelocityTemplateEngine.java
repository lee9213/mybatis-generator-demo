//package com.lee9213.mybatis.generator.template.engine;
//
//import com.lee9213.mybatis.generator.util.Constant;
//import org.apache.logging.log4j.util.Strings;
//import org.apache.velocity.Template;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.Velocity;
//import org.apache.velocity.app.VelocityEngine;
//
//import java.io.BufferedWriter;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * <p>
// * Velocity 模板引擎实现文件输出
// * </p>
// *
// * @author hubin
// * @since 2018-01-10
// */
//public class VelocityTemplateEngine extends AbstractTemplateEngine implements TemplateEngine {
//
//    private static final String DOT_VM = ".vm";
//    private static VelocityEngine velocityEngine;
//
//    public VelocityTemplateEngine(){
//        init();
//    }
//
//    @Override
//    void init() {
//        if (null == velocityEngine) {
//            synchronized (VelocityTemplateEngine.class) {
//                if (null == velocityEngine) {
//                    Properties p = new Properties();
//                    p.setProperty(Constant.VM_LOAD_PATH_KEY, Constant.VM_LOAD_PATH_VALUE);
//                    p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, Strings.EMPTY);
//                    p.setProperty(Velocity.ENCODING_DEFAULT, Constant.UTF8);
//                    p.setProperty(Velocity.INPUT_ENCODING, Constant.UTF8);
//                    p.setProperty("file.resource.loader.unicode", "true");
//                    velocityEngine = new VelocityEngine(p);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
//        if (Strings.isEmpty(templatePath)) {
//            return;
//        }
//        Template template = velocityEngine.getTemplate(templatePath, Constant.UTF8);
//        FileOutputStream fos = new FileOutputStream(outputFile);
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, Constant.UTF8));
//        template.merge(new VelocityContext(objectMap), writer);
//        writer.close();
//        logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
//    }
//}
