package com.lee9213.mybatis.generator.config.parser;

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
 * <p>路径信息解析</p>
 *
 * @author lee9213@163.com
 * @date 2018/10/18 11:14
 */
public class PathInfoParser implements Parser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Configuration configuration;

    public PathInfoParser(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void parser() {

        PackageInfo packageInfo = configuration.getPackageInfo();
        GlobalProperties globalProperties = configuration.getGlobalProperties();
        TemplateProperties templateProperties = configuration.getTemplateProperties();
        // 生成路径信息
        // TODO 优化路径
        String outputDir = globalProperties.getOutputDir();
        PathInfo pathInfo = new PathInfo()
                .setGeneratorPath(getPathInfo(templateProperties.getGenerator(), outputDir, Constant.RESOURCE_PATH, packageInfo.getGenerator()))
                .setEntityPath(getPathInfo(templateProperties.getEntity(), outputDir, Constant.JAVA_PATH, packageInfo.getEntity()))
                .setVoPath(getPathInfo(templateProperties.getVo(), outputDir, Constant.JAVA_PATH, packageInfo.getVo()))
                .setMapperPath(getPathInfo(templateProperties.getMapper(), outputDir, Constant.JAVA_PATH, packageInfo.getMapper()))
                .setMapperXmlPath(getPathInfo(templateProperties.getMapperXml(), outputDir, Constant.RESOURCE_PATH, packageInfo.getMapperXml()))
                .setExtendMapperXmlPath(getPathInfo(templateProperties.getExtendMapperXml(), outputDir, Constant.RESOURCE_PATH, packageInfo.getExtendMapperXml()))
                .setServicePath(getPathInfo(templateProperties.getService(), outputDir, Constant.JAVA_PATH, packageInfo.getService()))
                .setServiceImplPath(getPathInfo(templateProperties.getServiceImpl(), outputDir, Constant.JAVA_PATH, packageInfo.getServiceImpl()))
                .setControllerPath(getPathInfo(templateProperties.getController(), outputDir, Constant.JAVA_PATH, packageInfo.getController()));
        configuration.setPathInfo(pathInfo);
    }

    /***
     * 连接路径字符串
     * @param template 模板路径
     * @param outputDir 输出路径
     * @param prefixPath 文件路径
     * @param packageName 包名
     * @return
     */
    private String getPathInfo(String template, String outputDir, String prefixPath, String packageName) {
        if (StringUtils.isNotEmpty(template)) {
            if (StringUtils.isEmpty(outputDir)) {
                outputDir = System.getProperty(Constant.JAVA_TMPDIR);
            }
            StringBuilder builder = new StringBuilder(outputDir);
            if (!StringUtils.endsWith(outputDir, File.separator)) {
                builder.append(File.separator);
            }
            packageName = packageName.replaceAll("\\.", Constant.BACK_SLASH + File.separator);
            return builder.append(prefixPath).append(packageName).toString();
        }
        return null;
    }



}
