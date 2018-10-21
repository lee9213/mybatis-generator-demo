package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 19:42
 */
public abstract class AbstractFileGenerator implements FileGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Configuration configuration;

    @Override
    public void generator() throws Exception {
        List<TableInfo> tableInfoList = configuration.getTableInfoList();
        if (tableInfoList == null || tableInfoList.isEmpty()) {
            logger.error("没有数据");
            return;
        }
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
    protected boolean isCreate(String filePath) {
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
    private static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }
}
