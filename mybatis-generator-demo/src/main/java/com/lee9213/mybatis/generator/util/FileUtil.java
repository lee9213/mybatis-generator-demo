package com.lee9213.mybatis.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

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

    /**
     * 检测文件是否存在
     *
     * @return 是否
     */
    public static boolean exists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * <p>
     * 打开输出目录
     * </p>
     */
    public static void open(String outDir, boolean isOpen) {
        if (StringUtils.isNotEmpty(outDir) && isOpen) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + outDir);
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + outDir);
                    } else {
                        LOGGER.debug("文件输出目录:" + outDir);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
