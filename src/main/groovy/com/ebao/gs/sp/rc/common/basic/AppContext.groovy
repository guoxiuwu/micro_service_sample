package com.ebao.gs.sp.rc.common.basic

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import org.springframework.util.Assert

/**
 * Created by xiuwu.guo on 11/2/2017.
 */
@Singleton
final class AppContext {

    ApplicationContext springContext

    private final static ThreadLocal<LoginContext> loginContextHolder = new ThreadLocal<LoginContext>()

    LoginContext loginContext() {
        def loginContext = loginContextHolder.get()
        if (!loginContext) {
            synchronized (loginContextHolder) {
                loginContext = loginContextHolder.get()
                if (!loginContext) {
                    loginContext = new LoginContext()
                    loginContextHolder.set(loginContext)
                }
            }
        }
        loginContext
    }

    void detachLoginContext() {
        def exLoginContext = loginContextHolder.get()
        if (exLoginContext) {
            synchronized (loginContextHolder) {
                loginContextHolder.remove()
            }
        }
    }

    void attachLoginContext(LoginContext newLoginContext) {
        Assert.notNull(newLoginContext)
        LoginContext exLoginContext = loginContext()
        exLoginContext.currentUser = newLoginContext.currentUser
        synchronized (newLoginContext) {
            loginContextHolder.set(exLoginContext)
        }
    }

}


@Component
class SpringAppContextInjection implements ApplicationContextAware {

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContext.instance.springContext = applicationContext
    }
}
