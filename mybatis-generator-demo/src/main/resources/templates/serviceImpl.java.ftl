package ${package.serviceImpl};

import com.lee9213.als.common.exception.BusinessException;
import com.google.common.collect.Lists;
import ${package.entity}.${table.entityName};
import ${package.mapper}.${table.mapperName};
import ${package.service}.${table.serviceName};
import ${package.vo}.${table.voName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
<#if strategy.superServiceImplClass?default("")?length gt 1>
import ${strategy.superServiceImplClass};
</#if>

import java.util.Date;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
@Slf4j
@Service
<#if strategy.superServiceImplClass?default("")?length gt 1>
public class ${table.serviceImplName} extends ${strategy.superServiceImplClass}<${table.mapperName}, ${table.entityName}> implements ${table.serviceName} {
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {
</#if>

    @Autowired
    private ${table.mapperName} ${table.mapperName?uncap_first};

    /**
     * 添加${table.comment!}
     *
     * @param ${table.voName?uncap_first}
     */
    @Override
    public ${table.voName} add(${table.voName} ${table.voName?uncap_first}) throws BusinessException {
        if (${table.voName?uncap_first} == null) {
            throw new BusinessException("add.${table.entityName}.error", "新增内容为空");
        }
        ${table.entityName} ${table.entityName?uncap_first} = convert(${table.voName?uncap_first});
        ${table.mapperName?uncap_first}.insert(${table.entityName?uncap_first});
        return convert(${table.entityName?uncap_first});
    }

    /**
     * 获取${table.comment!}详情
     *
     * @param id
     */
    @Override
    public ${table.voName} detail(Integer id) throws BusinessException {
        if (id == null) {
            throw new BusinessException("detail.${table.entityName}.error", "id为空");
        }
        ${table.entityName} ${table.entityName?uncap_first} = ${table.mapperName?uncap_first}.selectByPrimaryKey(id);
        if (${table.entityName?uncap_first} == null) {
            return new ${table.voName}();
        }
        ${table.voName} ${table.voName?uncap_first} = convert(${table.entityName?uncap_first});
        return ${table.voName?uncap_first};
    }

    /**
     * 修改${table.comment!}
     *
     * @param ${table.voName?uncap_first}
     */
    @Override
    public void update(${table.voName} ${table.voName?uncap_first}) throws BusinessException {
        if (${table.voName?uncap_first} == null || ${table.voName?uncap_first}.getId() == null) {
            throw new BusinessException("update.${table.entityName}.error", "更新内容为空");
        }

        ${table.entityName} ${table.entityName?uncap_first} = convert(${table.voName?uncap_first})
                .setLastUpdateUserId(loginEmployee.getUserId())
                .setLastUpdateTime(new Date());
        ${table.mapperName?uncap_first}.updateByPrimaryKeySelective(${table.entityName?uncap_first});
    }


    /**
     * 删除${table.comment!}
     *
     * @param id
     */
    @Override
    public void delete(Integer id) throws BusinessException {
        if (id == null) {
            throw new BusinessException("delete.${table.entityName}.error", "id为空");
        }
        <#if table.isLogicDelete>
        ${table.entityName} ${table.entityName?uncap_first} = ${table.mapperName?uncap_first}.selectByPrimaryKey(id);
        if (${table.entityName?uncap_first} == null) {
            throw new BusinessException("delete.${table.entityName}.error", "数据异常");
        }
        ${table.mapperName?uncap_first}.updateByPrimaryKeySelective(new ${table.entityName}()
                .setId(${table.entityName?uncap_first}.getId())
                .setLastUpdateUserId(loginEmployee.getUserId())
                .setLastUpdateTime(new Date())
                .setIsDelete(true));
        <#else>
        ${table.mapperName?uncap_first}.deleteByPrimaryKey(id);
        </#if>

    }

    /**
     * 获取${table.comment!}列表
     *
     * @param pageCondition
     * @return
     */
    @Override
    public Page<${table.voName}> list(PageCondition pageCondition) throws BusinessException {
        pageCondition.getFilters().put("tenantId", loginEmployee.getTenantId());
        int count = ${table.mapperName?uncap_first}.countByPageCondition(pageCondition);
        if (count <= 0) {
            return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, null);
        }
        List<${table.voName}> ${table.voName?uncap_first}List = convertList(${table.mapperName?uncap_first}.selectByPageCondition(pageCondition));
        return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, ${table.voName?uncap_first}List);
    }

    /**
     * 从${table.voName}转换到${table.entityName}
     *
     * @param ${table.voName?uncap_first}
     * @return
     */
    private ${table.entityName} convert(${table.voName} ${table.voName?uncap_first}) {
        ${table.entityName} ${table.entityName?uncap_first} = new ${table.entityName}();
        if (${table.voName?uncap_first} == null) {
            return ${table.entityName?uncap_first};
        }

        <#list table.fields as field>
        <#if field_index == 0>${table.entityName?uncap_first}.set${field.propertyName?cap_first}(${table.voName?uncap_first}.get${field.propertyName?cap_first}())<#else>        .set${field.propertyName?cap_first}(${table.voName?uncap_first}.get${field.propertyName?cap_first}())</#if><#if !field_has_next>;</#if>
        </#list>
        <#--<#list table.commonFields as field>-->
        <#--.set${field.propertyName?cap_first}();-->
        <#--</#list>-->
        <#--BeanUtils.copyProperties(${table.voName?uncap_first}, ${table.entityName?uncap_first});-->
        return ${table.entityName?uncap_first};
    }

    /**
     * 从${table.entityName}转换到${table.voName}
     *
     * @param ${table.entityName?uncap_first}
     * @return
     */
    private ${table.voName} convert(${table.entityName} ${table.entityName?uncap_first}) {
        ${table.voName} ${table.voName?uncap_first} = new ${table.voName}();
        if (${table.entityName?uncap_first} == null) {
            return ${table.voName?uncap_first};
        }
        <#list table.fields as field>
        <#if field_index == 0>${table.voName?uncap_first}.set${field.propertyName?cap_first}(${table.entityName?uncap_first}.get${field.propertyName?cap_first}())<#else>        .set${field.propertyName?cap_first}(${table.entityName?uncap_first}.get${field.propertyName?cap_first}())</#if><#if !field_has_next>;</#if>
        </#list>
        <#--BeanUtils.copyProperties(${table.entityName?uncap_first}, ${table.voName?uncap_first});-->
        return ${table.voName?uncap_first};
    }

    /**
     * 从 List<${table.entityName}>	转换到 	List<${table.voName}>
     *
     * @param ${table.entityName?uncap_first}List
     */
    private List<${table.voName}> convertList(List<${table.entityName}> ${table.entityName?uncap_first}List) {
        List<${table.voName}> ${table.voName?uncap_first}List = Lists.newArrayList();
        if (CollectionUtils.isEmpty(${table.entityName?uncap_first}List)) {
            return ${table.voName?uncap_first}List;
        }
        for (${table.entityName} ${table.entityName?uncap_first} : ${table.entityName?uncap_first}List) {
            ${table.voName?uncap_first}List.add(convert(${table.entityName?uncap_first}));
        }
        return ${table.voName?uncap_first}List;
    }
}