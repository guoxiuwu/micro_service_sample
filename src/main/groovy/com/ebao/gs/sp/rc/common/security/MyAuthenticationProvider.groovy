package com.ebao.gs.sp.rc.common.security

import com.ebao.gs.sp.rc.common.filter.MyBasicAuthenticationFilter
import com.ebao.gs.sp.rc.common.filter.MySecurityFilter
import com.ebao.gs.sp.rc.common.utils.Encryptor
import com.ebao.gs.sp.rc.model.User
import com.ebao.gs.sp.rc.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.authentication.dao.SaltSource
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
@Configuration
class MyAuthenticationProvider {

    @Autowired
    UserService userService

    @Bean
    public DaoAuthenticationProvider myAuthProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider() {
            @Override
            Authentication authenticate(Authentication authentication) throws AuthenticationException {
                try {
                    return super.authenticate(authentication)
                } catch (BadCredentialsException badCredentialsException) {
                    def username = authentication.getPrincipal()
                    User localUser = userService.loadUserByUsername(username)
                    if (localUser) {
                        userService.increaseLoginFailedTimes(localUser.username)
                        //spConf.login_max_attempt(do not lock user if the password reaches the number of errors)
                        if (localUser.loginFailedTimes + 1 >= Integer.MAX_VALUE) {
                            userService.lockeUserIfNecessary(localUser.username)
                        }
                    }
                    throw badCredentialsException
                }
            }
        }
        daoAuthenticationProvider.setSaltSource(new SaltSource() {
            @Override
            Object getSalt(UserDetails user) {
                Encryptor.salt
            }
        })
        daoAuthenticationProvider.setPasswordEncoder(new MyMd5PasswordEncoder())
        daoAuthenticationProvider.setUserDetailsService(userService)
        daoAuthenticationProvider
    }

}
