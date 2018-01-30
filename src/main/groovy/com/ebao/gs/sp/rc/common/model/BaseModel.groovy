package com.ebao.gs.sp.rc.common.model

import com.fasterxml.jackson.annotation.JsonIgnore


abstract class BaseEntity implements Serializable {
    @JsonIgnore
    abstract public Serializable getPK()
}

abstract class BaseAuditEntity extends BaseEntity {
    @JsonIgnore
    Date insertTime
    @JsonIgnore
    Long insertUserId

    Date updateTime
    @JsonIgnore
    Long updateUserId
}

abstract class BaseDataVerEntity extends BaseAuditEntity implements EntityDataAware {
    int ver
}

interface EntityDataAware {
    int getVer()
    void setVer(int ver)
}