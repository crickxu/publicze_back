package com.platform.publicze_platform.Controller;
import com.platform.publicze_platform.Comment.Result;
import com.platform.publicze_platform.Dao.User;
import com.platform.publicze_platform.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("getUser")
    public User getUser(Integer id)
    {
        User user= userService.selectByPrimaryKey(id);
        return user;
    }
}
