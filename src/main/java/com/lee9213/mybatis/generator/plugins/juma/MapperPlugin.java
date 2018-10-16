package com.lee9213.mybatis.generator.plugins.juma;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.internal.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * mapper生成器
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

//    /**
//     * Entity生成
//     *
//     * @param topLevelClass
//     * @param introspectedTable
//     * @return
//     */
//    @Override
//    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        // 添加时间处理
//        List<Field> fields = topLevelClass.getFields();
//        boolean flag = false;
//        for (int i = 0; i < fields.size(); i++) {
//            Field f = fields.get(i);
//            if ("date".equalsIgnoreCase(f.getType().getShortName())) {
//                f.addAnnotation("@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
//                flag = true;
//            }
//        }
//        if (flag) {
//            topLevelClass.addImportedType(
//                    new FullyQualifiedJavaType("org.springframework.format.annotation.DateTimeFormat"));
//        }
//
//        return true;
//    }

//    /**
//     * Mapper生成
//     *
//     * @param interfaze
//     * @param topLevelClass
//     * @param introspectedTable
//     * @return
//     */
//    @Override
//    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
//                                   IntrospectedTable introspectedTable) {
//        interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
//        interfaze.addAnnotation("@Mapper");
//
//        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
//        interfaze.addImportedType(entityType);
//
//        Iterator<String> iterator = this.mappers.iterator();
//        while (iterator.hasNext()) {
//            String mapper = iterator.next();
//            interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
//            interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" + entityType.getShortName() + ">"));
//        }
//        addInterfaceJavaDoc(interfaze,author,introspectedTable.getRemarks());
//        List<Method> methods = interfaze.getMethods();
//        for (int i = 0; i < methods.size();) {
//            if (methods.size() == 0)
//                break;
//            methods.remove(i);
//        }
//        return true;
//    }

//    /**
//     * xml生成
//     *
//     * @param document
//     * @param introspectedTable
//     * @return
//     */
//    @Override
//    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
//        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();// 数据库表名
//        XmlElement parentElement = document.getRootElement();
//        // 产生分页语句前半部分
//        XmlElement element = new XmlElement("select");
//        element.addAttribute(new Attribute("id", "listByQuery"));
//        element.addAttribute(new Attribute("parameterType", "com.lee9213.core.base.mapper.query.Query"));
//        element.addAttribute(new Attribute("resultMap", "BaseResultMap"));
//
//        element.addElement(new TextElement(" SELECT\n" +
//                "        <include refid=\"Base_Column_List\"/>\n" +
//                "        FROM "+tableName+"\n" +
//                "        <where>\n" +
//                "            <if test=\"params != null\">\n" +
//                "                <foreach collection=\"params\" item=\"value\" index=\"key\" separator=\"and\">\n" +
//                "                    ${key} = #{value}\n" +
//                "                </foreach>\n" +
//                "            </if>\n" +
//                "        </where>"));
//        parentElement.addElement(element);
//
//        return super.sqlMapDocumentGenerated(document, introspectedTable);
//    }

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
}
