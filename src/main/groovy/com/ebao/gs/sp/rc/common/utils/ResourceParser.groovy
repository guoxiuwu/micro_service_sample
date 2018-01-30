package com.ebao.gs.sp.rc.common.utils

import com.ebao.gs.sp.rc.common.exception.sys.ResourceNotFoundException
import org.apache.commons.lang.ArrayUtils
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.util.Assert

import java.lang.reflect.Field

/**
 * Created by xiuwu.guo on 11/7/2017.
 */
class ResourceParser {

    private static ResourceParser parser

    private static PathMatchingResourcePatternResolver resolver

    private ResourceParser(PathMatchingResourcePatternResolver resolver) {
        this.resolver = resolver
    }

    public synchronized static ResourceParser getInstance() {
        if (parser == null) {
            parser = new ResourceParser(
                    new PathMatchingPatternResolver(ResourceParser.class.getClassLoader()))
        }
        return parser
    }

    /**
     * @param pattern 1) classPath:com/...  2) file:D:/test
     * @return
     */
    public Resource[] getResources(String pattern) {
        Assert.notNull(pattern)
        Resource[] resources = null
        try {
            resources = resolver.getResources(pattern)
        } catch (IOException e) {
            throw e
        }
        if (resources == null) {
            return []
        } else {
            return resources
        }
    }

    public Resource getResource(String pattern) {
        def res = getResources(pattern)
        if (ArrayUtils.isNotEmpty(res)) {
            Resource resource =  res.first()
            if(!resource.file?.exists()){
                throw new ResourceNotFoundException("The Resource:${pattern} not found".toString())
            }
            return res.first()
        } else {
            throw new ResourceNotFoundException("The Resource:${pattern} not found".toString())
        }
    }


    private static class PathMatchingPatternResolver extends org.springframework.core.io.support.PathMatchingResourcePatternResolver {

        private static String OSGI_FIELD_NAME = "equinoxResolveMethod"

        public PathMatchingPatternResolver(ClassLoader classLoader) {
            super(classLoader)
            Field f_equinoxResolveMethod = this.getClass().getSuperclass().
                    getDeclaredField(OSGI_FIELD_NAME)
            f_equinoxResolveMethod.setAccessible(true)
            f_equinoxResolveMethod.set(this, null)
        }
    }
}

