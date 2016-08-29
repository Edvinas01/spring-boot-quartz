package com.edv.quartz.jobs;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class NotificationJobInitializer {

    private static final String NOTIFICATION_JOB = "log-notification";
    private static final String NOTIFICATION_GROUP = "notifications";

    private final SimpleTrigger quickTrigger;
    private final Scheduler scheduler;

    @Autowired
    public NotificationJobInitializer(Scheduler scheduler,
                                      @Value("${triggers.quick.millis:}") long quickMillis) {

        this.scheduler = scheduler;
        this.quickTrigger = newTrigger()
                .withSchedule(simpleSchedule()
                        .withIntervalInMilliseconds(quickMillis)
                        .repeatForever())
                .build();
    }

    @PostConstruct
    public void initialize() throws SchedulerException {
        JobDetail job = newJob(NotificationJob.class)
                .storeDurably()
                .withIdentity(NOTIFICATION_JOB, NOTIFICATION_GROUP)
                .build();

        scheduler.scheduleJob(job, quickTrigger);
    }
}