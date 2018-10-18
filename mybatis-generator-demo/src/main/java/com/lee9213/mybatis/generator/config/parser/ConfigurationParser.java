package com.lee9213.mybatis.generator.config.parser;

import com.lee9213.mybatis.generator.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author libo
 * @date 2018/10/18 10:32
 */
public class ConfigurationParser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Configuration configuration;

    public ConfigurationParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parser() {
        // TODO 优化解析
        PackageInfoParser packageInfoParser = new PackageInfoParser();
        packageInfoParser.parser(configuration);

        PathInfoParser pathInfoParser = new PathInfoParser();
        pathInfoParser.parser(configuration);

        TableInfoParser tableInfoParser = new TableInfoParser();
        tableInfoParser.parser(configuration);

        TableFieldParser tableFieldParser = new TableFieldParser();
        tableFieldParser.parser(configuration);

    }

//    /**
//     * <p>
//     * 预处理配置
//     * </p>
//     *
//     * @param config 总配置信息
//     * @return 解析数据结果集
//     */
//    protected ConfigurationBuilder pretreatmentConfigBuilder(ConfigurationBuilder config) {
//        /**
//         * 表信息列表
//         */
//        List<TableInfo> tableList = this.getAllTableInfoList(config);
//
//        for (TableInfo tableInfo : tableList) {
////            /* ---------- 添加导入包 ---------- */
////            if (config.getGlobalConfig().isActiveRecord()) {
////                // 开启 ActiveRecord 模式
////                tableInfo.setImportPackages(Model.class.getCanonicalName());
////            }
//            if (tableInfo.isConvert()) {
//                // 表注解
//                tableInfo.setImportPackages(TableName.class.getCanonicalName());
//            }
////            if (config.getStrategyConfig().getLogicDeleteFieldName() != null && tableInfo.isLogicDelete(config.getStrategyConfig().getLogicDeleteFieldName())) {
////                // 逻辑删除注解
////                tableInfo.setImportPackages(TableLogic.class.getCanonicalName());
////            }
////            if (StringUtils.isNotEmpty(config.getStrategyConfig().getVersionFieldName())) {
////                // 乐观锁注解
////                tableInfo.setImportPackages(Version.class.getCanonicalName());
////            }
////            if (StringUtils.isNotEmpty(config.getSuperEntityClass())) {
////                // 父实体
////                tableInfo.setImportPackages(config.getSuperEntityClass());
////            } else {
////                tableInfo.setImportPackages(Serializable.class.getCanonicalName());
////            }
////            // Boolean类型is前缀处理
////            if (config.getStrategyConfig().isEntityBooleanColumnRemoveIsPrefix()) {
////                tableInfo.getFields().stream().filter(field -> "boolean".equalsIgnoreCase(field.getPropertyType()))
////                        .filter(field -> field.getPropertyName().startsWith("is"))
////                        .forEach(field -> field.setPropertyName(config.getStrategyConfig(),
////                                StringUtils.removePrefixAfterPrefixToLower(field.getPropertyName(), 2)));
////            }
//        }
//        return config.setTableInfoList(tableList);
//    }
}
