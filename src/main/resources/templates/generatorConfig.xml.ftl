<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
<generatorConfiguration>

    <context id="MysqlContext" targetRuntime="MyBatis3" defaultModelType="flat">

        <property name="javaFileEncoding" value="UTF-8"/>
        <!-- 由于beginningDelimiter和endingDelimiter的默认值为双引号(")，在Mysql中不能这么写，所以还要将这两个默认值改为`  -->
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>
        <!-- 格式化java代码 -->
        <property name="javaFormatter" value="org.mybatis.generator.api.dom.DefaultJavaFormatter"/>
        <!-- 格式化XML代码 -->
        <property name="xmlFormatter" value="org.mybatis.generator.api.dom.DefaultXmlFormatter"/>
        
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin"></plugin>
        <plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin"></plugin>
        <#--<plugin type="com.lee9213.generator.plugin.MapperPlugin">-->
            <#--<#if strategy.superMapperClass??>-->
            <#--<property name="mappers" value="${strategy.superMapperClass}"/>-->
            <#--</#if>-->
            <#--<property name="user.name" value="${global.author}"/>-->
        <#--</plugin>-->

        <!-- 生成一个新的selectByExample方法，这个方法可以接收offset和limit参数，主要用来实现分页，只支持mysql(已使用pagehelper代替) -->
        <!--<plugin type="com.zheng.common.plugin.PaginationPlugin"></plugin>-->

        <!-- 生成的代码去掉注释 -->
        <commentGenerator type="com.lee9213.mybatis.generator.plugin.mybatis.CommentGenerator">
            <property name="suppressAllComments" value="true"/>
            <property name="suppressDate" value="true"/>
            <property name="user.name" value="${global.author}"/>
        </commentGenerator>

        <!-- 数据库连接 -->
        <jdbcConnection driverClass="${datasource.driverName}" connectionURL="${datasource.url}"
                        userId="${datasource.username}" password="${datasource.password}">
            <!--设置可以获取tables remarks信息-->
            <property name="useInformationSchema" value="true"/>
            <!--设置可以获取remarks信息-->
            <property name="remarks" value="true"/>
        </jdbcConnection>
        <!-- 类型转换 -->
        <javaTypeResolver>
            <!-- 是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.） -->
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!-- model生成 -->
        <javaModelGenerator targetPackage="${package.Entity}" targetProject="${global.outputDir}\src\main\java">
            <!-- 设置一个根对象，-->
            <#if strategy.superEntityClass??>
            <property name="rootClass" value="${strategy.superEntityClass}"/>
            </#if>
        </javaModelGenerator>

        <!-- MapperXML生成 -->
        <sqlMapGenerator targetPackage="mybatis" targetProject="${global.outputDir}\src\main\resources"/>

        <!-- Mapper接口生成 -->
        <javaClientGenerator targetPackage="${package.Mapper}" targetProject="${global.outputDir}\src\main\java"
                             type="XMLMAPPER">
        </javaClientGenerator>

        <!-- 需要映射的表 -->
        <#list tables as table>
            <#if last_insert_id_tables[table.name]?? >
                <table tableName="${table.name}" domainObjectName="${table.entityName}"
                       enableInsert = "true"
                       enableSelectByPrimaryKey = "true"
                       enableSelectByExample = "true"
                       enableUpdateByPrimaryKey = "true"
                       enableDeleteByPrimaryKey = "true"
                       enableDeleteByExample = "true"
                       enableCountByExample="true"
                       enableUpdateByExample="true"
                       mapperName="${table.mapperName}" >
                    <generatedKey column="$!last_insert_id_tables.get($!table.table_name)" sqlStatement="MySql"
                                  identity="true"/>
                    <columnOverride column="UNSIGNED_BIGINT_FIELD" javaType="java.lang.Object" jdbcType="LONG"/>
                </table>
            <#else>
                <table tableName="${table.name}" domainObjectName="${table.entityName}"
                       enableInsert = "true"
                       enableSelectByPrimaryKey = "true"
                       enableSelectByExample = "true"
                       enableUpdateByPrimaryKey = "true"
                       enableDeleteByPrimaryKey = "true"
                       enableDeleteByExample = "true"
                       enableCountByExample="true"
                       enableUpdateByExample="true"
                       mapperName="${table.mapperName}" >
                    <generatedKey column="id" sqlStatement="Mysql" identity="true"/>
                </table>
            </#if>
        </#list>
    </context>
</generatorConfiguration>
