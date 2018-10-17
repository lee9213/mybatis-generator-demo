package com.lee9213.mybatis.generator.config.properties;

import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.NamingStrategy;
import com.lee9213.mybatis.generator.util.StringUtils;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 21:02
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "strategy")
public class StrategyProperties {

    /**
     * 是否大写命名
     */
    private boolean isCapitalMode = false;

    /**
     * 是否跳过视图
     */
    private boolean skipView = false;

    /**
     * 数据库表映射到实体的命名策略
     */
    private NamingStrategy naming = NamingStrategy.nochange;
    /**
     * 数据库表字段映射到实体的命名策略<br/>
     * 未指定按照 naming 执行
     */
    private NamingStrategy columnNaming = null;

    /**
     * 表前缀
     */
    private String[] tablePrefix;

    /**
     * 字段前缀
     */
    private String[] fieldPrefix;

    /**
     * 自定义继承的Entity类全称，带包名
     */
    private String superEntityClass;

    /**
     * 自定义基础的Entity类，公共字段
     */
    private String[] superEntityColumns;

    /**
     * 自定义继承的Mapper类全称，带包名
     */
    private String superMapperClass = Constant.SUPER_MAPPER_CLASS;

    /**
     * 自定义继承的Service类全称，带包名
     */
    private String superServiceClass = Constant.SUPER_SERVICE_CLASS;

    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    private String superServiceImplClass = Constant.SUPER_SERVICE_IMPL_CLASS;

    /**
     * 自定义继承的Controller类全称，带包名
     */
    private String superControllerClass;

    /**
     * 需要包含的表名
     */
    private String[] includeTables = null;
    /**
     * 需要包含的表名的前缀
     */
    private String[] includeTablePrefixs = null;

    /**
     * 需要排除的表名
     */
    private String[] excludeTables = null;
    /**
     * 【实体】是否生成字段常量（默认 false）<br>
     * -----------------------------------<br>
     * public static final String ID = "test_id";
     */
    private boolean entityColumnConstant = false;

    /**
     * 【实体】是否为构建者模型（默认 false）<br>
     * -----------------------------------<br>
     * public User setName(String name) { this.name = name; return this; }
     */
    private boolean entityBuilderModel = false;

    /**
     * 【实体】是否为lombok模型（默认 false）<br>
     * <a href="https://projectlombok.org/">document</a>
     */
    private boolean entityLombokModel = false;

    /**
     * Boolean类型字段是否移除is前缀（默认 false）<br>
     * 比如 : 数据库字段名称 : 'is_xxx',类型为 : tinyint. 在映射实体的时候则会去掉is,在实体类中映射最终结果为 xxx
     */
    private boolean entityBooleanColumnRemoveIsPrefix = false;
    /**
     * 生成 <code>@RestController</code> 控制器
     * <pre>
     *      <code>@Controller</code> -> <code>@RestController</code>
     * </pre>
     */
    private boolean restControllerStyle = false;
    /**
     * 驼峰转连字符
     * <pre>
     *      <code>@RequestMapping("/managerUserActionHistory")</code> -> <code>@RequestMapping("/manager-user-action-history")</code>
     * </pre>
     */
    private boolean controllerMappingHyphenStyle = false;

    /**
     * 是否生成实体时，生成字段注解
     */
    private boolean entityTableFieldAnnotationEnable = false;
    /**
     * 乐观锁属性名称
     */
    private String versionFieldName;

    /**
     * 逻辑删除属性名称
     */
    private String logicDeleteFieldName;
//
//    /**
//     * 表填充字段
//     */
//    private List<TableFill> tableFillList = null;

    /**
     * <p>
     * 大写命名、字段符合大写字母数字下划线命名
     * </p>
     *
     * @param word 待判断字符串
     * @return
     */
    public boolean isCapitalModeNaming(String word) {
        return isCapitalMode && StringUtils.isCapitalMode(word);
    }

    /**
     * <p>
     * 表名称包含指定前缀
     * </p>
     *
     * @param tableName 表名称
     * @return
     */
    public boolean containsTablePrefix(String tableName) {
        if (null != tableName) {
            String[] tps = getTablePrefix();
            if (null != tps) {
                for (String tp : tps) {
                    if (tableName.contains(tp)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCapitalMode() {
        return isCapitalMode;
    }

    public StrategyProperties setCapitalMode(boolean isCapitalMode) {
        this.isCapitalMode = isCapitalMode;
        return this;
    }

    public boolean isSkipView() {
        return skipView;
    }

    public StrategyProperties setSkipView(boolean skipView) {
        this.skipView = skipView;
        return this;
    }

    public NamingStrategy getNaming() {
        return naming;
    }

    public StrategyProperties setNaming(NamingStrategy naming) {
        this.naming = naming;
        return this;
    }

    public NamingStrategy getColumnNaming() {
        if (null == columnNaming) {
            // 未指定以 naming 策略为准
            return naming;
        }
        return columnNaming;
    }

    public StrategyProperties setColumnNaming(NamingStrategy columnNaming) {
        this.columnNaming = columnNaming;
        return this;
    }

    public String[] getTablePrefix() {
        return tablePrefix;
    }

    public StrategyProperties setTablePrefix(String... tablePrefix) {
        this.tablePrefix = tablePrefix;
        return this;
    }

    public String getSuperEntityClass() {
        return superEntityClass;
    }

    public StrategyProperties setSuperEntityClass(String superEntityClass) {
        this.superEntityClass = superEntityClass;
        return this;
    }

    public boolean includeSuperEntityColumns(String fieldName) {
        if (null != superEntityColumns) {
            for (String column : superEntityColumns) {
                if (column.equals(fieldName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String[] getSuperEntityColumns() {
        return superEntityColumns;
    }

    public StrategyProperties setSuperEntityColumns(String... superEntityColumns) {
        this.superEntityColumns = superEntityColumns;
        return this;
    }

    public String getSuperMapperClass() {
        return superMapperClass;
    }

    public StrategyProperties setSuperMapperClass(String superMapperClass) {
        this.superMapperClass = superMapperClass;
        return this;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public StrategyProperties setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
        return this;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public StrategyProperties setSuperServiceImplClass(String superServiceImplClass) {
        this.superServiceImplClass = superServiceImplClass;
        return this;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    public StrategyProperties setSuperControllerClass(String superControllerClass) {
        this.superControllerClass = superControllerClass;
        return this;
    }

    public String[] getIncludeTables() {
        return includeTables;
    }

    public StrategyProperties setIncludeTables(String... includeTables) {
        this.includeTables = includeTables;
        return this;
    }

    public String[] getIncludeTablePrefixs() {
        return includeTablePrefixs;
    }

    public StrategyProperties setIncludeTablePrefixs(String... includeTablePrefixs) {
        this.includeTablePrefixs = includeTablePrefixs;
        return this;
    }

    public String[] getExcludeTables() {
        return excludeTables;
    }

    public StrategyProperties setExcludeTables(String... excludeTables) {
        this.excludeTables = excludeTables;
        return this;
    }
    public StrategyProperties setExcludeTabless(String[] excludeTables) {
        this.excludeTables = excludeTables;
        return this;
    }

    public boolean isEntityColumnConstant() {
        return entityColumnConstant;
    }

    public StrategyProperties setEntityColumnConstant(boolean entityColumnConstant) {
        this.entityColumnConstant = entityColumnConstant;
        return this;
    }

    public boolean isEntityBuilderModel() {
        return entityBuilderModel;
    }

    public StrategyProperties setEntityBuilderModel(boolean entityBuilderModel) {
        this.entityBuilderModel = entityBuilderModel;
        return this;
    }

    public boolean isEntityLombokModel() {
        return entityLombokModel;
    }

    public StrategyProperties setEntityLombokModel(boolean entityLombokModel) {
        this.entityLombokModel = entityLombokModel;
        return this;
    }

    public boolean isEntityBooleanColumnRemoveIsPrefix() {
        return entityBooleanColumnRemoveIsPrefix;
    }

    public StrategyProperties setEntityBooleanColumnRemoveIsPrefix(boolean entityBooleanColumnRemoveIsPrefix) {
        this.entityBooleanColumnRemoveIsPrefix = entityBooleanColumnRemoveIsPrefix;
        return this;
    }

    public boolean isRestControllerStyle() {
        return restControllerStyle;
    }

    public StrategyProperties setRestControllerStyle(boolean restControllerStyle) {
        this.restControllerStyle = restControllerStyle;
        return this;
    }

    public boolean isControllerMappingHyphenStyle() {
        return controllerMappingHyphenStyle;
    }

    public StrategyProperties setControllerMappingHyphenStyle(boolean controllerMappingHyphenStyle) {
        this.controllerMappingHyphenStyle = controllerMappingHyphenStyle;
        return this;
    }

    public String getLogicDeleteFieldName() {
        return logicDeleteFieldName;
    }

    /**
     * 设置逻辑删除字段
     *
     * @param logicDeleteFieldName 数据库字段
     * @return
     */
    public StrategyProperties setLogicDeleteFieldName(String logicDeleteFieldName) {
        this.logicDeleteFieldName = logicDeleteFieldName;
        return this;
    }

    public String getVersionFieldName() {
        return versionFieldName;
    }

    /**
     * 设置乐观锁字段
     *
     * @param versionFieldName 数据库字段
     * @return
     */
    public StrategyProperties setVersionFieldName(String versionFieldName) {
        this.versionFieldName = versionFieldName;
        return this;
    }

//    public List<TableFill> getTableFillList() {
//        return tableFillList;
//    }
//
//    public StrategyConfiguration setTableFillList(List<TableFill> tableFillList) {
//        this.tableFillList = tableFillList;
//        return this;
//    }

    public String[] getFieldPrefix() {
        return fieldPrefix;
    }

    public StrategyProperties setFieldPrefix(String... fieldPrefixs) {
        this.fieldPrefix = fieldPrefixs;
        return this;
    }

    public StrategyProperties entityTableFieldAnnotationEnable(boolean isEnableAnnotation) {
        entityTableFieldAnnotationEnable = isEnableAnnotation;
        return this;
    }

    public boolean isEntityTableFieldAnnotationEnable() {
        return entityTableFieldAnnotationEnable;
    }
}