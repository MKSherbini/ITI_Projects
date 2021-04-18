package de.rieckpil.quickstarts.bbeans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.ejb.*;


@Startup
@Singleton
@DependsOn("StatusBean")
public class ProgTimer {
//    @PostConstruct
//    public void postConstruct() {
//        init();
//        System.out.println("ProgTimer.postConstruct");
//    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("ProgTimer.preDestroy");
    }


    @Resource
    private TimerService timerService;

    @Timeout
    public void timeout() {
        System.out.println("ProgTimer.timeout");
    }

    public void init() {
        ScheduleExpression exp = new ScheduleExpression();
        exp.hour("*").minute("*").second("*/4");

        timerService.createCalendarTimer(exp);
    }
}