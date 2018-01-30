package com.ebao.gs.sp.rc.test.common.basic

import com.ebao.gs.sp.rc.test.BaseTest
import org.junit.Test
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.PlatformTransactionManager

import javax.annotation.Resource
import javax.sql.DataSource

/**
 * Created by xiuwu.guo on 11/2/2017.
 */
class DataSourceTest extends BaseTest {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate
    @Autowired
    JdbcTemplate jdbcTemplate
    @Resource
    DataSource dataSource
    @Autowired
    DataSourceProperties dataSourceProperties
    @Autowired
    PlatformTransactionManager platformTransactionManager

    @Test
    void testBasicDao() {
        assert jdbcTemplate != null
        assert sqlSessionTemplate != null
        assert dataSourceProperties != null
        assert dataSource.getConnection() != null
        assert platformTransactionManager != null
        jdbcTemplate.execute("select 1 from dual")
    }
}
