package com.edv.quartz.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotifications(String jobDetails) {
        LOGGER.info("[{}] Sending notifications...", jobDetails);
    }
}