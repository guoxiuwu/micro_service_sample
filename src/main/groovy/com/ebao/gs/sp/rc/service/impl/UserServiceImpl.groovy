package com.ebao.gs.sp.rc.service.impl

import com.ebao.gs.sp.rc.model.User
import com.ebao.gs.sp.rc.model.UserAuth
import com.ebao.gs.sp.rc.service.UserService
import org.springframework.stereotype.Service

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    void lockeUserIfNecessary(String username) {

    }

    @Override
    void unlockUserIfNecessary(String username) {

    }

    @Override
    void increaseLoginFailedTimes(String username) {

    }

    @Override
    User loadUserByUsername(String username) {
        return new User(
                userId: 1,
                userName: "aaaa",
                passWord: "bbbb",
                authorities: [new UserAuth(
                        authority: "ROLE"
                )]
        )
    }
}
