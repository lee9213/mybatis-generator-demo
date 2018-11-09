package ${package.service};

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import ${package.entity}.${table.entityName};
import ${package.vo}.${table.voName};
<#if strategy.superServiceClass?default("")?length gt 1>
import ${strategy.superServiceClass};
</#if>

/**
 * <p>${table.comment!} 服务类</p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
<#if strategy.superServiceClass?default("")?length gt 1>
public interface ${table.serviceName} extends ${strategy.superServiceClass}<${table.entityName}> {
<#else>
public interface ${table.serviceName} {
</#if>


    /**
	* 新增${table.comment!}
	*
	* @param ${table.voName?uncap_first}
	* @param loginEmployee
	*/
	${table.voName} add(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee)  throws BusinessException;

	/**
	* 获取${table.comment!}详情
	*
	* @param id
	* @param loginEmployee
	*/
	${table.voName} detail(Integer id, LoginEmployee loginEmployee)  throws BusinessException;

	/**
	* 修改${table.comment!}
	*
	* @param ${table.voName?uncap_first}
	* @param loginEmployee
	*/
	void update(${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee)  throws BusinessException;

	/**
	* 删除${table.comment!}
	*
	* @param id
	* @param loginEmployee
	*/
	void delete(Integer id, LoginEmployee loginEmployee) throws BusinessException ;

	/**
	* 获取${table.comment!}列表
	*
	* @param pageCondition
	* @param loginEmployee
	* @return
	*/
	Page<${table.voName}> list(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;
}
