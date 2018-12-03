package com.lee9213.mybatis.generator.plugins;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.util.ApplicationContextUtil;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>mapper生成器</p>
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
        Configuration configuration = (Configuration) ApplicationContextUtil.getBean("configuration");
        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        interfaze.addImportedType(new FullyQualifiedJavaType("com.giants.common.tools.PageCondition"));
        String entityName = configuration.getTableInfoList().stream().filter(tableInfo -> tableInfo.getName().equalsIgnoreCase(introspectedTable.getTableConfiguration().getTableName())).collect(Collectors.toList()).get(0).getEntityName();
        interfaze.addImportedType(new FullyQualifiedJavaType(configuration.getPackageInfo().getEntity() + "." +entityName));
        Method selectByPageCondition = new Method("selectByPageCondition");
        selectByPageCondition.addParameter(new Parameter(new FullyQualifiedJavaType("PageCondition"),"pageCondition"));
        FullyQualifiedJavaType list = new FullyQualifiedJavaType("List");
        list.addTypeArgument(new FullyQualifiedJavaType(entityName));
        selectByPageCondition.setReturnType(list);
        interfaze.addMethod(selectByPageCondition);


        Method countByPageCondition = new Method("countByPageCondition");
        countByPageCondition.addParameter(new Parameter(new FullyQualifiedJavaType("PageCondition"),"pageCondition"));
        countByPageCondition.setReturnType(new FullyQualifiedJavaType("int"));
        interfaze.addMethod(countByPageCondition);
        return true;
    }


    @Override
    public boolean validate(List<String> list) {
        return true;
    }

}
