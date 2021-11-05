package com.sydney.vacbook.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sydney.vacbook.entity.Admin;
import com.sydney.vacbook.entity.Location;
import com.sydney.vacbook.entity.Vaccine;
import com.sydney.vacbook.service.IAdminService;
import com.sydney.vacbook.service.ILocationService;
import com.sydney.vacbook.service.IVaccineService;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;

@Controller
public class IndexController {

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private IVaccineService iVaccineService;

    @Autowired
    private ILocationService iLocationService;

    @GetMapping("vacBook/user/index")
    public  ModelAndView viewHomePage()  {
        Map<String, Object> result = new LinkedHashMap<>();
        List<Location> locationList = iLocationService.list();
        result.put("location_options", locationList);
        ModelAndView modelAndView = new ModelAndView("userPages/index", "result", result);
        return modelAndView;
    }

    @GetMapping("vacBook/user/login")
    public String userLogin() {
        return "userPages/userLogin";
    }

    @GetMapping("vacBook/user/register")
    public String userRegister() {
        return "userPages/userRegister";
    }

    @GetMapping("vacBook/user/register/index")
    public String success() {
        return "userPages/index";
    }

    @PostMapping("vacBook/user/index")
    public ModelAndView indexCheckboxDone(){
        Map<String, Object> result = new LinkedHashMap<>();
        List<Location> locationList = iLocationService.list();
        result.put("location_options", locationList);
        ModelAndView modelAndView = new ModelAndView( "userPages/index");
        modelAndView.addObject("result", result);
        return modelAndView;

    }

    @GetMapping("/vacBook/user/sendEmailSuccessful")
    public String userSentEmailSuccess() {
        return "userPages/emailConfirmation";
    }


    @GetMapping("vacBook/user/checkEligibility")
    public String userCheckEligibility() {
        return "userPages/userCheckEligibility";
    }

    @RequestMapping("/vacBook/user/forgottenPassword")
    public String userForgottenPassword() { return "userPages/forgot-psw";}

    @GetMapping("/vacBook/user/changePassword")
    public String userChangePassword() {
        return "userPages/change-psw";
    }


    @GetMapping("vacBook/user/checkEligibilityRegister")
    public String userCheckEligibilityInRegister() {
        return "userPages/CheckEligibilityInRegister";
    }


    @GetMapping("/getAllUserInfo")
    public String getAllUser(){
        return null;
    }

    @PostMapping("/sendGuestEmail")
    public void sendGuestEmail(){

    }

}
