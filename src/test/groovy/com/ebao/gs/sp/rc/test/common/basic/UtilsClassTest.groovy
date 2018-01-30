package com.ebao.gs.sp.rc.test.common.basic

import com.ebao.gs.sp.rc.common.exception.sys.ResourceNotFoundException
import com.ebao.gs.sp.rc.common.utils.ResourceParser
import com.ebao.gs.sp.rc.test.BaseTest
import org.junit.Test

/**
 * Created by xiuwu.guo on 11/8/2017.
 */
class UtilsClassTest extends BaseTest {

    @Test
    void testResourceUtils() {
        final String classPathPattern = "classpath:config/authority/apiAuths.yml"
        ResourceParser.instance.getResource(classPathPattern).file.exists() == true
        final String filePathPattern = "file:D:\\test.txt"
        assert (ResourceParser.instance.getResource(filePathPattern).file.exists()) == true

        shouldFail(IllegalArgumentException) {
            final String filePathPattern4 = null
            ResourceParser.instance.getResource(filePathPattern4)
        }
        shouldFail(ResourceNotFoundException) {
            final String filePathPattern5 = "file:D:\\93823.txt"
            ResourceParser.instance.getResource(filePathPattern5)
        }
    }

}
