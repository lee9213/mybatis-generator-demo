package com.lee9213.mybatis.generator.plugins.juma;

import com.lee9213.mybatis.generator.config.Configuration;
import com.lee9213.mybatis.generator.config.handler.ExtendHandler;
import com.lee9213.mybatis.generator.config.handler.Handler;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-22 23:52
 */
@ExtendHandler
public class JumaHandler implements Handler {

    @Override
    public void handler(Configuration configuration) {
        configuration.getTableInfoList().forEach(tableInfo -> {
            if (!tableInfo.getIsLogicDelete()) {
                configuration.getStrategyProperties().setSuperEntityClass(null);
            }
        });
    }
}
