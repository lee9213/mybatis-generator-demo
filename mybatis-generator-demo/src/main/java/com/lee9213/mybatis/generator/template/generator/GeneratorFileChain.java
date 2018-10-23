package com.lee9213.mybatis.generator.template.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>生成器链</p>
 *
 * @author libo
 * @date 2018/10/23 11:35
 */
public class GeneratorFileChain {

    private final List<AbstractFileGenerator> generators = new ArrayList<>();

    public void generator() throws Exception {
        for (AbstractFileGenerator generator : generators) {
            generator.generator();
        }
    }

    public void addGenerator(AbstractFileGenerator generator) {
        generators.add(generator);
    }
}
