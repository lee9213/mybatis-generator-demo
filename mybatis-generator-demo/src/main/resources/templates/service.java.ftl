package ${package.service};

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import ${package.entity}.${table.entityName};
<#if strategy.superServiceClass?default("")?length gt 1>
import ${strategy.superServiceClass};
</#if>

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
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
	*  add 新增接口
	*   @param  scmTransactionSourceVO
	* @param loginEmployee
	*/
	${table.entityName} add(${table.entityName}VO scmTransactionSourceVO, LoginEmployee loginEmployee)  throws BusinessException;

	/**
	* detail 详情接口
	*   @param  id
	* @param loginEmployee
	*/
	${table.entityName}VO detail(Integer id, LoginEmployee loginEmployee)  throws BusinessException;

	/**
	* update 更新接口
	*   @param  scmTransactionSourceVO
	* @param loginEmployee
	*/
	void update(${table.entityName}VO scmTransactionSourceVO, LoginEmployee loginEmployee)  throws BusinessException;

	/**
	*  delete  删除接口
	*   @param  id
	* @param loginEmployee
	*/
	void delete(Integer id, LoginEmployee loginEmployee) throws BusinessException ;

	/**
	* 列表
	* @param pageCondition
	* @param loginEmployee
	* @return
	*/
	Page<${table.entityName}VO > list(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;
}
