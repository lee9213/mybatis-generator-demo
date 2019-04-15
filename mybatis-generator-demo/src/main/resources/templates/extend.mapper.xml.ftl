<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.mapper}.${table.mapperName}">

<#if global.enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>
</#if>
    <#--
    <!-- 通用查询映射结果 &ndash;&gt;
    <resultMap id="BaseResultMapExtend" type="${package.entity}.${table.entityName}">
    <#list table.fields as field>
        <#if field.keyFlag>&lt;#&ndash;生成主键排在第一位&ndash;&gt;
        <id column="${field.name}" property="${field.propertyName}" jdbcType="${field.type}"/>
        <#else>
        <result column="${field.name}" property="${field.propertyName}" jdbcType="${field.type}"/>
        </#if>
    </#list>
    <#list table.commonFields as field>&lt;#&ndash;生成公共字段 &ndash;&gt;
        <result column="${field.name}" property="${field.propertyName}" jdbcType="${field.type}"/>
    </#list>
    </resultMap>

    <!-- 通用查询结果列 &ndash;&gt;
    <sql id="Base_Column_ListExtend">
        <#assign fieldLength=table.fields?size/>
        <#assign commonFieldLength=table.commonFields?size/>
        <#list table.fields as field><#if field.keywordFlag>`</#if>${field.name}<#if field.keywordFlag>`</#if><#if field_index?number lt fieldLength-1>, </#if></#list>
        <#if commonFieldLength?number gt 0>,<#list table.commonFields as field><#if field.keywordFlag>`</#if>${field.name}<#if field.keywordFlag>`</#if><#if field_index?number lt commonFieldLength-1>, </#if></#list></#if>
    </sql>
    -->

    <select id="page" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${table.name}
        order by id desc
    </select>
</mapper>
