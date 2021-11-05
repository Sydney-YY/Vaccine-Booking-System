package com.sydney.vacbook.service.impl;

import com.sydney.vacbook.entity.Booking;
import com.sydney.vacbook.mapper.BookingMapper;
import com.sydney.vacbook.service.IBookingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Group45
 * @since 2021-09-11
 */
@Service
public class BookingServiceImpl extends ServiceImpl<BookingMapper, Booking> implements IBookingService {

}
