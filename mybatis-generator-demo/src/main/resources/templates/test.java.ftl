package ${package.test};

import com.alibaba.fastjson.JSONObject;
import ${package.service}.${table.serviceName};
import ${package.entity}.${table.entityName};
import ${package.vo}.${table.voName};
import org.junit.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
<#list table.importPackages as pkg>
import ${pkg};
</#list>
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * <p>${table.comment!}</p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterServerApplication.class)
@AutoConfigureMockMvc
public class ${table.testName} {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test(){
        testCreate${table.entityName}();
        testSelect${table.entityName}();
        testUpdate${table.entityName}();
        testSelectByPageCondition${table.entityName}();
        testDelete${table.entityName}();
    }

    public void testCreate${table.entityName}(){
		${table.voName} ${table.voName?uncap_first} = new ${table.voName}();
	<#list table.fields as field>
		${table.voName?uncap_first}.set${field.propertyName?cap_first}();
	</#list>
    <#list table.commonFields as field>
        ${table.voName?uncap_first}.set${field.propertyName?cap_first}();
    </#list>
        ${table.entityName} ${table.entityName?uncap_first} = ${table.serviceName?uncap_first}.add(${table.voName?uncap_first});
        id = ${table.entityName?uncap_first}.getId();
        logger.info("添加成功【{}】",JSONObject.toJSONString(${table.entityName?uncap_first}));
    }

    public void testSelect${table.entityName}() {
        ${table.voName} ${table.voName?uncap_first} = ${table.serviceName?uncap_first}.detail(id);
        logger.info("查询成功【{}】",JSONObject.toJSONString(${table.voName?uncap_first}));
    }

    public void testUpdate${table.entityName}() {
        ${table.voName} ${table.voName?uncap_first} = ${table.serviceName?uncap_first}.detail(id);
        logger.info("更新之前【{}】",JSONObject.toJSONString(${table.voName?uncap_first}));

        ${table.voName} new${table.voName} = new ${table.voName}();
        ${table.serviceName?uncap_first}.update(new${table.voName});

        ${table.voName?uncap_first} = ${table.serviceName?uncap_first}.detail(id);
        logger.info("更新之后【{}】",JSONObject.toJSONString(${table.voName?uncap_first}));
    }

    public void testDelete${table.entityName}() {
        ${table.voName} ${table.voName?uncap_first} = ${table.serviceName?uncap_first}.detail(id);
        logger.info("删除之前【{}】",JSONObject.toJSONString(${table.voName?uncap_first}));

        ${table.serviceName?uncap_first}.delete(id);

        ${table.voName?uncap_first} = ${table.serviceName?uncap_first}.detail(id);
        logger.info("删除之后【{}】",JSONObject.toJSONString(${table.voName?uncap_first}));
    }

    public void testSelectByPageCondition${table.entityName}() {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(10);
        LoginEmployee loginEmployee = new LoginEmployee();
        Page<${table.voName}> page = ${table.serviceName?uncap_first}.list(pageCondition, loginEmployee);
        logger.info("分页信息【{}】",JSONObject.toJSONString(page));
    }
}
