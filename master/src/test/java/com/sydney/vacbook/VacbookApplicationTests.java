package com.sydney.vacbook;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sydney.vacbook.entity.*;
import com.sydney.vacbook.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class VacbookApplicationTests {

    @Autowired
    //每一个函数都要装配 不然报空指针错误
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private VaccineMapper vaccineMapper;
    @Autowired
    private LocationMapper locationMapper;


    @Test
    void contextLoads0() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }

    @Test
    void contextLoads1() {
        List<Admin> admins = adminMapper.selectList(null);
        System.out.println(admins);
    }
    @Test
    void contextLoads2() {
        List<Booking> bookings = bookingMapper.selectList(null);
        System.out.println(bookings);
    }
    @Test
    void contextLoads3() {
        List<Vaccine> vaccines = vaccineMapper.selectList(null);
        System.out.println(vaccines);
    }
    @Test
    void contextLoads4() {
        List<Location> locations = locationMapper.selectList(null);
        System.out.println(locations);
    }
    //    插入
    @Test
    void adminAdd(){

        Admin admin = new Admin();
        admin.setAdminAccount("haha111");
        admin.setAdminName("Lucy");
        admin.setAdminPassword("123");
        admin.setLocationId(7);
        int insert = adminMapper.insert(admin);//自动生成ID
        System.out.println(insert);//受影响的行数
        System.out.println(admin);//id自动回填

    }
    @Test
    void adminDelete(){

        Admin admin1 = new Admin();
        admin1.setAdminId(7);
        int i = adminMapper.deleteById(admin1);

    }
    //    更新
    @Test
    void adminUpdate(){
        Admin admin = new Admin();
        admin.setAdminAccount("hahah2");
        admin.setAdminName("Lucy");
        admin.setLocationId(1);
        admin.setAdminPassword("changePassword");
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();//query包装包
        wrapper.eq("admin_id", "7");//更新条件
        int result = this.adminMapper.update(admin, wrapper);
        System.out.println("result = " + result);
    }

    @Test
    void selectById(){
        Admin admin = adminMapper.selectById(1);
        System.out.println(admin);
    }


}
