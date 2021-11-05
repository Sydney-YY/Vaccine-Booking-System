package com.sydney.vacbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Admin implements Serializable {

    //private static final long serialVersionUID = 1L;

    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    private String adminAccount;

    private String adminPassword;

    private String adminName;

    private Integer locationId;

    public void updateByMap(Map<String, Object> body) {
        for (Map.Entry<String, Object> item : body.entrySet()) {
            String key = item.getKey();
            Object value = item.getValue();
            switch (key) {
                case "adminName":
                    this.setAdminName(value.toString());
                    break;
                case "adminPassword":
                    this.setAdminPassword(value.toString());
                    break;
                case "locationId":
                    this.setLocationId((int) value);
                    break;
            }
        }
    }

}
