package com.ebao.gs.sp.rc.common.basic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.util.Assert
import org.springframework.validation.annotation.Validated

import javax.validation.constraints.NotNull

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
@Validated
@Component
@ConfigurationProperties(prefix = "my.var")
class ConfigVariable {

    @Autowired
    Environment environment

    String getSysProperty(String propertyName) {
        Assert.notNull(propertyName)
        environment.getProperty(propertyName)
    }

    @NotNull
    Integer login_failed_locked_minutes
    @NotNull
    String api_auth_mapping_locations

}
