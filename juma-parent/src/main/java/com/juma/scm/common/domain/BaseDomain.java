package com.juma.scm.common.domain;

import java.util.Date;

/**
 * @author libo
 * @date 2018/11/7 15:08
 */
public class BaseDomain {

    private static final long serialVersionUID = -6265066617007399404L;

    private boolean isDelete;

    private Integer createUserId;

    protected Date createTime = new Date();

    private Integer lastUpdateUserId;

    private Date lastUpdateTime = new Date();

    public boolean isDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
