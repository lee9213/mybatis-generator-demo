package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.config.rules.FileType;

import java.io.File;
import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 19:42
 */
public abstract class AbstractFileGenerator implements Generator {

    protected Configuration configuration;


    @Override
    public void generator() throws Exception {
        List<TableInfo> tableInfoList = configuration.getTableInfoList();
        for (TableInfo tableInfo : tableInfoList) {
            doGenerator(tableInfo);
        }
    }

    protected abstract void doGenerator(TableInfo tableInfo) throws Exception;


    /**
     * 检测文件是否存在
     *
     * @return 是否
     */
    protected boolean isCreate(FileType fileType, String filePath) {
        Configuration cb = configuration;
        // 自定义判断
//        InjectionConfig ic = cb.getInjectionConfig();
//        if (null != ic && null != ic.getFileCreate()) {
//            return ic.getFileCreate().isCreate(cb, fileType, filePath);
//        }
        // 全局判断【默认】
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            mkDir(file.getParentFile());
        }
        return !exist || configuration.getGlobalProperties().isFileOverride();
    }

    /**
     * <p>
     * 新建文件目录
     * </p>
     *
     * @param file 文件
     */
    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }
}
