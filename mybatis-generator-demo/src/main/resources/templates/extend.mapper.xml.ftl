<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.mapper}.${table.mapperName}">

<#if global.enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>
</#if>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMapExtend" type="${package.entity}.${table.entityName}">
    <#list table.fields as field>
        <#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" jdbcType="${field.type}"/>
        <#else>
        <result column="${field.name}" property="${field.propertyName}" jdbcType="${field.type}"/>
        </#if>
    </#list>
    <#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.name}" property="${field.propertyName}" jdbcType="${field.type}"/>
    </#list>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_ListExtend">
        ${table.fieldNames}
    </sql>

    <sql id="selectWhere">
    </sql>

    <select id="selectByPageCondition" resultMap="BaseResultMapExtend" parameterType="com.giants.common.tools.PageCondition">
        select
        <include refid="Base_Column_ListExtend"/>
        from ${table.name}
        <include refid="selectWhere"/>
        order by id desc
        limit ${r'#{startOffSet}'},${r'#{pageSize}'}
    </select>

    <select id="countByPageCondition" resultType="java.lang.Integer"
            parameterType="com.giants.common.tools.PageCondition">
        select count(1) from ${table.name}
        <include refid="selectWhere"/>
    </select>
</mapper>
