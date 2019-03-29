package ${package.service};

import com.lee9213.als.common.exception.BusinessException;
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
	* 添加${table.comment!}
	*
	* @param ${table.voName?uncap_first}
	*/
	${table.voName} add(${table.voName} ${table.voName?uncap_first})  throws BusinessException;

	/**
	* 获取${table.comment!}详情
	*
	* @param id
	*/
	${table.voName} detail(Integer id)  throws BusinessException;

	/**
	* 修改${table.comment!}
	*
	* @param ${table.voName?uncap_first}
	*/
	void update(${table.voName} ${table.voName?uncap_first})  throws BusinessException;

	/**
	* 删除${table.comment!}
	*
	* @param id
	*/
	void delete(Integer id) throws BusinessException ;

	/**
	* 获取${table.comment!}列表
	*
	* @param pageCondition
	* @return
	*/
	Page<${table.voName}> list(PageCondition pageCondition) throws BusinessException;
}
