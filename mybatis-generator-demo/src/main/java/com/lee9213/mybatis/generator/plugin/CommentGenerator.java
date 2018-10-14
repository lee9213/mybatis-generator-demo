//package com.lee9213.mybatis.generator.plugin;
//
//import com.lee9213.mybatis.generator.util.StringUtils;
//import org.mybatis.generator.api.IntrospectedColumn;
//import org.mybatis.generator.api.IntrospectedTable;
//import org.mybatis.generator.api.dom.java.Field;
//import org.mybatis.generator.api.dom.java.TopLevelClass;
//import org.mybatis.generator.internal.DefaultCommentGenerator;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//
///**
// * 生成model中，字段增加注释
// *
// * @author lee9213@163.com
// * @version 1.0
// * @date 2017/3/29 16:21
// */
//public class CommentGenerator extends DefaultCommentGenerator {
//
//    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    private String author;
//
//    @Override
//    public void addConfigurationProperties(Properties properties) {
//        super.addConfigurationProperties(properties);
//        author = properties.getProperty("user.name");
//    }
//
//    /**
//     * 添加字段注释
//     *
//     * @param field
//     * @param introspectedTable
//     * @param introspectedColumn
//     */
//    @Override
//    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
//                                IntrospectedColumn introspectedColumn) {
//        if (StringUtils.isNotEmpty(introspectedColumn.getRemarks())) {
//            field.addJavaDocLine("/**");
//            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
//            field.addJavaDocLine(" */");
//        }
//    }
//
//    /**
//     * 添加类注释
//     *
//     * @param topLevelClass
//     * @param introspectedTable
//     */
//    @Override
//    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        topLevelClass.addJavaDocLine("/**");
//        topLevelClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().getRemarks());
//        topLevelClass.addJavaDocLine(" *");
//        topLevelClass.addJavaDocLine(" * @author " + author);
//        topLevelClass.addJavaDocLine(" * @version 1.0");
//        topLevelClass.addJavaDocLine(" * @date " + format.format(new Date()));
//        topLevelClass.addJavaDocLine(" */");
//    }
//}
