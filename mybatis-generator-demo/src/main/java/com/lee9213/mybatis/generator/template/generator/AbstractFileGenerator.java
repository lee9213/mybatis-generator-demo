package com.lee9213.mybatis.generator.template.generator;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.domain.TableInfo;
import com.lee9213.mybatis.generator.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>抽象文件生成器</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-21 19:42
 */
public abstract class AbstractFileGenerator implements FileGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void generator() throws Exception {
        Configuration configuration = (Configuration) SpringContextUtil.getBean("configuration");
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
}
