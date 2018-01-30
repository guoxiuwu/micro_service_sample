package com.ebao.gs.sp.rc.model

import com.ebao.gs.sp.rc.common.model.BaseEntity

/**
 * Created by xiuwu.guo on 11/8/2017.
 */
class EmailAttach extends BaseEntity {

    Long attachId
    Long emailId
    String filePath
    Byte fileType
    Byte status

    @Override
    Serializable getPK() {
        return attachId
    }
}
