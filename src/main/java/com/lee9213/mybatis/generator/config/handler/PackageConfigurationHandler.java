package com.lee9213.mybatis.generator.config.handler;

import com.google.common.base.Strings;
import com.lee9213.mybatis.generator.config.builder.ConfigurationBuilder;
import com.lee9213.mybatis.generator.config.domain.PackageInfo;
import com.lee9213.mybatis.generator.config.domain.PathInfo;
import com.lee9213.mybatis.generator.config.properties.GlobalProperties;
import com.lee9213.mybatis.generator.config.properties.PackageProperties;
import com.lee9213.mybatis.generator.config.properties.TemplateProperties;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.StringPool;
import com.lee9213.mybatis.generator.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author libo
 * @version 1.0
 * @date 2018/10/15 16:06
 */
public class PackageConfigurationHandler implements ConfigurationHandler {
    protected static final Logger logger = LoggerFactory.getLogger(PackageConfigurationHandler.class);

    @Override
    public void handler(ConfigurationBuilder configBuilder) {

        GlobalProperties globalConfiguration = configBuilder.getGlobalProperties();
        PackageProperties packageConfiguration = configBuilder.getPackageProperties();
        TemplateProperties templateConfiguration = configBuilder.getTemplateProperties();
        // 包信息
        packageConfiguration.setParent(joinPackage(packageConfiguration.getParent(), packageConfiguration.getModuleName()));
        PackageInfo packageInfo = new PackageInfo()
                .setModuleName(packageConfiguration.getModuleName())
                .setEntity(joinPackage(packageConfiguration.getParent(), packageConfiguration.getEntity()))
                .setMapper(joinPackage(packageConfiguration.getParent(), packageConfiguration.getMapper()))
                .setMapperXml(joinPackage(packageConfiguration.getParent(), packageConfiguration.getMapperXml()))
                .setService(joinPackage(packageConfiguration.getParent(), packageConfiguration.getService()))
                .setServiceImpl(joinPackage(packageConfiguration.getParent(), packageConfiguration.getServiceImpl()))
                .setController(joinPackage(packageConfiguration.getParent(), packageConfiguration.getController()));
        configBuilder.setPackageInfo(packageInfo);

        // 生成路径信息
        // TODO 优化路径
        String outputDir = globalConfiguration.getOutputDir();
        PathInfo pathInfo = new PathInfo()
                .setGeneratorPath(getPathInfo(templateConfiguration.getGenerator(), outputDir + "\\src\\main\\resources", packageInfo.getGenerator()))
                .setEntityPath(getPathInfo(templateConfiguration.getEntity(), outputDir + "\\src\\main\\java", packageInfo.getEntity()))
                .setMapperPath(getPathInfo(templateConfiguration.getMapper(), outputDir + "\\src\\main\\java", packageInfo.getMapper()))
                .setMapperXmlPath(getPathInfo(templateConfiguration.getXml(), outputDir + "\\src\\main\\resources", packageInfo.getMapperXml()))
                .setServicePath(getPathInfo(templateConfiguration.getService(), outputDir + "\\src\\main\\java", packageInfo.getService()))
                .setServiceImplPath(getPathInfo(templateConfiguration.getServiceImpl(), outputDir + "\\src\\main\\java", packageInfo.getServiceImpl()))
                .setControllerPath(getPathInfo(templateConfiguration.getController(), outputDir + "\\src\\main\\java", packageInfo.getController()));
        configBuilder.setPathInfo(pathInfo);

        mkdir(pathInfo.getGeneratorPath());
        mkdir(pathInfo.getEntityPath());
        mkdir(pathInfo.getMapperPath());
        mkdir(pathInfo.getMapperXmlPath());
        mkdir(pathInfo.getServicePath());
        mkdir(pathInfo.getServiceImplPath());
        mkdir(pathInfo.getControllerPath());
    }
    /**
     * <p>
     * 处理输出目录
     * </p>
     */
    private void mkdir(String path){
        File dir = new File(path);
        if (!dir.exists()) {
            boolean result = dir.mkdirs();
            if (result) {
                logger.debug("创建目录： [" + path + "]");
            }
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
        if (Strings.isNullOrEmpty(parent)) {
            return subPackage;
        }
        return parent + StringPool.DOT + subPackage;
    }

    private String getPathInfo(String template, String outputDir, String packageInfo) {
        if (!Strings.isNullOrEmpty(template)) {
            return joinPath(outputDir, packageInfo);
        }
        return null;
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
