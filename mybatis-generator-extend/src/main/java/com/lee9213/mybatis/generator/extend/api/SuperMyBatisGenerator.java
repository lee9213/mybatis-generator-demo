package com.lee9213.mybatis.generator.extend.api;

import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.exception.InvalidConfigurationException;

import java.util.List;

/**
 * @author libo
 * @date 2018/10/17 18:05
 */
public class SuperMyBatisGenerator extends org.mybatis.generator.api.MyBatisGenerator {
    /**
     * Constructs a MyBatisGenerator object.
     *
     * @param configuration The configuration for this invocation
     * @param shellCallback an instance of a ShellCallback interface. You may specify
     *                      <code>null</code> in which case the DefaultShellCallback will
     *                      be used.
     * @param warnings      Any warnings generated during execution will be added to this
     *                      list. Warnings do not affect the running of the tool, but they
     *                      may affect the results. A typical warning is an unsupported
     *                      data type. In that case, the column will be ignored and
     *                      generation will continue. You may specify <code>null</code> if
     *                      you do not want warnings returned.
     * @throws InvalidConfigurationException if the specified configuration is invalid
     */
    public SuperMyBatisGenerator(Configuration configuration, ShellCallback shellCallback, List<String> warnings) throws InvalidConfigurationException {
        super(configuration, shellCallback, warnings);
    }
}
