package spring.security.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.security.dao.UsersDao;
import spring.security.dao.UsersDaoImpl;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    private UsersDao usersDao;

    @Autowired //  usersDao
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Autowired
    private ApplicationContext appContext;

    @GetMapping("/admin")
    public String goAdmin(Model model, Map<String, String> map) throws IOException {
        model.addAttribute("msg", "ModelHello Admin");
        logger.debug("debug-map = " + map);
        return "Admin";
    }

    @GetMapping("/user")
    public String goUser(Model model, Map<String, String> map) throws IOException {
        model.addAttribute("msg", "ModelHello Admin");
        logger.debug("debug-map = " + map);
        return "User";
    }

    @ResponseBody
    @GetMapping(value = "/usersCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCount() {
        return String.valueOf(usersDao.count());
    }

    @ResponseBody
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUsers() {
        return new Gson().toJson(usersDao.getUsers());
    }

    @ResponseBody
    @GetMapping(value = "/addUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam int enabled) {
        usersDao.addUser(username, password, enabled);
        return new Gson().toJson(usersDao.getUsers());
    }


}
