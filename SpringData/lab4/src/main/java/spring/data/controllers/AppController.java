package spring.data.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.data.dao.UsersDao;
import spring.data.services.SystemUserService;

import java.io.IOException;
import java.util.List;
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
        return new Gson().toJson(usersDao.findAll());
    }

    @ResponseBody
    @GetMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam Boolean enabled) {
        logger.info("addUser {}, {}, {}", username, password, enabled);
        usersDao.addUser(username, password, enabled);
        return new Gson().toJson(usersDao.findAll());
    }

    @ResponseBody
    @GetMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestParam String username, @RequestParam String password, @RequestParam int enabled) {
        logger.info("updateUser {}, {}, {}", username, password, enabled);
        usersDao.updateUser(username, password, enabled);
        return new Gson().toJson(usersDao.findAll());
    }

    @ResponseBody
    @GetMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@RequestParam String username) {
        usersDao.deleteById(username);
        return new Gson().toJson(usersDao.findAll());
    }

    @ResponseBody
    @GetMapping(value = "/findUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findByUsername(@RequestParam String username) {
        return new Gson().toJson(usersDao.findByUsername(username));
    }

    @ResponseBody
    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
//        return new Gson().toJson(usersDao.countByEnabled(1));
        return new Gson().toJson(usersDao.queryDistinctFirstByUsernameEqualsOrUsernameIsInOrUsernameLike(
                "alixxx",
                List.of("alixx", "ali2"),
                "ali%"
        ));
    }


}
