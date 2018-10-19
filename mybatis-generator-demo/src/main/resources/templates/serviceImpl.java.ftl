package ${package.serviceImpl};

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import ${package.entity}.${table.entityName};
import ${package.vo}.${table.voName};
import ${package.mapper}.${table.mapperName};
import ${package.service}.${table.serviceName};
<#if strategy.superServiceImplClass?default("")?length gt 1>
import ${strategy.superServiceImplClass};
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
     * add 新增接口
     *
     * @param ${table.voName?uncap_first}
     * @param loginEmployee
     */
    @Override
    public ${table.entityName} add(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        validAdd(${table.voName?uncap_first}, loginEmployee);
        ${table.entityName} ${table.entityName?uncap_first} = convert${table.entityName}(${table.voName?uncap_first}, loginEmployee);
        ${table.mapperName?uncap_first}.insert(${table.entityName?uncap_first});
        return ${table.entityName?uncap_first};
    }

    /**
     * 新增接口验证数据
     *
     * @param ${table.voName?uncap_first}
     * @param loginEmployee
     */
    private void validAdd(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        if (${table.voName?uncap_first} == null) {
            throw new BusinessException("add.${table.entityName?uncap_first}.error", "新增内容为空");
        }
        //TODO 新增接口的其他部分的验证

    }

    /**
     * 从${table.voName} 转换到 ${table.entityName}
     *
     * @param ${table.voName?uncap_first}
     * @param loginEmployee
     */
    private ${table.entityName} convert${table.entityName}(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        if (${table.voName?uncap_first} == null) {
            return new ${table.entityName}();
        }
        //TODO 类似的验证自己添加
        ${table.entityName} ${table.entityName?uncap_first} = new ${table.entityName}();
        BeanUtils.copyProperties(${table.voName?uncap_first}, ${table.entityName?uncap_first});
        return ${table.entityName?uncap_first};
    }

    /**
     * detail 详情接口
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
        ${table.voName} ${table.voName?uncap_first} = convert${table.voName}(${table.entityName?uncap_first}, loginEmployee);
        return ${table.voName?uncap_first};
    }

    /**
     * 从 ${table.entityName}	转换到 	${table.voName}
     *
     * @param ${table.entityName?uncap_first}
     * @param loginEmployee
     */
    private ${table.voName} convert${table.voName}(${table.entityName} ${table.entityName?uncap_first}, LoginEmployee loginEmployee) {
        if (${table.entityName?uncap_first} == null) {
            return new ${table.voName}();
        }
        ${table.voName} ${table.voName?uncap_first} = new ${table.voName}();
        BeanUtils.copyProperties(${table.entityName?uncap_first}, ${table.voName?uncap_first});
        return ${table.voName?uncap_first};
    }

    /**
     * update 更新接口
     *
     * @param ${table.voName?uncap_first}
     * @param loginEmployee
     */
    @Override
    public void update(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        validUpdate(${table.voName?uncap_first}, loginEmployee);
        ${table.entityName} ${table.entityName?uncap_first} = convertUpdate${table.entityName}(${table.voName?uncap_first}, loginEmployee);
        ${table.mapperName?uncap_first}.updateByPrimaryKeySelective(${table.entityName?uncap_first});
    }

    /**
     * 验证能否修改
     *
     * @param ${table.voName?uncap_first}
     * @param loginEmployee
     */
    private void validUpdate(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        if (${table.voName?uncap_first} == null) {
            throw new BusinessException("update.${table.entityName?uncap_first}.error", "更新内容为空");
        }
        //TODO 其他验证自己加
    }

    /**
     * 修改对象转换成数据库对象
     *
     * @param ${table.voName?uncap_first}
     * @param loginEmployee
     */
    private ${table.entityName} convertUpdate${table.entityName}(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {

        ${table.entityName} ${table.entityName?uncap_first} = ${table.mapperName?uncap_first}.selectByPrimaryKey(${table.voName?uncap_first}.getId());
        BeanUtils.copyProperties(${table.voName?uncap_first}, ${table.entityName?uncap_first});
//		${table.entityName?uncap_first}.setLastUpdateUserId(loginEmployee.getUserId());
//		${table.entityName?uncap_first}.setLastUpdateTime(new Date());
        return ${table.entityName?uncap_first};
    }

    /**
     * delete  删除接口
     *
     * @param id
     * @param loginEmployee
     */
    @Override
    public void delete(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        validDelete(id, loginEmployee);
        ${table.entityName} ${table.entityName?uncap_first} = ${table.mapperName?uncap_first}.selectByPrimaryKey(id);
//		${table.entityName?uncap_first}.setLastUpdateTime(new Date());
//		${table.entityName?uncap_first}.setLastUpdateUserId(loginEmployee.getUserId());
        // 默认逻辑删除
//		${table.entityName?uncap_first}.setIsDelete(true);
        ${table.mapperName?uncap_first}.updateByPrimaryKey(${table.entityName?uncap_first});
    }

    /**
     * 验证能否删除
     *
     * @param id
     * @param loginEmployee
     */
    private void validDelete(Integer id, LoginEmployee loginEmployee) {
        if (id == null) {
            throw new BusinessException("delete.${table.entityName?uncap_first}.error", "id为空");
        }

        ${table.entityName} ${table.entityName?uncap_first} = ${table.mapperName?uncap_first}.selectByPrimaryKey(id);
        if (${table.entityName?uncap_first} == null) {
            throw new BusinessException("delete.${table.entityName?uncap_first}.error", "数据异常");
        }
        //TODO 删除方面其他验证自己加,例如状态
    }

    /**
     * 列表
     *
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    @Override
    public Page<${table.voName}> list(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
    pageCondition.getFilters().put("tenant_id", loginEmployee.getTenantId());
    pageCondition.getFilters().put("tenantCode", loginEmployee.getTenantCode());
    		return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(),
    			${table.mapperName?uncap_first}.count(pageCondition),
    			convert${table.entityName}List(${table.mapperName?uncap_first}.select(pageCondition)));
    }

    /**
     * 从 List<${table.entityName}>	转换到 	List<${table.voName}>
     *
     * @param ${table.entityName?uncap_first}List
     */
    private List<${table.voName}> convert${table.entityName}List(List<${table.entityName}> ${table.entityName?uncap_first}List) {
    List<${table.voName}> ${table.voName?uncap_first}List = Lists.newArrayList();
        ${table.voName} ${table.voName?uncap_first};
        for (${table.entityName} ${table.entityName?uncap_first} : ${table.entityName?uncap_first}List) {
            ${table.voName?uncap_first} = new ${table.voName}();
            BeanUtils.copyProperties(${table.entityName?uncap_first}, ${table.voName?uncap_first});
            ${table.voName?uncap_first}List.add(${table.voName?uncap_first});
        }
        return ${table.voName?uncap_first}List;
    }
}