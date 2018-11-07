package com.giants.common.tools;

import lombok.Data;

import java.util.Map;

/**
 * @author libo
 * @date 2018/11/7 14:58
 */
@Data
public class PageCondition {

    private Map<String,Object> filters;

    private Integer pageNo;

    private Integer pageSize;

    private String orderBy;

    private String orderSort;
}
