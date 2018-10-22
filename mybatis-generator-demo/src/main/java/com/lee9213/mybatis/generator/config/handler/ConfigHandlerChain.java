package com.lee9213.mybatis.generator.config.handler;

import com.lee9213.mybatis.generator.config.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-22 23:25
 */
public class ConfigHandlerChain {

    private final List<Handler> handlers = new ArrayList<>();

    public void handler(Configuration configuration) {
        for (Handler parser : handlers) {
            parser.handler(configuration);
        }
    }

    public void addHandler(Handler handler) {
        handlers.add(handler);
    }
}
