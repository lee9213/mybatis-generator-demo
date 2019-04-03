package ${package.client};

import ${global.exceptionPackage};
import com.lee9213.als.common.vo.Result;
import ${package.vo}.${table.voName};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * <p>${table.comment!}</p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
@RequestMapping("/${table.name?replace("_","/")}")
<#if global.swagger2>
@Api(value = "/${table.name?replace("_","/")}", description = "${table.comment!}")
</#if>
public interface ${table.clientName} {

    /**
     * 创建${table.comment!}
     *
     * @param ${table.voName?uncap_first} ${table.comment!}
     * @return true or false
     * @throws BusinessException 统一异常
     */
    @PostMapping(value = "/save")
    <#if global.swagger2>
    @ApiOperation(value = "创建${table.comment!}")</#if>
    Result save(@RequestBody ${table.voName} ${table.voName?uncap_first}) throws BusinessException;

    /**
     * 获取${table.comment!}详情
     *
     * @param id ID
     * @return ${table.comment!}
     * @throws BusinessException 统一异常
     */
    @GetMapping(value = "/{id}/detail")
    <#if global.swagger2>
    @ApiOperation(value = "获取${table.comment!}详情",response = ${table.voName}.class)</#if>
    Result detail(@PathVariable Long id) throws BusinessException;

    /**
     * 修改${table.comment!}
     *
     * @param ${table.voName?uncap_first} ${table.comment!}
     * @return true or false
     * @throws BusinessException 统一异常
     */
    @PutMapping(value = "/update")
    <#if global.swagger2>
    @ApiOperation(value = "修改${table.comment!}")</#if>
    Result update(@RequestBody ${table.voName} ${table.voName?uncap_first}) throws BusinessException;


    /**
     * 删除${table.comment!}
     *
     * @param id ID
     * @return true or false
     * @throws BusinessException 统一异常
     */
    @DeleteMapping(value = "/{id}/delete")
    <#if global.swagger2>
    @ApiOperation(value = "删除${table.comment!}")</#if>
    Result delete(@PathVariable Long id) throws BusinessException;


    /**
     * 获取${table.comment!}列表
     *
     * @param pageCondition
     * @return
     */
    @PostMapping(value = "/list")
    <#if global.swagger2>
    @ApiOperation(value = "分页查询${table.comment!}",response = ${table.voName}.class, responseContainer = "List")</#if>
    public Result list(@RequestBody PageCondition pageCondition) throws BusinessException;

}

