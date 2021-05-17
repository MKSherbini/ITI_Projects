package com.example.demo1.services;

import com.example.demo1.dao.UsersDao;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SystemUserService {

    private UsersDao usersDao;

    // for metrics
    private final Timer timer;

    @Autowired //  usersDao
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public SystemUserService(MeterRegistry meterRegistry) {
        timer = meterRegistry.timer("SystemUserService.timer");
    }

    @PostConstruct
    public void init() {
        timer.record(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
