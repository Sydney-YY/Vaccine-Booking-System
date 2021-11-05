package com.sydney.vacbook.service.impl;

import com.sydney.vacbook.entity.User;
import com.sydney.vacbook.mapper.UserMapper;
import com.sydney.vacbook.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
