package com.lee9213.mybatis.generator;

import com.lee9213.mybatis.generator.config.*;
import com.lee9213.mybatis.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 10:16
 */
@SpringBootApplication
public class Generator {

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {


        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(Generator.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF).run(args);
        GlobalConfiguration globalConfiguration = (GlobalConfiguration) applicationContext.getBean("globalConfiguration");
        DataSourceConfiguration dataSourceConfiguration = (DataSourceConfiguration) applicationContext.getBean("dataSourceConfiguration");
        StrategyConfiguration strategyConfiguration = (StrategyConfiguration) applicationContext.getBean("strategyConfiguration");
        PackageConfiguration packageConfiguration = (PackageConfiguration) applicationContext.getBean("packageConfiguration");
        TemplateConfiguration templateConfiguration = (TemplateConfiguration) applicationContext.getBean("templateConfiguration");

        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(globalConfiguration)
                .setDataSource(dataSourceConfiguration)
                .setStrategy(strategyConfiguration)
                .setPackageInfo(packageConfiguration)
                .setTemplate(templateConfiguration);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

//
////        dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
////            // 自定义数据库表字段类型转换【可选】
////            @Override
////            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
////                System.out.println("转换类型：" + fieldType);
////                // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
////                //    return DbColumnType.BOOLEAN;
////                // }
////                return super.processTypeConvert(globalConfig, fieldType);
////            }
////        });
////        // 自定义需要填充的字段
////        List<TableFill> tableFillList = new ArrayList<>();
////        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
////        mpg.setCfg(
////                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
////                new InjectionConfig() {
////                    @Override
////                    public void initMap() {
////                        Map<String, Object> map = new HashMap<>();
////                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
////                        this.setMap(map);
////                    }
////                }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(
////                        "/templates/mapper.xml" + ((1 == 1) ? ".ftl" : ".vm")) {
////                    // 自定义输出文件目录
////                    @Override
////                    public String outputFile(TableInfo tableInfo) {
////                        return "/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
////                    }
////                }))
////        );
//        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
//        System.err.println(mpg.getCfg().getMap().get("abc"));

    }
}
