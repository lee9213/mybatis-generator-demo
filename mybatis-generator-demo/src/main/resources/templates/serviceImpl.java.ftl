package ${package.serviceImpl};

import ${package.entity}.${table.entityName};
import ${package.mapper}.${table.mapperName};
import ${package.service}.${table.serviceName};
import ${package.vo}.${table.voName};
import ${package.convert}.${table.convertName};
import ${global.exceptionPackage};
import ${global.exceptionCodePackage};
import lombok.extern.slf4j.Slf4j;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<#if strategy.superServiceImplClass?default("")?length gt 1>
import ${strategy.superServiceImplClass};
</#if>

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public int add(${table.voName} ${table.voName?uncap_first}) throws ${global.exceptionName} {
        if (Objects.isNull(${table.voName?uncap_first})) {
            throw new ${global.exceptionName}(${global.exceptionCodeName}.PARAM_NULL, "产品信息");
        }
        ${table.entityName} ${table.entityName?uncap_first} = ${table.convertName}.convert(${table.voName?uncap_first});
        return ${table.mapperName?uncap_first}.insertSelective(${table.entityName?uncap_first});
    }

    @Override
    public ${table.voName} detail(Long id) throws ${global.exceptionName} {
        if (Objects.isNull(id)) {
            throw new ${global.exceptionName}(${global.exceptionCodeName}.PARAM_NULL, "产品ID");
        }
        ${table.entityName} ${table.entityName?uncap_first} = ${table.mapperName?uncap_first}.selectByPrimaryKey(id);
        if (Objects.isNull(${table.entityName?uncap_first})) {
            return new ${table.voName}();
        }
        ${table.voName} ${table.voName?uncap_first} = ${table.convertName}.convert(${table.entityName?uncap_first});
        return ${table.voName?uncap_first};
    }

    @Override
    public int update(${table.voName} ${table.voName?uncap_first}) throws ${global.exceptionName} {
        if (Objects.isNull(${table.voName?uncap_first}) || Objects.isNull(${table.voName?uncap_first}.getId())) {
            throw new ${global.exceptionName}(${global.exceptionCodeName}.PARAM_NULL, "产品信息");
        }

        ${table.entityName} ${table.entityName?uncap_first} = ${table.convertName}.convert(${table.voName?uncap_first})
                .setModifyUser()
                .setModifyTime(new Date());
        return ${table.mapperName?uncap_first}.updateByPrimaryKeySelective(${table.entityName?uncap_first});
    }

    @Override
    public int delete(Long id) throws ${global.exceptionName} {
        if (Objects.isNull(id)) {
            throw new ${global.exceptionName}(${global.exceptionCodeName}.PARAM_NULL, "产品ID");
        }
        <#if table.isLogicDelete>
        ${table.entityName} ${table.entityName?uncap_first} = ${table.mapperName?uncap_first}.selectByPrimaryKey(id);
        if (Objects.isNull(${table.entityName?uncap_first})) {
            throw new ${global.exceptionName}("delete.${table.entityName}.error", "数据异常");
        }
        ${table.mapperName?uncap_first}.updateByPrimaryKeySelective(new ${table.entityName}()
                .setId(${table.entityName?uncap_first}.getId())
                .setLastUpdateUserId(loginEmployee.getUserId())
                .setLastUpdateTime(new Date())
                .setIsDelete(true));
        <#else>
        return ${table.mapperName?uncap_first}.deleteByPrimaryKey(id);
        </#if>

    }

    @Override
    public PageInfo<${table.voName}> pageByCondition() throws ${global.exceptionName} {
        PageHelper.startPage(pageSize, pageNum);
        PageInfo<${table.entityName}> ${table.entityName?uncap_first}PageInfo = productInfoMapper.pageByCondition(productName, financDay);
        return ${table.entityName}Convert.convertPage(${table.entityName?uncap_first}PageInfo);
    }
}