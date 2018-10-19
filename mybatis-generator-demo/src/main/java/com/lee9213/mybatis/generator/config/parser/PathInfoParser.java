package com.lee9213.mybatis.generator.config.parser;

import com.google.common.base.Strings;
import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.PackageInfo;
import com.lee9213.mybatis.generator.config.domain.PathInfo;
import com.lee9213.mybatis.generator.config.properties.GlobalProperties;
import com.lee9213.mybatis.generator.config.properties.TemplateProperties;
import com.lee9213.mybatis.generator.util.Constant;
import com.lee9213.mybatis.generator.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author libo
 * @date 2018/10/18 11:14
 */
public class PathInfoParser implements Parser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void parser(Configuration configuration) {

        PackageInfo packageInfo = configuration.getPackageInfo();
        GlobalProperties globalProperties = configuration.getGlobalProperties();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        // 生成路径信息
        // TODO 优化路径
        String outputDir = globalProperties.getOutputDir();
        PathInfo pathInfo = new PathInfo()
                .setGeneratorPath(getPathInfo(templateProperties.getGenerator(), outputDir + "\\src\\main\\resources", packageInfo.getGenerator()))
                .setEntityPath(getPathInfo(templateProperties.getEntity(), outputDir + "\\src\\main\\java", packageInfo.getEntity()))
                .setVoPath(getPathInfo(templateProperties.getVo(), outputDir + "\\src\\main\\java", packageInfo.getVo()))
                .setMapperPath(getPathInfo(templateProperties.getMapper(), outputDir + "\\src\\main\\java", packageInfo.getMapper()))
                .setMapperXmlPath(getPathInfo(templateProperties.getMapperXml(), outputDir + "\\src\\main\\resources", packageInfo.getMapperXml()))
                .setExtendMapperXmlPath(getPathInfo(templateProperties.getExtendMapperXml(), outputDir + "\\src\\main\\resources", packageInfo.getExtendMapperXml()))
                .setServicePath(getPathInfo(templateProperties.getService(), outputDir + "\\src\\main\\java", packageInfo.getService()))
                .setServiceImplPath(getPathInfo(templateProperties.getServiceImpl(), outputDir + "\\src\\main\\java", packageInfo.getServiceImpl()))
                .setControllerPath(getPathInfo(templateProperties.getController(), outputDir + "\\src\\main\\java", packageInfo.getController()));
        configuration.setPathInfo(pathInfo);

        mkdir(pathInfo.getGeneratorPath());
        mkdir(pathInfo.getEntityPath());
        mkdir(pathInfo.getVoPath());
        mkdir(pathInfo.getMapperPath());
        mkdir(pathInfo.getMapperXmlPath());
        mkdir(pathInfo.getServicePath());
        mkdir(pathInfo.getServiceImplPath());
        mkdir(pathInfo.getControllerPath());
    }

    private String getPathInfo(String template, String outputDir, String packageInfo) {
        if (!Strings.isNullOrEmpty(template)) {
            return joinPath(outputDir, packageInfo);
        }
        return null;
    }

    /**
     * <p>
     * 处理输出目录
     * </p>
     */
    private void mkdir(String path) {
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
        packageName = packageName.replaceAll("\\.", Constant.BACK_SLASH + File.separator);
        return parentDir + packageName;
    }
}
