package com.ebao.gs.sp.rc.service

import org.springframework.security.core.userdetails.UserDetailsService

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
public interface UserService extends UserDetailsService {

    public void lockeUserIfNecessary(String username)

    public void unlockUserIfNecessary(String username)

    public void increaseLoginFailedTimes(String username)


}
