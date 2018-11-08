package com.lee9213.mybatis.generator.config.handler;

import com.google.common.base.Strings;
import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.PackageInfo;
import com.lee9213.mybatis.generator.config.properties.PackageProperties;
import com.lee9213.mybatis.generator.util.Constant;

/**
 * <p>包信息解析</p>
 *
 * @author lee9213@163.com
 * @date 2018/10/18 11:07
 */
public class PackageInfoHandler implements Handler {

    @Override
    public void handler(Configuration configuration) {
        PackageProperties packageProperties = configuration.getPackageProperties();
        // 包信息
        packageProperties.setParent(joinPackage(packageProperties.getParent(), packageProperties.getModuleName()));
        PackageInfo packageInfo = new PackageInfo()
                .setModuleName(packageProperties.getModuleName())
                .setEntity(joinPackage(packageProperties.getParent(), packageProperties.getEntity()))
                .setVo(joinPackage(packageProperties.getParent(), packageProperties.getVo()))
                .setMapper(joinPackage(packageProperties.getParent(), packageProperties.getMapper()))
                .setMapperXml(joinPackage(packageProperties.getParent(), packageProperties.getMapperXml()))
                .setExtendMapperXml(joinPackage(packageProperties.getParent(), packageProperties.getExtendMapperXml()))
                .setService(joinPackage(packageProperties.getParent(), packageProperties.getService()))
                .setServiceImpl(joinPackage(packageProperties.getParent(), packageProperties.getServiceImpl()))
                .setController(joinPackage(packageProperties.getParent(), packageProperties.getController()))
                .setTest(joinPackage(packageProperties.getParent(), packageProperties.getTest()));
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
        return new StringBuilder(parent).append(Constant.DOT).append(subPackage).toString();
    }
}
