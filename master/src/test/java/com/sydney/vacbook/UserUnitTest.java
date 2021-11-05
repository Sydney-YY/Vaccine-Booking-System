package com.sydney.vacbook;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sydney.vacbook.controller.AdminController;
import com.sydney.vacbook.controller.UserController;
import com.sydney.vacbook.entity.*;
import com.sydney.vacbook.mapper.*;
import com.sydney.vacbook.service.IAdminService;
import com.sydney.vacbook.service.ILocationService;
import com.sydney.vacbook.service.IUserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.sydney.vacbook.tool.MD5.code;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author shuonan wang (kevin)
 * @since 2021-10-25
 *
 * Test all user functions from user controller
 *
 */
@SpringBootTest
@Transactional
public class UserUnitTest {

    @Autowired
    private ILocationService iLocationService;

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    UserController userController;

    void login(){
        User user  = iUserService.list().get(0);
        String account = user.getUserAccount();
        Map<String, Object> map = new HashMap<String, Object>();
        userController.login(account, "123");
    }

    @Test
    void loginTest(){
        User user = iUserService.list().get(0);
        System.out.println(user);

        String account = user.getUserAccount();

        //test wrong account, password
        assertFalse(userController.login(account, "1234"));
        //test correct account, password
        assertTrue(userController.login(account, "123"));
    }

    @Test
    void fetchUserTest(){
        login();
        User user = iUserService.list().get(0);
        ModelAndView modelAndView =  userController.fetchUser();
        Map<String, Object> map = modelAndView.getModel();
        User result = (User) map.get("result");
        assertTrue(result.equals(user));
    }

    @Test
    void updateUserTest(){
        login();
        String first = "kevin";
        String last = "wang";
        Integer age = 11;
        String account = "change";
        String email = "change@qq.com";
        String gender = "Female";
        String phone = "043161771";
        String address = "china";
        String question = "change";
        String answer = "change";
        User user = iUserService.list().get(0);
        ModelAndView modelAndView =  userController.updateUser(first, last, age, account, email, gender, phone, address, question, answer);
        Map<String, Object> map = modelAndView.getModel();
        User result = (User) map.get("result");
        assertTrue(result.getPhoneNumber().equals(phone));
        assertTrue(result.getUserFirstname().equals(first));
        assertTrue(result.getUserLastname().equals(last));
        assertTrue(result.getAge().equals(age));
        assertTrue(result.getEmail().equals(email));
        assertTrue(result.getAddress().equals(address));
        assertTrue(result.getUserQuestion().equals(question));
        assertTrue(result.getUserSafeKey().equals(answer));

    }

    @Test
    void changePassword(){
        User user = iUserService.list().get(0);
        System.out.println(user.getUserPassword());
        String changePassword = "hhh";
        userController.changePassword(user.getUserId(), changePassword);
        user = iUserService.list().get(0);
        System.out.println(user.getUserPassword());
        assertTrue(user.getUserPassword().equals(code(changePassword)));
    }

    @Test
    void forgetPassword(){
        User user = iUserService.list().get(0);
        assertTrue(userController.forgetPassword(user.getUserAccount(), user.getUserSafeKey())==user.getUserId());
        assertNull(userController.forgetPassword(user.getUserAccount(), "wrong"));

    }

    @Test
    void logoutTest(){
        Map map = new HashMap();

        assertTrue(userController.logout(map)=="redirect:login");
    }

    @Test
    void registerTest(){
        Map map = new HashMap();
        User user = new User(0,"456711","test1133@qq.com",
                "test12","test32","male","test",
                20,"123","test2213","1","1");

        assertTrue(userController.register(user,map)=="index");
        assertFalse(userController.register(user,map)=="index");
    }





}
