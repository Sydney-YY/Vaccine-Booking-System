package com.sydney.vacbook.service.impl;

import com.sydney.vacbook.entity.Admin;
import com.sydney.vacbook.mapper.AdminMapper;
import com.sydney.vacbook.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Group45
 * @since 2021-09-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    

}
