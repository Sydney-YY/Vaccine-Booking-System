package com.sydney.vacbook.mapper;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.sydney.vacbook.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Group45
 * @since 2021-09-11
 */
@Repository
@Mapper
//相当于自动扫描（mapperScan）
public interface AdminMapper extends BaseMapper<Admin> {


}
