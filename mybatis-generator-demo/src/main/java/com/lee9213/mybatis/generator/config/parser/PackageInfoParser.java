package com.lee9213.mybatis.generator.config.parser;

import com.google.common.base.Strings;
import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.PackageInfo;
import com.lee9213.mybatis.generator.config.properties.PackageProperties;
import com.lee9213.mybatis.generator.util.Constant;

/**
 * @author libo
 * @date 2018/10/18 11:07
 */
public class PackageInfoParser implements Parser {

    @Override
    public void parser(Configuration configuration) {
        PackageProperties packageProperties = configuration.getPackageProperties();
        // 包信息
        packageProperties.setParent(joinPackage(packageProperties.getParent(), packageProperties.getModuleName()));
        PackageInfo packageInfo = new PackageInfo()
                .setModuleName(packageProperties.getModuleName())
                .setEntity(joinPackage(packageProperties.getParent(), packageProperties.getEntity()))
                .setMapper(joinPackage(packageProperties.getParent(), packageProperties.getMapper()))
                .setMapperXml(joinPackage(packageProperties.getParent(), packageProperties.getMapperXml()))
                .setService(joinPackage(packageProperties.getParent(), packageProperties.getService()))
                .setServiceImpl(joinPackage(packageProperties.getParent(), packageProperties.getServiceImpl()))
                .setController(joinPackage(packageProperties.getParent(), packageProperties.getController()));
        configuration.setPackageInfo(packageInfo);
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
        return parent + Constant.DOT + subPackage;
    }
}
