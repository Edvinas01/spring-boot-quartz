package com.edd.quartz.notification;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class NotificationJobInitializer {

    private static final String NOTIFICATION_GROUP = "notifications";

    private final SimpleTrigger quickTrigger;
    private final CronTrigger cronTrigger;

    private final Scheduler scheduler;

    @Autowired
    public NotificationJobInitializer(Scheduler scheduler,
                                      @Value("${triggers.millis}") long millis,
                                      @Value("${triggers.cron}") String cron) {

        this.scheduler = scheduler;
        this.quickTrigger = createTrigger(millis);
        this.cronTrigger = createTrigger(cron);
    }

    @PostConstruct
    public void initialize() throws SchedulerException {

        // Schedule simple millisecond job.
        scheduler.scheduleJob(newJob(NotificationJob.class)
                .storeDurably()
                .withIdentity("simple-job", NOTIFICATION_GROUP)
                .build(), quickTrigger);

        // Schedule cron-job.
        scheduler.scheduleJob(newJob(NotificationJob.class)
                .storeDurably()
                .withIdentity("cron-job", NOTIFICATION_GROUP)
                .build(), cronTrigger);
    }

    /**
     * Creates a simple millisecond based trigger.
     */
    private SimpleTrigger createTrigger(long millis) {
        return newTrigger()
                .withSchedule(simpleSchedule()
                        .withIntervalInMilliseconds(millis)
                        .repeatForever())
                .build();
    }

    /**
     * Creates a cron based trigger.
     */
    private CronTrigger createTrigger(String cron) {
        return newTrigger()
                .withSchedule(cronSchedule(cron))
                .build();
    }
}