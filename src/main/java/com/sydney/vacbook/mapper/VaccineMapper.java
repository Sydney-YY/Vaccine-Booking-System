package com.sydney.vacbook.mapper;

import com.sydney.vacbook.entity.Vaccine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface VaccineMapper extends BaseMapper<Vaccine> {


}
