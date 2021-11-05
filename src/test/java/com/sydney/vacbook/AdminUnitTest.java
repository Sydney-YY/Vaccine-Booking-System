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
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author shuonan wang (kevin)
 * @since 2021-10-25
 *
 * Test all admin functions from admin controller
 *
 */

@SpringBootTest
@Transactional
public class AdminUnitTest {

    @Autowired
    private ILocationService iLocationService;

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    AdminController adminController;

    void login(){
        Admin admin = iAdminService.list().get(0);
        String account = admin.getAdminAccount();
        Map<String, Object> map = new HashMap<String, Object>();
        adminController.login(account, "1234",map);
    }

    @Test
    void loginTest(){
        Admin admin = iAdminService.list().get(0);
        String account = admin.getAdminAccount();
        Map<String, Object> map = new HashMap<String, Object>();

        //test wrong account, password
        assertFalse(adminController.login(account, "123",map));
        //test correct account, password
        assertTrue(adminController.login(account, "1234",map));
    }

    @Test
    void fetchDashboardTest(){
        login();
        Admin admin = iAdminService.list().get(0);
        System.out.println(admin);
        ModelAndView modelAndView = adminController.fetchDashboard();
        Map<String, Object> map = modelAndView.getModel();
        LinkedHashMap result =  (LinkedHashMap) map.get("result");
        System.out.println(result);
        assertTrue((result.get("account").equals(admin.getAdminAccount())));
        assertTrue((result.get("name").equals(admin.getAdminName())));
        String LocationName = iLocationService.getById(admin.getLocationId()).getLocation();
        assertTrue((result.get("location").equals(LocationName)));
    }

    @Test
    void fetchSettingTest(){
        login();
        Admin admin = iAdminService.list().get(0);
        System.out.println(admin);
        ModelAndView modelAndView = adminController.fetchSetting();
        Map<String, Object> map = modelAndView.getModel();
        LinkedHashMap result =  (LinkedHashMap) map.get("result");
        System.out.println(result);
        assertTrue((result.get("account").equals(admin.getAdminAccount())));
        assertTrue((result.get("name").equals(admin.getAdminName())));
        String LocationName = iLocationService.getById(admin.getLocationId()).getLocation();
        assertTrue((result.get("location_name").equals(LocationName)));
    }

    @Test
    void updateSettingTest(){
        login();
        Admin admin = iAdminService.list().get(0);
        System.out.println(admin);

        String name = "change";
        String password = "change";
        Integer locationId = iAdminService.list().get(1).getLocationId();
        String LocationName = iLocationService.getById( locationId).getLocation();

        ModelAndView modelAndView = adminController.updateSetting(name, password, locationId);
        Map<String, Object> map = modelAndView.getModel();
        LinkedHashMap result =  (LinkedHashMap) map.get("result");
        System.out.println(result);
        assertTrue((result.get("account").equals(admin.getAdminAccount())));
        assertTrue((result.get("name").equals(name)));
        assertTrue((result.get("location_name").equals(LocationName)));
    }

    @Test
    void fetchBookingUserTest(){
        login();
        User user = iUserService.list().get(0);
        ModelAndView modelAndView = adminController.fetchBookingUser(user.getUserId());
        Map<String, Object> map = modelAndView.getModel();
        User result = (User) map.get("result");
        System.out.println(result);
        assertTrue(result.equals(user));
    }

    @Test
    void registerTest(){
        Admin admin = new Admin(null,"testAccount","123","test1",7);
        Map<Object, Object> map = new HashMap<Object, Object>();
        String result = adminController.register(admin, map);

        assertTrue(result.equals("adminPages/base"));
    }

    @Test
    void addVaccineTest(){
        login();
        User user = iUserService.list().get(0);
        Vaccine vaccineTest = new Vaccine(0, "mRNA", "Pfizer", 17, 100);
        String result = adminController.addVaccine(vaccineTest.getVaccineName(), vaccineTest.getVaccineType(), vaccineTest.getVaccineAmount(), new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        });

        assertTrue(result=="adminPages/adminVaccines::vac_container");
    }

    //    make sure the id is in database if this test is wrong because id is not in your database
    @Test
    void delVaccineTest(){
        login();
        User user = iUserService.list().get(0);
        Vaccine vaccineTest = new Vaccine(48, "mRNA", "Pfizer", 17, 100);
        String result = adminController.deleteVaccine(vaccineTest.getVaccineId(), new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        });
        assertTrue(result=="adminPages/adminVaccines::vac_container");
    }

    @Test
    void updateVaccineTest(){
        login();
        User user = iUserService.list().get(0);
        Vaccine vaccineTest = new Vaccine(33, "mRNA", "Pfizer", 17, 200);
        String result = adminController.updateVaccine( vaccineTest.getVaccineAmount(), vaccineTest.getVaccineId(), new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        });
        System.out.println(result);
        assertTrue(result=="adminPages/adminVaccines::vac_container");
    }


    @Test
    void fetchVaccinesTest(){
        login();
        Admin admin = iAdminService.list().get(0);
        System.out.println(admin);
        ModelAndView modelAndView = adminController.fetchVaccines();
        Map<String, Object> map = modelAndView.getModel();
        LinkedHashMap result =  (LinkedHashMap) map.get("result");
        System.out.println(result);
        assertTrue(result==null);
    }

    @Test
    void fetchVaccinesRefreshTest(){
        login();
        Admin admin = iAdminService.list().get(0);

        String result = adminController.fetchVaccinesRefresh(new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        });

        System.out.println(result);
        assertTrue(result=="adminPages/adminVaccines::vac_container");
    }

    @Test
    void registerPageTest(){
        ModelAndView result = adminController.registerPage();
        System.out.println(result);
        assertTrue(result.hasView());
        assertTrue(result.getModel()!=null);
    }


}