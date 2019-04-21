package com.lee9213.mybatis.generator.plugins;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.util.SpringContextUtil;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * mapper生成器
 * </p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2017/3/29 16:21
 */
public class MapperPlugin extends PluginAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(MapperPlugin.class);

    /**
     * 不使用自带生成
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
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
        interfaze.getMethods().removeIf(e -> true);
        Configuration configuration = (Configuration) SpringContextUtil.getBean("configuration");
        String superMapperClass = configuration.getStrategyProperties().getSuperMapperClass();
        interfaze.addImportedType(new FullyQualifiedJavaType(superMapperClass));
        TableInfo tableInfo1 = configuration.getTableInfoList().stream().filter(
                tableInfo -> tableInfo.getName().equalsIgnoreCase(introspectedTable.getTableConfiguration().getTableName()))
                .collect(Collectors.toList()).get(0);
        String entityName = tableInfo1.getEntityName();
        FullyQualifiedJavaType entity = new FullyQualifiedJavaType(configuration.getPackageInfo().getEntity() + "." + entityName);
        interfaze.addImportedType(entity);

        FullyQualifiedJavaType superClass = new FullyQualifiedJavaType(superMapperClass);
        superClass.addTypeArgument(entity);
        interfaze.addSuperInterface(superClass);
        interfaze.addAnnotation("@Mapper");
        interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
//        Method pageMethod = new Method("page");
//        FullyQualifiedJavaType parameter = new FullyQualifiedJavaType("IPage");
//        parameter.addTypeArgument(entity);
//        pageMethod.addParameter(new Parameter(parameter, "page"));
//        FullyQualifiedJavaType list = new FullyQualifiedJavaType("List");
//        list.addTypeArgument(new FullyQualifiedJavaType(entityName));
//        pageMethod.setReturnType(list);
//        pageMethod.addJavaDocLine("/**");
//        pageMethod.addJavaDocLine(" * 分页查询" + tableInfo1.getComment() + "列表");
//        pageMethod.addJavaDocLine(" *");
//        pageMethod.addJavaDocLine(" * @param page 分页对象");
//        pageMethod.addJavaDocLine(" * @return 分页信息");
//        pageMethod.addJavaDocLine(" */");
//        interfaze.addMethod(pageMethod);

        return true;
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
                                                           IntrospectedTable introspectedTable) {
        method.setName("selectById");
        return super.clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        element.getAttributes().removeIf(attribute -> "id".equals(attribute.getName()));
        element.addAttribute(new Attribute("id", "selectById"));
        return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
                                                           IntrospectedTable introspectedTable) {
        method.setName("deleteById");
        return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        element.getAttributes().removeIf(attribute -> "id".equals(attribute.getName()));
        element.addAttribute(new Attribute("id", "deleteById"));
        return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                       IntrospectedTable introspectedTable) {
        method.setName("updateById");
        return super.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
                                                                        IntrospectedTable introspectedTable) {
        element.getAttributes().removeIf(attribute -> "id".equals(attribute.getName()));
        element.addAttribute(new Attribute("id", "updateById"));
        return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        element.addAttribute(new Attribute("useGeneratedKeys", "true"));
        element.addAttribute(new Attribute("keyProperty", "id"));
        element.addAttribute(new Attribute("keyColumn", "id"));
        return super.sqlMapInsertElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze,
                                                        IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        return false;
    }

}
