package com.juma.auth.employee.domain;

import lombok.Data;

/**
 * @author libo
 * @date 2018/11/7 15:01
 */
@Data
public class LoginEmployee {

    private String sessionId;
    private Integer tenantId;
    private String tenantCode;
    private Integer userId;
    private String loginName;

    private boolean isTest = false;
    private boolean isSysUser = false;
    private Integer employeeId;
    private int maxInactiveInterval = 0;

    /**
     * 登录系统授权KEY
     * 后台管理系统，这个值为空，表示登录到所有系统
     */
    private String loginAuthKey;
    /**
     * 登录具有 数据权限的角色 如：经纪人
     * 后台管理系统，这个值为空，表示没有限制，登录所有角色
     */
    private String loginPermissionKey;
}
