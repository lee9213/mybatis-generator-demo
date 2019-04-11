package ${package.service};

import ${global.exceptionPackage};
import com.lee9213.als.core.base.PageInfo;
<#if strategy.superServiceClass?default("")?length gt 1>
import ${strategy.superServiceClass};
</#if>
import ${package.entity}.${table.entityName};
import ${package.vo}.${table.voName};

/**
 * <p>${table.comment!} 服务类</p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
<#if strategy.superServiceClass?default("")?length gt 1>
public interface ${table.serviceName} extends ${strategy.superServiceClass?substring(strategy.superServiceClass?last_index_of(".")+1)}<${table.entityName}> {
<#else>
public interface ${table.serviceName} {
</#if>

	/**
   * 获取${table.comment!}列表
   *
   * @param pageNum 页码
   * @param pageSize 每页显示条数
	 * @throws ${global.exceptionName} 统一异常
	 */
	 PageInfo<${table.voName}> page(Long pageNum, Long pageSize) throws ${global.exceptionName};

}
