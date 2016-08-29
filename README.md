# Spring Boot Quartz
This is an example Spring Boot project which configures Quartz using minimal configuration classes. 

# Running
After running the project, a `H2` in-memory database will be loaded. Quartz tables will be automatically created by `flyway`. After startup is complete, [NotificationJobInitializer](src/main/java/com/edd/quartz/notification/NotificationJobInitializer.java) class will create two example jobs which will start automatically (one `cron` job and one simple job). 

Example of both jobs logging:
```
2016-08-30 00:07:59.666  INFO 4468 --- [actory_Worker-3] c.e.q.notification.NotificationService   : [notifications.simple-job] Sending notifications...
2016-08-30 00:08:00.002  INFO 4468 --- [actory_Worker-4] c.e.q.notification.NotificationService   : [notifications.cron-job] Sending notifications...
```

Based off:
* https://github.com/davidkiss/spring-boot-quartz-demo
* https://github.com/nurkiewicz/quartz-demo
