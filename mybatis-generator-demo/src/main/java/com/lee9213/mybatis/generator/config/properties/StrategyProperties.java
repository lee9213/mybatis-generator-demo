package com.lee9213.mybatis.generator.config.properties;

import com.lee9213.mybatis.generator.util.CollectionUtils;
import com.lee9213.mybatis.generator.util.StringUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>策略配置信息</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 21:02
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "strategy")
@Data
@Accessors(chain = true)
public class StrategyProperties {

    /**
     * 表前缀
     */
    private List<String> tablePrefix;

    /**
     * 字段前缀
     */
    private List<String> fieldPrefix;

    /**
     * 需要包含的表名
     */
    private List<String> includeTables = null;
    /**
     * 需要包含的表名的前缀
     */
    private List<String> includeTablePrefixs = null;

    /**
     * 需要排除的表名
     */
    private List<String> excludeTables = null;

    /**
     * 数据库表映射到实体的命名策略，是否转换成驼峰
     */
    private String underlineToCamelTableName = "true";
    /**
     * 数据库表字段映射到实体的命名，是否转换成驼峰
     */
    private String underlineToCamelColumnNames = "true";

    /**
     * 自定义继承的Entity类全称，带包名
     */
    private String superEntityClass;

    /**
     * 自定义继承的Entity类全称，带包名
     */
    private String superVOClass;

    /**
     * 自定义基础的Entity类，公共字段
     */
    private List<String> superEntityColumns;

    /**
     * 自定义继承的Mapper类全称，带包名
     */
    private String superMapperClass;

    /**
     * 自定义继承的Service类全称，带包名
     */
    private String superServiceClass;

    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    private String superServiceImplClass;

    /**
     * 自定义继承的Controller类全称，带包名
     */
    private String superControllerClass;

    /**
     * 【实体】setter方法是否为构建者模型（默认 false）<br>
     * -----------------------------------<br>
     * for example : public User setName(String name) { this.name = name; return this; }
     */
    private boolean entityBuilderModel = false;

    /**
     * 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
     */
    private boolean entityLombokModel = false;

    /**
     * 生成 <code>@RestController</code> 控制器
     * <pre>
     *  <code>@Controller</code> -> <code>@RestController</code>
     * </pre>
     */
    private boolean restControllerStyle = false;

    /**
     * 逻辑删除属性名称
     */
    private String logicDeleteFieldName;

    public boolean includeSuperEntityColumns(String fieldName) {
        if(StringUtils.isNotEmpty(fieldName) && CollectionUtils.isNotEmpty(superEntityColumns)) {
            return superEntityColumns.stream()
                    .map(key -> key.toLowerCase())
                    .collect(Collectors.toList())
                    .contains(fieldName.toLowerCase());
        }
        return false;
    }
}
