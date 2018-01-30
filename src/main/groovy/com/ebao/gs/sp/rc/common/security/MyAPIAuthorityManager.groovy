package com.ebao.gs.sp.rc.common.security

import com.ebao.gs.sp.rc.common.basic.ConfigVariable
import com.ebao.gs.sp.rc.common.exception.sys.APIAuthConfigureException
import com.ebao.gs.sp.rc.common.utils.ResourceParser
import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.security.access.SecurityConfig
import org.springframework.stereotype.Component
import org.springframework.util.Assert
import org.yaml.snakeyaml.Yaml

import javax.annotation.PostConstruct
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by xiuwu.guo on 11/7/2017.
 */
@Component
@Configuration
class MyAPIAuthorityManager implements Serializable {

    @Autowired
    private ConfigVariable configVariable

    private static final Map apiRoles = new ConcurrentHashMap()

    @PostConstruct
    void init() {
        Assert.notNull(configVariable.api_auth_mapping_locations, "The 'api_auth_mapping_locations' must be configured")
        Resource resource = ResourceParser.instance.getResource(configVariable.api_auth_mapping_locations)
        Map configs = new Yaml().loadAs(resource.file.newInputStream(), Map.class)
        configs.each { k, v ->
            if (StringUtils.isEmpty(k) || !(v instanceof List)) {
                throw new APIAuthConfigureException("""invalid api and role defined in ${configVariable.api_auth_mapping_locations},it should be defined like:
                 /api/test/test1/: ['ROLE1','ROLE2']
                 /api/test/test2/: ['ROLE1']
                 /api/test/test3/: [] """)
            }
            apiRoles.put(StringUtils.trim(k), v.collect { role ->
                StringUtils.trim(role)
            }.unique().collect {
                new SecurityConfig(it)
            })
        }
    }

    Map getAPIAndRolesMapping() {
        return apiRoles
    }
}
