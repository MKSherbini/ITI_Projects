package com.broker.web;

import com.broker.model.SystemUser;
import com.broker.publish.CustomPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    //    @Autowired
//    @Qualifier("testChannel")
//    private MessageChannel testChannel;
    @Autowired
    private CustomPublisher gateway;

    @GetMapping
    public String helloWorld() {
//        testChannel.send(MessageBuilder
//                .withPayload(new SystemUser("username", "password", 1))
//                .setHeader("time", OffsetDateTime.now())
//                .build());
        gateway.publish(new SystemUser("username", "password", 1), OffsetDateTime.now());
        return "HelloWorld";
    }

//    @ResponseBody
//    @GetMapping(value = "/usersCount", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getCount() {
//        return String.valueOf(usersDao.count());
//    }
//
//    @ResponseBody
//    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getUsers() {
//        return new Gson().toJson(usersDao.findAll());
//    }
//
//    @ResponseBody
//    @GetMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam Boolean enabled) {
//        logger.info("addUser {}, {}, {}", username, password, enabled);
//        logger.info("{}", testChannel.send(MessageBuilder.withPayload(username).setHeader("time", OffsetDateTime.now()).build()));
//        logger.info("{}", testChannel.send(MessageBuilder.withPayload(username).setHeader("time", OffsetDateTime.now()).build()));
//        logger.info("{}", testChannel.send(MessageBuilder.withPayload(username).setHeader("time", OffsetDateTime.now()).build()));
////        ;
////        usersDao.addUser(username, password, enabled);
//        return new Gson().toJson(usersDao.findAll());
//    }
//
//    @ResponseBody
//    @GetMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String updateUser(@RequestParam String username, @RequestParam String password, @RequestParam int enabled) {
//        logger.info("updateUser {}, {}, {}", username, password, enabled);
//        usersDao.updateUser(username, password, enabled);
//        return new Gson().toJson(usersDao.findAll());
//    }
//
//    @ResponseBody
//    @GetMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String deleteUser(@RequestParam String username) {
//        usersDao.deleteById(username);
//        return new Gson().toJson(usersDao.findAll());
//    }
//
//    @ResponseBody
//    @GetMapping(value = "/findUser", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String findByUsername(@RequestParam String username) {
//        return new Gson().toJson(usersDao.findByUsername(username));
//    }
//
//    @ResponseBody
//    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String test() {
////        return new Gson().toJson(usersDao.countByEnabled(1));
//        return new Gson().toJson(usersDao.queryDistinctFirstByUsernameEqualsOrUsernameIsInOrUsernameLike(
//                "alixxx",
//                List.of("alixx", "ali2"),
//                "ali%"
//        ));
//    }


}
