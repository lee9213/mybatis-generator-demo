package ${package.service};

import ${package.entity}.${table.entityName};
import ${package.vo}.${table.voName};
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${global.exceptionPackage};
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
	* @param ${table.voName?uncap_first} ${table.comment!}
	* @return ${table.comment!}
	* @throws ${global.exceptionName} 统一异常
	*/
	int add(${table.voName} ${table.voName?uncap_first}) throws ${global.exceptionName};

	/**
	* 获取${table.comment!}详情
	*
	* @param id ${table.comment!}ID
	* @return ${table.comment!}
	* @throws ${global.exceptionName} 统一异常
	*/
	${table.voName} detail(Long id) throws ${global.exceptionName};

	/**
	* 修改${table.comment!}
	*
	* @param ${table.voName?uncap_first} ${table.comment!}
	* @return 修改记录数
	* @throws ${global.exceptionName} 统一异常
	*/
	int update(${table.voName} ${table.voName?uncap_first}) throws ${global.exceptionName};

	/**
	* 删除${table.comment!}
	*
	* @param id ${table.comment!}ID
	* @return 删除记录数
	* @throws ${global.exceptionName} 统一异常
	*/
	int delete(Long id) throws ${global.exceptionName};

	/**
     * 获取${table.comment!}列表
	 * @throws ${global.exceptionName} 统一异常
     */
    PageInfo<${table.voName}> pageByCondition() throws ${global.exceptionName};

}
