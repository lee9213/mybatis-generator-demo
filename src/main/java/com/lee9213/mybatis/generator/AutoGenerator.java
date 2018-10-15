package com.lee9213.mybatis.generator;

import ch.qos.logback.classic.db.names.TableName;
import com.lee9213.mybatis.generator.config.*;
import com.lee9213.mybatis.generator.config.builder.ConfigurationBuilder;
import com.lee9213.mybatis.generator.config.po.TableInfo;
import com.lee9213.mybatis.generator.engine.AbstractTemplateEngine;
import com.lee9213.mybatis.generator.engine.FreemarkerTemplateEngine;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-14 0:07
 */
@Setter
@Accessors(chain = true)
public class AutoGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 数据源配置
     */
    private DataSourceConfiguration dataSourceConfiguration;
    /**
     * 数据库表配置
     */
    private StrategyConfiguration strategyConfiguration;
    /**
     * 包 相关配置
     */
    private PackageConfiguration packageConfiguration;
    /**
     * 模板 相关配置
     */
    private TemplateConfiguration templateConfiguration;
    /**
     * 全局 相关配置
     */
    private GlobalConfiguration globalConfiguration;
    /**
     * 模板引擎
     */
    private AbstractTemplateEngine templateEngine;

    /**
     * 生成代码
     */
    public void execute() {
        logger.info("==========================准备生成文件...==========================");
        // 初始化配置
        ConfigurationBuilder config = new ConfigurationBuilder(globalConfiguration, templateConfiguration, packageConfiguration, dataSourceConfiguration, strategyConfiguration);
        if (null == templateEngine) {
            // 采用 Freemarker 引擎 【 默认 】
            templateEngine = new FreemarkerTemplateEngine();
        }

        // 模板引擎初始化执行文件输出
        templateEngine.init(this.pretreatmentConfigBuilder(config)).mkdirs().batchOutput().open();
        logger.info("==========================文件生成完成！！！==========================");
    }

    /**
     * <p>
     * 开放表信息、预留子类重写
     * </p>
     *
     * @param config 配置信息
     * @return
     */
    protected List<TableInfo> getAllTableInfoList(ConfigurationBuilder config) {
        return config.getTableInfoList();
    }

    /**
     * <p>
     * 预处理配置
     * </p>
     *
     * @param config 总配置信息
     * @return 解析数据结果集
     */
    protected ConfigurationBuilder pretreatmentConfigBuilder(ConfigurationBuilder config) {
        /**
         * 表信息列表
         */
        List<TableInfo> tableList = this.getAllTableInfoList(config);

        for (TableInfo tableInfo : tableList) {
//            /* ---------- 添加导入包 ---------- */
//            if (config.getGlobalConfig().isActiveRecord()) {
//                // 开启 ActiveRecord 模式
//                tableInfo.setImportPackages(Model.class.getCanonicalName());
//            }
            if (tableInfo.isConvert()) {
                // 表注解
                tableInfo.setImportPackages(TableName.class.getCanonicalName());
            }
//            if (config.getStrategyConfig().getLogicDeleteFieldName() != null && tableInfo.isLogicDelete(config.getStrategyConfig().getLogicDeleteFieldName())) {
//                // 逻辑删除注解
//                tableInfo.setImportPackages(TableLogic.class.getCanonicalName());
//            }
//            if (StringUtils.isNotEmpty(config.getStrategyConfig().getVersionFieldName())) {
//                // 乐观锁注解
//                tableInfo.setImportPackages(Version.class.getCanonicalName());
//            }
//            if (StringUtils.isNotEmpty(config.getSuperEntityClass())) {
//                // 父实体
//                tableInfo.setImportPackages(config.getSuperEntityClass());
//            } else {
//                tableInfo.setImportPackages(Serializable.class.getCanonicalName());
//            }
//            // Boolean类型is前缀处理
//            if (config.getStrategyConfig().isEntityBooleanColumnRemoveIsPrefix()) {
//                tableInfo.getFields().stream().filter(field -> "boolean".equalsIgnoreCase(field.getPropertyType()))
//                        .filter(field -> field.getPropertyName().startsWith("is"))
//                        .forEach(field -> field.setPropertyName(config.getStrategyConfig(),
//                                StringUtils.removePrefixAfterPrefixToLower(field.getPropertyName(), 2)));
//            }
        }
        return config.setTableInfoList(tableList);
    }
}
