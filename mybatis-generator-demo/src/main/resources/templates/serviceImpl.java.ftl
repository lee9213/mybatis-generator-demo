package ${package.serviceImpl};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${global.exceptionPackage};
import com.lee9213.als.core.base.PageInfo;
<#if strategy.superServiceImplClass?default("")?length gt 1>
  import ${strategy.superServiceImplClass};
</#if>
import ${package.convert}.${table.convertName};
import ${package.entity}.${table.entityName};
import ${package.vo}.${table.voName};
import ${package.mapper}.${table.mapperName};
import ${package.service}.${table.serviceName};
import ${global.exceptionCodePackage};
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public PageInfo<${table.voName}> pageByCondition() throws ${global.exceptionName} {
        IPage<${table.entityName}> ${table.entityName?uncap_first}PageInfo = baseMapper.page(new Page(pageNum, pageSize));
        return ${table.convertName}.convertPage(${table.entityName?uncap_first}PageInfo);
    }
}