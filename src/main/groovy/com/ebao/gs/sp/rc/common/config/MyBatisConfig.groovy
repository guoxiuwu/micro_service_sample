package com.ebao.gs.sp.rc.common.config

import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.ebao.gs.sp.rc.dao.repo")
class MyBatisConfig {

}
