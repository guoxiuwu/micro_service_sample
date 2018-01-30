package com.ebao.gs.sp.rc.common.config

import com.ebao.gs.sp.rc.common.filter.MyBasicAuthenticationFilter
import com.ebao.gs.sp.rc.common.filter.MySecurityFilter
import com.ebao.gs.sp.rc.common.security.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyInvocationSecurityMetadataSourceService myInvocationSecurityMetadataSourceService
    @Autowired
    private MyRefinedHttp401EntryPoint myRefinedHttp401EntryPoint
    @Autowired
    private DaoAuthenticationProvider myAuthenticationProvider
    @Autowired
    private MyLoginSuccessHandler myLoginSuccessHandler

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .addFilterBefore(new MySecurityFilter(myInvocationSecurityMetadataSourceService, new MyAccessDecisionManager(), authenticationManager()), FilterSecurityInterceptor.class)
                .addFilterAt(new MyBasicAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(myRefinedHttp401EntryPoint)
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers(MyStaticParams.PATH_REGEX.NO_AUTH_ALL).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable().anonymous().disable()
                .formLogin().loginPage(MyStaticParams.PATH_REGEX.LOGIN).successHandler(myLoginSuccessHandler).permitAll()
                .and()
                .logout().permitAll()
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider)
    }

}

