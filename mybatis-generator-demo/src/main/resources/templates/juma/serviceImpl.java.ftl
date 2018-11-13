package ${package.serviceImpl};

import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.juma.auth.employee.domain.LoginEmployee;
import ${package.entity}.${table.entityName};
import ${package.mapper}.${table.mapperName};
import ${package.service}.${table.serviceName};
import ${package.vo}.${table.voName};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<#if strategy.superServiceImplClass?default("")?length gt 1>
import ${strategy.superServiceImplClass};
</#if>

<#if table.isLogicDelete>
import java.util.Date;
</#if>
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
@Service
<#if strategy.superServiceImplClass?default("")?length gt 1>
public class ${table.serviceImplName} extends ${strategy.superServiceImplClass}<${table.mapperName}, ${table.entityName}> implements ${table.serviceName} {
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {
</#if>

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ${table.mapperName} ${table.mapperName?uncap_first};

    /**
     * 添加${table.comment!}
     *
     * @param ${table.voName?uncap_first}
     * @param loginEmployee
     */
    @Override
    public ${table.voName} add(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        if (${table.voName?uncap_first} == null) {
            throw new BusinessException("add.${table.entityName?uncap_first}.error", "新增内容为空");
        }
        ${table.entityName} ${table.entityName?uncap_first} = convert(${table.voName?uncap_first});
        ${table.mapperName?uncap_first}.insertSelective(${table.entityName?uncap_first});
        return convert(${table.entityName?uncap_first});
    }

    /**
     * 获取${table.comment!}详情
     *
     * @param id
     * @param loginEmployee
     */
    @Override
    public ${table.voName} detail(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        if (id == null) {
            throw new BusinessException("detail.${table.entityName?uncap_first}.error", "id为空");
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
     * @param loginEmployee
     */
    @Override
    public void update(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        if (${table.voName?uncap_first} == null  || ${table.voName?uncap_first}.getId() == null) {
            throw new BusinessException("update.${table.entityName?uncap_first}.error", "更新内容为空");
        }

        ${table.entityName} ${table.entityName?uncap_first} = convert(${table.voName?uncap_first});
        <#if table.isLogicDelete>
        ${table.entityName?uncap_first}.setLastUpdateUserId(loginEmployee.getUserId());
        ${table.entityName?uncap_first}.setLastUpdateTime(new Date());
        </#if>
        ${table.mapperName?uncap_first}.updateByPrimaryKeySelective(${table.entityName?uncap_first});
    }


    /**
     * 删除${table.comment!}
     *
     * @param id
     * @param loginEmployee
     */
    @Override
    public void delete(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        if (id == null) {
            throw new BusinessException("delete.${table.entityName?uncap_first}.error", "id为空");
        }
        <#if table.isLogicDelete>
        ${table.entityName} ${table.entityName?uncap_first} = ${table.mapperName?uncap_first}.selectByPrimaryKey(id);
        if (${table.entityName?uncap_first} == null) {
            throw new BusinessException("delete.${table.entityName?uncap_first}.error", "数据异常");
        }
		${table.entityName?uncap_first}.setLastUpdateTime(new Date());
		${table.entityName?uncap_first}.setLastUpdateUserId(loginEmployee.getUserId());
        // 默认逻辑删除
		${table.entityName?uncap_first}.setDelete(true);
        ${table.mapperName?uncap_first}.updateByPrimaryKeySelective(${table.entityName?uncap_first});
        <#else>
        ${table.mapperName?uncap_first}.deleteByPrimaryKey(id);
        </#if>

    }

    /**
     * 获取${table.comment!}列表
     *
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    @Override
    public Page<${table.voName}> list(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
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
        BeanUtils.copyProperties(${table.voName?uncap_first}, ${table.entityName?uncap_first});
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
        BeanUtils.copyProperties(${table.entityName?uncap_first}, ${table.voName?uncap_first});
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