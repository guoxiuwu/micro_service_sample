package com.ebao.gs.sp.rc.test

import com.ebao.gs.sp.rc.Main
import com.ebao.gs.sp.rc.common.basic.AppContext
import com.ebao.gs.sp.rc.common.basic.LoginContext
import com.ebao.gs.sp.rc.model.User
import com.ebao.gs.sp.rc.test.common.basic.GroovyTestCase
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * Created by xiuwu.guo on 11/1/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = [Main.class])
@ActiveProfiles(["localdev"])
class BaseTest extends GroovyTestCase {

    private MockMvc mockMvc

    @BeforeClass
    static void contextLoads() {}

    @Autowired
    WebApplicationContext applicationContext

    // test rest api
    MockMvc getMockMVC() {
        if (mockMvc != null) {
            mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build()
        }
        mockMvc
    }


    void login(String userName = "admin") {
        AppContext.instance.attachLoginContext(
                new LoginContext(
                        currentUser: new User(
                                userName: "admin",
                                userId: 00001,
                        )
                )
        )
    }
}
