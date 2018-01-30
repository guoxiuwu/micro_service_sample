package com.ebao.gs.sp.rc.controller

import com.ebao.gs.sp.rc.dao.UserDao
import com.ebao.gs.sp.rc.dao.repo.UserRepository
import com.ebao.gs.sp.rc.model.User
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.sql.DataSource

/**
 * Created by xiuwu.guo on 11/1/2017.
 */
@Slf4j
@RestController
class HelloController {

    @Autowired
    UserDao userDao

    @Autowired
    UserRepository userRepository

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String test() {
        User user = new User(
                userName: "zhangsan"
        )
        user = userDao.save(user)
        return "welcome-->" + user.getPK() + " : " + user.userName
    }

    @RequestMapping(value = "/api/users/login", method = RequestMethod.GET)
    public String test2() {
        User user = new User(
                userName: "zhangsan"
        )
        return "welcome-->" + "zhangsan"
    }
}
