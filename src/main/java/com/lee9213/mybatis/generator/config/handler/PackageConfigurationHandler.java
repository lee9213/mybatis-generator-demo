package com.lee9213.mybatis.generator.config.handler;

import com.google.common.collect.Maps;
import com.lee9213.mybatis.generator.config.GlobalConfiguration;
import com.lee9213.mybatis.generator.config.PackageConfiguration;
import com.lee9213.mybatis.generator.config.TemplateConfiguration;
import com.lee9213.mybatis.generator.config.builder.ConfigurationBuilder;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.StringPool;
import com.lee9213.mybatis.generator.util.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.util.Map;

/**
 * @author libo
 * @version 1.0
 * @date 2018/10/15 16:06
 */
public class PackageConfigurationHandler implements ConfigurationHandler {

    @Override
    public void handler(ConfigurationBuilder configBuilder) {

        GlobalConfiguration globalConfiguration = configBuilder.getGlobalConfiguration();
        PackageConfiguration packageConfiguration = configBuilder.getPackageConfiguration();
        TemplateConfiguration templateConfiguration = configBuilder.getTemplateConfiguration();
        // 包信息
        Map<String, String> packageInfo = Maps.newHashMapWithExpectedSize(8);
        packageInfo.put(Constant.GENERATOR, "");
        packageInfo.put(Constant.MODULE_NAME, packageConfiguration.getModuleName());
        packageConfiguration.setParent(joinPackage(packageConfiguration.getParent(),packageConfiguration.getModuleName()));
        packageInfo.put(Constant.ENTITY, joinPackage(packageConfiguration.getParent(), packageConfiguration.getEntity()));
        packageInfo.put(Constant.MAPPER, joinPackage(packageConfiguration.getParent(), packageConfiguration.getMapper()));
        packageInfo.put(Constant.XML, joinPackage(packageConfiguration.getParent(), packageConfiguration.getXml()));
        packageInfo.put(Constant.SERVICE, joinPackage(packageConfiguration.getParent(), packageConfiguration.getService()));
        packageInfo.put(Constant.SERVICE_IMPL, joinPackage(packageConfiguration.getParent(), packageConfiguration.getServiceImpl()));
        packageInfo.put(Constant.CONTROLLER, joinPackage(packageConfiguration.getParent(), packageConfiguration.getController()));
        configBuilder.setPackageInfo(packageInfo);
        // 自定义路径
        Map<String, String> pathInfo = packageConfiguration.getPathInfo();
        if (pathInfo == null) {
            pathInfo = Maps.newHashMapWithExpectedSize(8);
            // 生成路径信息
            // TODO 优化路径
            String outputDir = globalConfiguration.getOutputDir();
            setPathInfo(pathInfo, templateConfiguration.getGenerator(), outputDir + "\\src\\main\\resources", Constant.GENERATOR_PATH, packageInfo.get(Constant.GENERATOR));
            setPathInfo(pathInfo, templateConfiguration.getEntity(), outputDir + "\\src\\main\\java", Constant.ENTITY_PATH, packageInfo.get(Constant.ENTITY));
            setPathInfo(pathInfo, templateConfiguration.getMapper(), outputDir + "\\src\\main\\java", Constant.MAPPER_PATH, packageInfo.get(Constant.MAPPER));
            setPathInfo(pathInfo, templateConfiguration.getXml(), outputDir + "\\src\\main\\resources", Constant.XML_PATH, packageInfo.get(Constant.XML));
            setPathInfo(pathInfo, templateConfiguration.getService(), outputDir + "\\src\\main\\java", Constant.SERVICE_PATH, packageInfo.get(Constant.SERVICE));
            setPathInfo(pathInfo, templateConfiguration.getServiceImpl(), outputDir + "\\src\\main\\java", Constant.SERVICE_IMPL_PATH, packageInfo.get(Constant.SERVICE_IMPL));
            setPathInfo(pathInfo, templateConfiguration.getController(), outputDir + "\\src\\main\\java", Constant.CONTROLLER_PATH, packageInfo.get(Constant.CONTROLLER));
            configBuilder.setPathInfo(pathInfo);
        }
    }

    /**
     * <p>
     * 连接父子包名
     * </p>
     *
     * @param parent     父包名
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    private String joinPackage(String parent, String subPackage) {
        if (Strings.isEmpty(parent)) {
            return subPackage;
        }
        return parent + StringPool.DOT + subPackage;
    }

    private void setPathInfo(Map<String, String> pathInfo, String template, String outputDir, String path, String packageInfo) {
        if (Strings.isNotEmpty(template)) {
            pathInfo.put(path, joinPath(outputDir, packageInfo));
        }
    }

    /**
     * <p>
     * 连接路径字符串
     * </p>
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (StringUtils.isEmpty(parentDir)) {
            parentDir = System.getProperty(Constant.JAVA_TMPDIR);
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        return parentDir + packageName;
    }
}
