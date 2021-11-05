package com.sydney.vacbook.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sydney.vacbook.entity.Admin;
import com.sydney.vacbook.entity.Location;
import com.sydney.vacbook.entity.User;
import com.sydney.vacbook.entity.Vaccine;
import com.sydney.vacbook.service.*;
import com.sydney.vacbook.service.impl.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sydney.vacbook.tool.MD5.code;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Group45
 * @since 2021-09-11
 */
@Controller
@RequestMapping("/vacBook/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private IVaccineService iVaccineService;

    @Autowired
    private IBookingService iBookingService;

    @Autowired
    private ILocationService iLocationService;


    List<User> listUser = new ArrayList<>();

    @GetMapping("/profile")
    public ModelAndView fetchUser() {
        User user = listUser.get(0);
        ModelAndView modelAndView = new ModelAndView("userPages/userProfile", "result", user);
        System.out.println(user);
        return modelAndView;
    }

    @PutMapping("/profile")
    public ModelAndView updateUser(@RequestParam String first, String last, Integer age, String account, String email, String gender, String phone, String address, String question, String answer) {
        User user = listUser.get(0);
        user.setUserFirstname(first);
        user.setUserLastname(last);
        user.setGender(gender);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setAddress(address);
        user.setUserAccount(account);
        user.setUserQuestion(question);
        user.setUserSafeKey(answer);
        user.setAge(age);
        iUserService.saveOrUpdate(user);
        return fetchUser();
    }

    /**
     * send account and receive user to get question
     */
    @RequestMapping("/getUserByAccount")
    @ResponseBody
    public String getUserByAccount(@RequestParam String userAccount) {
        System.out.println(userAccount);
        QueryWrapper<User> findUserByAccount = new QueryWrapper<>();
        findUserByAccount.lambda().eq(User::getUserAccount, userAccount);
        User user = iUserService.getOne(findUserByAccount);
        if (user != null) {
            System.out.println(user);
            String account = user.getUserQuestion();
            return account;
        } else {
            return null;
        }
    }

    @PostMapping("/forgetPassword")
    @ResponseBody
    public Integer forgetPassword(@RequestParam String userAccount, String answer){
        System.out.println("check answer");
        System.out.println(answer);
        QueryWrapper<User> findUserByAccount = new QueryWrapper<>();
        findUserByAccount.lambda().eq(User::getUserAccount, userAccount);
        User user = iUserService.getOne(findUserByAccount);
        System.out.println(user);
        if(!user.getUserSafeKey().equalsIgnoreCase(answer)){
            System.out.println(answer);
            System.out.println(user.getUserSafeKey());
            return null;
        }
        return user.getUserId();
    }

    @RequestMapping("/changePassword")
    public String changePassword(@RequestParam  Integer user_id, String changePassword) {
        User user = iUserService.getById(user_id);
        System.out.println(user);
        String userPasswordMD5 = code(changePassword);
        user.setUserPassword(userPasswordMD5);
        iUserService.saveOrUpdate(user);
        System.out.println(user.getUserPassword());
        return "index";
    }


    //存放user list
    @RequestMapping("/userList")
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView("userPages/index", "userList", listUser);
        return modelAndView;
    }

    @GetMapping("/index/booking")
    public ModelAndView userBooking() {
        User user = listUser.get(0);
        System.out.println(listUser.get(0));
        //List<Vaccine> vaccineList = iVaccineService.list();
        List<Vaccine> vaccineList = iVaccineService.list(new QueryWrapper<Vaccine>().gt("vaccine_amount",0));
        //根据vaccineList获取所有的adminId
        List<Integer> adminIdList = vaccineList.stream().map(Vaccine::getAdminId).collect(Collectors.toList());
        //查询adminId对应的管理员
        List<Admin> adminList = iAdminService.list(new QueryWrapper<Admin>().in("admin_id", adminIdList));
        ModelAndView modelAndView = new ModelAndView("userPages/indexBooking", "providers", adminList);
        modelAndView.addObject("vaccineList", vaccineList);
        modelAndView.addObject("firstName", user.getUserFirstname());
        modelAndView.addObject("lastName", user.getUserLastname());
        modelAndView.addObject("user_name", user.getUserLastname());
        modelAndView.addObject("user_name", user.getUserLastname());
        modelAndView.addObject("user_id", user.getUserId());
        Map<String, Object> result = new LinkedHashMap<>();
        List<Location> locationList = iLocationService.list();
        result.put("location_options", locationList);
        modelAndView.addObject("result", result);
        return modelAndView;
    }


    @PostMapping("/login")
    @ResponseBody
    public boolean login(String userAccount, String userPassword) {
        System.out.println(userAccount + ".,.." + userPassword);
        String userPasswordMD5 = code(userPassword);

        //按用户名密码查询
        QueryWrapper<User> sectionQueryWrapper = new QueryWrapper<>();
        sectionQueryWrapper.eq("user_account", userAccount);
        sectionQueryWrapper.eq("user_password", userPasswordMD5);
        listUser = iUserService.list(sectionQueryWrapper);

        if (!listUser.toString().equals("[]")) {
            System.out.println("Welcome to our system!");
            System.out.println(listUser.get(0));
            return true;
        } else {
            System.err.println("Password wrong");
            return false;
        }
    }

    @RequestMapping("/register")
    public String register(User user, Map<String, Object> body) {

        System.out.println(user);
        String passwordMD5 = code(user.getUserPassword());
        user.setUserPassword(passwordMD5);
        QueryWrapper<User> checkQueryWrapper1 = new QueryWrapper<>();
        checkQueryWrapper1.eq("user_account", user.getUserAccount());

        if (iUserService.getOne(checkQueryWrapper1) != null) {
            System.err.println("This account has been registered");
            return "redirect:registerPage";//重定向
        }
        QueryWrapper<User> checkQueryWrapper2 = new QueryWrapper<>();
        checkQueryWrapper2.eq("phone_number", user.getPhoneNumber());

        if (iUserService.getOne(checkQueryWrapper2) != null) {
            System.err.println("This phone has been registered");
            return "redirect:registerPage";//重定向
        }
        QueryWrapper<User> checkQueryWrapper3 = new QueryWrapper<>();
        checkQueryWrapper3.eq("email", user.getEmail());

        if (iUserService.getOne(checkQueryWrapper3) != null) {
            System.err.println("This email has been registered");
            return "redirect:registerPage";//重定向
        }


        boolean newUser = iUserService.save(user);

        if (newUser == false) {
            System.err.println("This account has been ...");
            return "redirect:registerPage";
        } else {
            System.out.println("Hi!");

            QueryWrapper<User> sectionQueryWrapper = new QueryWrapper<>();
            sectionQueryWrapper.eq("user_account", user.getUserAccount());
//            sectionQueryWrapper.eq("admin_password", user.getUserPassword());
            listUser = iUserService.list(sectionQueryWrapper);

            int userid = listUser.get(0).getUserId();
            String account = listUser.get(0).getUserAccount();
            String password = listUser.get(0).getUserPassword();
            String email = listUser.get(0).getEmail();
            String userLastName = listUser.get(0).getUserLastname();
            String userFirstName = listUser.get(0).getUserFirstname();
            String address = listUser.get(0).getAddress();
            int age = listUser.get(0).getAge();
            String phoneNumber = listUser.get(0).getPhoneNumber();
            String question = listUser.get(0).getUserQuestion();
            String userSafeKey = listUser.get(0).getUserSafeKey();


            body.put("userid", userid);
            body.put("username", account);
            body.put("password", password);
            body.put("email", email);
            body.put("userFirstName", userFirstName);
            body.put("userLastName", userLastName);
            body.put("address", address);
            body.put("age", age);
            body.put("phoneNumber", phoneNumber);
            body.put("question", question);
            body.put("userSafeKey", userSafeKey);

            return "index";

        }


    }

    @RequestMapping("/sendEmail")
    public ModelAndView sendToUserEmail(@RequestParam String email) throws MessagingException {
        sendEmailService.sendHtmlEmail(email, "HI!", "Subscription email from VacBook!");
        System.out.println("sent subscription email success!");
        ModelAndView modelAndView = new ModelAndView("userPages/emailConfirmation");
        return modelAndView;

    }


    @PostMapping("/checkboxDone")
    public ModelAndView checkboxDone() {
        ModelAndView modelAndView = new ModelAndView("userPages/userRegister");
        return modelAndView;

    }

    @GetMapping("/logout")
    public String logout(Map<Object, Object> map) {
        map.put("userid", "");
        map.put("username", "");
        return "redirect:login";// 重定向
    }


}