package com.lee9213.mybatis.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-22 22:28
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * <p>
     * 处理输出目录
     * </p>
     */
    public static void mkdir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            boolean result = dir.mkdirs();
            if (result) {
                LOGGER.debug("创建目录： [" + path + "]");
            }
        }
    }

}
