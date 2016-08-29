package com.edv.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationJob implements Job {

    @Autowired
    private NotificationService notifierService;

    @Override
    public void execute(JobExecutionContext context) {
        notifierService.sendNotifications(context
                .getJobDetail()
                .getKey()
                .toString());
    }
}