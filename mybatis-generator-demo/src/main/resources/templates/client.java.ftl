package ${package.client};

import ${global.exceptionPackage};
import com.lee9213.als.common.vo.Result;
import com.lee9213.als.core.base.vo.request.PageRequest;
import ${package.vo}.${table.voName};
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
public interface ${table.clientName} {

    /**
     * 创建${table.comment!}
     *
     * @param ${table.voName?uncap_first} ${table.comment!}
     * @return true or false
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody ${table.voName} ${table.voName?uncap_first});

    /**
     * 获取${table.comment!}详情
     *
     * @param id ID
     * @return ${table.comment!}
     */
    @GetMapping(value = "/{id}/detail")
    Result detail(@PathVariable Long id);

    /**
     * 修改${table.comment!}
     *
     * @param ${table.voName?uncap_first} ${table.comment!}
     * @return true or false

     */
    @PutMapping(value = "/update")
    Result update(@RequestBody ${table.voName} ${table.voName?uncap_first});

    /**
     * 删除${table.comment!}
     *
     * @param id ID
     * @return true or false

     */
    @DeleteMapping(value = "/{id}/delete")
    Result delete(@PathVariable Long id);

    /**
     * 获取${table.comment!}列表
     *
     * @param pageRequest 分页请求对象
     * @return 分页信息
     */
    @PostMapping(value = "/page")
    Result page(@RequestBody PageRequest pageRequest);

}

