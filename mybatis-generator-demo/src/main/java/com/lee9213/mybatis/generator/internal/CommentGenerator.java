package com.lee9213.mybatis.generator.internal;

import com.google.common.base.Strings;
import com.lee9213.mybatis.generator.config.properties.GlobalProperties;
import com.lee9213.mybatis.generator.util.ApplicationContextUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>生成model中，字段增加注释</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2017/3/29 16:21
 */
public class CommentGenerator extends DefaultCommentGenerator {

    /**
     * 添加字段注释
     *
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (!Strings.isNullOrEmpty(introspectedColumn.getRemarks())) {
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
            field.addJavaDocLine(" */");
        }
    }

    /**
     * 添加类注释
     *
     * @param topLevelClass
     * @param introspectedTable
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        GlobalProperties globalProperties = (GlobalProperties) ApplicationContextUtil.getBean("globalProperties");
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" *");
        topLevelClass.addJavaDocLine(" * @author " + globalProperties.getAuthor());
        topLevelClass.addJavaDocLine(" * @version 1.0");
        topLevelClass.addJavaDocLine(" * @date " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        topLevelClass.addJavaDocLine(" */");
    }
}
