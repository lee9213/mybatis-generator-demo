package com.lee9213.mybatis.generator.plugins.juma;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.internal.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>mapper生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2017/3/29 16:21
 */
public class MapperPlugin extends PluginAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(MapperPlugin.class);
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Set<String> mappers = new HashSet<>();
    private String author = "";

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String mappers = this.properties.getProperty("mappers");
        if (StringUtility.stringHasValue(mappers)) {
            String[] caseSensitive = mappers.split(",");
            int beginningDelimiter = caseSensitive.length;
            String schema;
            for (int endingDelimiter = 0; endingDelimiter < beginningDelimiter; ++endingDelimiter) {
                schema = caseSensitive[endingDelimiter];
                this.mappers.add(schema);
            }
        }
        author = properties.getProperty("user.name");
    }

    /**
     * Entity生成
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        return true;
    }

    /**
     * Mapper生成
     *
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {

        return true;
    }

    /**
     * xml生成
     *
     * @param document
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    private void addInterfaceJavaDoc(Interface interfaze, String author, String remarks){
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine(" * " + remarks);
        interfaze.addJavaDocLine(" *");
        interfaze.addJavaDocLine(" * @author " + author);
        interfaze.addJavaDocLine(" * @version 1.0");
        interfaze.addJavaDocLine(" * @date " + format.format(new Date()));
        interfaze.addJavaDocLine(" */");
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();

//        GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
//                context.getJavaModelGeneratorConfiguration()
//                        .getTargetProject(),
//                context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
//                context.getJavaFormatter());
//        answer.add(gjf);
//
//        GeneratedJavaFile gjf1 = new GeneratedJavaFile(compilationUnit,
//                context.getJavaClientGeneratorConfiguration()
//                        .getTargetProject(),
//                context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
//                context.getJavaFormatter());
//        answer.add(gjf1);

        return answer;
    }
}
