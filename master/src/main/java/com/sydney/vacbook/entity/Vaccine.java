package com.sydney.vacbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author Group45
 * @since 2021-09-11
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Vaccine implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "vaccine_id", type = IdType.AUTO)
    private Integer vaccineId;

    private String vaccineType;

    private String vaccineName;

    private Integer adminId;

    private Integer vaccineAmount;


//    for test
    public Vaccine(int id,  String mRNA, String pfizer, int admin, int i1) {
        vaccineType = mRNA;
        vaccineName = pfizer;
        adminId = admin;
        vaccineAmount = i1;
        vaccineId = id;
    }
}
