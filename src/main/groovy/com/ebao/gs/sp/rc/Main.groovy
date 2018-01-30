package com.ebao.gs.sp.rc

import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
@Slf4j
@SpringBootApplication
class Main {

    static void main(String[] args) {
        log.info("-----------------------------------")
        log.info("--- Run Registration Center APP ---")
        log.info("-----------------------------------")
        SpringApplication.run(Main.class, args)
    }
}
