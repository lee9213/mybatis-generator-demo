package com.lee9213.mybatis.generator.property;

import com.google.common.base.Strings;
import com.lee9213.mybatis.generator.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 12:16
 */
public abstract class AbstractPropertySource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void parse(String fileName) {

        if (Strings.isNullOrEmpty(fileName)) {
            logger.error("File name not empty！");
        }
        PropertiesUtil properties = PropertiesUtil.getInstance(fileName);
        doParse(properties);
    }

    public abstract void doParse(PropertiesUtil properties);
}
