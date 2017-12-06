package com.winterchen.job;

import groovy.util.logging.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志
 * Created by Winterchen on 2017/11/22.
 */
@Component
@Log4j2
public class LogJob {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 2秒钟执行1次
     */
    //@Scheduled(fixedRate = 2 * 1000)
    public void logging(){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        log.info(simpleDateFormat.format(now));
        log.debug("-------DEBUG---------");
        log.error(new Long(now.getTime()).toString());
    }

}
