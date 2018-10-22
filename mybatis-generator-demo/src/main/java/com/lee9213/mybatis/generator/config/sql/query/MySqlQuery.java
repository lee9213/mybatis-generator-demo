/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.lee9213.mybatis.generator.config.sql.query;


import com.lee9213.mybatis.generator.config.sql.enums.DbType;
import com.lee9213.mybatis.generator.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>MySql 表数据查询</p>
 *
 * @author lee9213@163.com
 * @since 2018-01-16
 */
public class MySqlQuery extends AbstractDbQuery {


    @Override
    public DbType dbType() {
        return DbType.MYSQL;
    }


    @Override
    public String tablesSql(List<String> includeTables, List<String> includeTablePrefixs, List<String> excludeTables) {
        boolean isIncludeTables = CollectionUtils.isNotEmpty(includeTables);
        boolean isIncludeTablePrefixs = CollectionUtils.isNotEmpty(includeTablePrefixs);
        boolean isExcludeTables = CollectionUtils.isNotEmpty(excludeTables);
        StringBuilder builder = new StringBuilder();
        builder.append("show table status where 1=1 ");
        if (isIncludeTables && isIncludeTablePrefixs) {
            String includeTableStr = includeTables.stream().map(item -> "'" + item + "',").collect(Collectors.joining());
            includeTableStr = includeTableStr.substring(0, includeTableStr.lastIndexOf(","));
            builder.append(" and (name in (").append(includeTableStr).append(")");
            includeTablePrefixs.forEach(item -> builder.append(" or name like '" + item + "%'"));
            builder.append(")");
        } else if (isIncludeTables) {
            String includeTableStr = includeTables.stream().map(item -> "'" + item + "',").collect(Collectors.joining());
            includeTableStr = includeTableStr.substring(0, includeTableStr.lastIndexOf(","));
            builder.append(" and name in (").append(includeTableStr).append(")");
        } else if (isIncludeTablePrefixs) {
            builder.append(" and (");
            String includeTablePrefixStr = includeTablePrefixs.stream().map(item -> " name like '" + item + "%' or").collect(Collectors.joining());
            includeTablePrefixStr = includeTablePrefixStr.substring(0, includeTablePrefixStr.lastIndexOf("or"));
            builder.append(includeTablePrefixStr);
            builder.append(" )");
        }
        if (isExcludeTables) {
            String excludeTableStr = excludeTables.stream().map(item -> "'" + item + "',").collect(Collectors.joining());
            excludeTableStr = excludeTableStr.substring(0, excludeTableStr.lastIndexOf(","));
            builder.append(" and name not in (").append(excludeTableStr).append(")");
        }
        return builder.toString();
    }


    @Override
    public String tableFieldsSql() {
        return "show full fields from `%s`";
    }


    @Override
    public String tableName() {
        return "NAME";
    }


    @Override
    public String tableComment() {
        return "COMMENT";
    }


    @Override
    public String fieldName() {
        return "FIELD";
    }


    @Override
    public String fieldType() {
        return "TYPE";
    }


    @Override
    public String fieldComment() {
        return "COMMENT";
    }


    @Override
    public String fieldKey() {
        return "KEY";
    }


    @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return "auto_increment".equals(results.getString("Extra"));
    }
}
