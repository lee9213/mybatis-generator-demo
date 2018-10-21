package com.lee9213.mybatis.generator.template.engine;

import com.lee9213.mybatis.generator.util.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * <p>
 * Freemarker 模板引擎实现文件输出
 * </p>
 *
 * @author nieqiurong
 * @since 2018-01-11
 */
public class FreemarkerTemplateEngine extends AbstractTemplateEngine implements TemplateEngine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Configuration configuration;

    public FreemarkerTemplateEngine() {
        init();
    }

    @Override
    void init() {
        if (configuration == null) {
            synchronized (FreemarkerTemplateEngine.class) {
                if (configuration == null) {
                    configuration = new Configuration();
                    configuration.setDefaultEncoding(Constant.UTF8);
                    configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, Constant.SLASH);
                }
            }
        }
    }


    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        Template template = configuration.getTemplate(templatePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile))) {
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, Constant.UTF8));
            logger.info("模板:" + templatePath + ";  文件:" + outputFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
