package com.olegzet.spring.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

/**
 * Created by oleg.zorin on 20.11.2017.
 */

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {

        log.info("The time is now {}", ZonedDateTime.now().format(ISO_ZONED_DATE_TIME));
    }
}
