package com.sydney.vacbook;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sydney.vacbook.controller.AdminController;
import com.sydney.vacbook.controller.BookingController;
import com.sydney.vacbook.controller.UserController;
import com.sydney.vacbook.controller.VaccineController;
import com.sydney.vacbook.entity.*;
import com.sydney.vacbook.mapper.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BookingUnitTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private VaccineMapper vaccineMapper;
    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private BookingController bookingController;
    @Autowired
    private AdminController adminController;
    @Autowired
    private UserController userController;
    @Autowired
    private VaccineController vaccineController;



    @Test
    void bookingRejectTest() {
        Booking booking = new Booking();
        booking.setBookingId(1); //数据库里的booking id
        //bookingController.reject(booking);
        assertTrue(bookingController.reject(booking));
    }

    @Test
    void bookingFetchTest() {
        Booking booking = new Booking();
        Vaccine vaccine = new Vaccine();
        booking.setUserId(48);
        booking.setVaccine_name("pfizer");
        booking.setVaccine("pfizer");
        vaccine.setVaccineName("pfizer");
        booking.setBookingId(2);
        booking.setVaccineId(3);
        booking.setBookingTimezone("23:00");
        booking.setDate("2021-10-27");
       //bookingController.fetchBooking(booking);
        assertFalse(bookingController.fetchBooking(booking));
    }

    @Test
    void bookingDelTest(){
        Booking booking = new Booking();
        booking.setBookingId(1);
        assertTrue(bookingController.bookingDelete(booking));
    }

    @Test
    void bookingConfirmTest(){
        Booking booking = new Booking();
        booking.setBookingId(1);
        booking.setDate("2021-10-27");
        booking.setBookingTimezone("08:00");
        assertTrue(bookingController.confirmBooking(booking));
    }
    @Test
    void bookingEditTest(){
        Booking booking = new Booking();
        booking.setBookingId(1);
        booking.setDate("2021-11-27");
        booking.setBookingTimezone("23:00");
        assertTrue(bookingController.editBooking(booking));
    }
    @Test
    void bookingUpdateTest(){
        Booking booking = new Booking();
        booking.setBookingId(1);
        booking.setDate("2021-01-27");
        booking.setBookingTimezone("15:00");
        assertTrue(bookingController.bookingUpdate(booking));
    }

    @Test
    void bookingAddTest(){
        Booking booking = new Booking();
        booking.setUserId(48);
        booking.setVaccineId(33);
        booking.setDate("2021-01-27");
        booking.setBookingTimezone("15:00");
        assertTrue(bookingController.bookingAdd(booking));
    }

    @Test
    void userBookingsTest(){
        Booking booking = new Booking();
        booking.setUserId(48);
        booking.setVaccineId(33);
        booking.setDate("2021-01-27");
        booking.setBookingTimezone("15:00");
        assertTrue(bookingController.bookingAdd(booking));
    }
    @Test
    void adminBookingTest(){
        adminController.login("Admin_1","1234",new HashMap<>());
        ModelAndView modelAndView = adminController.fetchBookings();
        Map<String, Object> model = modelAndView.getModel();
        List<Booking> bookingList1 = (List<Booking>) model.get("bookingList1");
        assertNotNull(bookingList1);
    }


}