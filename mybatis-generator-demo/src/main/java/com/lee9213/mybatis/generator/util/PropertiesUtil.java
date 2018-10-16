package com.lee9213.mybatis.generator.util;

import java.util.Date;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 资源文件读取工具
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2017-11-01 20:44
 */
public final class PropertiesUtil {

    /**
     * 当打开多个资源文件时，缓存资源文件
     */
    private static Map<String, PropertiesUtil> configMap = new ConcurrentHashMap<>();
    /**
     * 打开文件时间，判断超时使用
     */
    private Date loadTime;
    /**
     * 资源文件
     */
    private ResourceBundle resourceBundle;
    /**
     * 默认资源文件名称
     */
    private static final String NAME = "generator";
    /**
     * 缓存时间
     */
    private static final Integer TIME_OUT = 60 * 1000;

    private PropertiesUtil(String name) {
        this.loadTime = new Date();
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    public static synchronized PropertiesUtil getInstance() {
        return getInstance(NAME);
    }

    public static synchronized PropertiesUtil getInstance(String name) {
        PropertiesUtil conf = configMap.get(name);
        if (null == conf) {
            conf = new PropertiesUtil(name);
            configMap.put(name, conf);
        }
        // 判断是否打开的资源文件是否超时1分钟
        if ((System.currentTimeMillis() - conf.getLoadTime().getTime()) > TIME_OUT) {
            conf = new PropertiesUtil(name);
            configMap.put(name, conf);
        }
        return conf;
    }

    /**
     * 根据key读取value
     *
     * @param key
     * @return String
     */
    public String get(String key) {
        try {
            String value = resourceBundle.getString(key);
            return value;
        } catch (MissingResourceException e) {
            return "";
        }
    }

    /**
     * 根据key读取value
     *
     * @param key
     * @return Integer
     */
    public Integer getInt(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Integer.parseInt(value);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * 根据key读取value
     *
     * @param key
     * @return boolean
     */
    public boolean getBoolean(String key) {
        try {
            String value = resourceBundle.getString(key);
            if ("true".equals(value)) {
                return true;
            }
            return false;
        } catch (MissingResourceException e) {
            return false;
        }
    }

    public Date getLoadTime() {
        return loadTime;
    }
}
