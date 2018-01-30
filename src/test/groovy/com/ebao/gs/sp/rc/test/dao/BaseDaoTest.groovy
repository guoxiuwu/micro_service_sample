package com.ebao.gs.sp.rc.test.dao

import com.ebao.gs.sp.rc.common.basic.ConfigVariable
import com.ebao.gs.sp.rc.common.exception.sys.EntityNotFoundException
import com.ebao.gs.sp.rc.common.utils.Const
import com.ebao.gs.sp.rc.common.utils.Encryptor
import com.ebao.gs.sp.rc.dao.UserDao
import com.ebao.gs.sp.rc.dao.repo.UserRepository
import com.ebao.gs.sp.rc.model.User
import com.ebao.gs.sp.rc.model.UserAuth
import com.ebao.gs.sp.rc.model.UserProfile
import com.ebao.gs.sp.rc.model.UserReg
import com.ebao.gs.sp.rc.test.BaseTest
import org.joda.time.DateTime
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class BaseDaoTest extends BaseTest {

    @Autowired
    UserDao userDao

    @Autowired
    UserRepository userRepository

    @Autowired
    ConfigVariable configVariable

    @Test
    @Transactional
    @Rollback(false)
    void testUser() {
        login()
        User user = new User(
                insurerTenantCode: "TEST_TENANT_CODE",
                userName: "test" + UUID.randomUUID().toString().substring(0, 6),
                realName: "tesst_ralName",
                email: "test@test.com" + UUID.randomUUID().toString().substring(0, 6),
                mobile: "123434" + UUID.randomUUID().toString().substring(0, 6),
                telPhone: "telphone",
                passWord: Encryptor.encrypt("mypassword"),
                authorities: [new UserAuth(authority: "ADMIN")],
                userProfile: new UserProfile(preferredLang: "en_EN"),
                credentialsValidBy: new DateTime().plusWeeks(2).toDate()
        )
        user = userDao.save(user)
        assert user.userId != null
        println "-> userId is:" + user.userId

        User user1 = userDao.findOne(User.class, user.userId)
        assert user1.userProfile.preferredLang == "en_EN"
        assert user1.userId == user.userId

        int count = userDao.delete(user)
        assert count == 1

        shouldFail(EntityNotFoundException) {
            userDao.load(User.class, user.userId)
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    void testUserReg() {
        login()
        UserReg userReg = new UserReg(
                insurerTenantCode: "TEST_TENANT_CODE",
                userName: "test",
                realName: "tesst_ralName",
                email: "test@test.com",
                mobile: "13232323434",
                telPhone: "telphone",
                passWord: Encryptor.encrypt("mypassword"),
                userProfile: new UserProfile(preferredLang: "en_EN"),
                verifyEmailRes: Const.BASE_FAILED,
        )
        userReg = userDao.save(userReg)
        assert userReg.userRegId != null
        println "-> userRegId is:" + userReg.userRegId
    }


}
