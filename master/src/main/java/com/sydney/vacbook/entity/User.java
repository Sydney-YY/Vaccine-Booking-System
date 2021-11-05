package com.sydney.vacbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Group45
 * @since 2021-09-11
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String phoneNumber;

    private String email;

    private String userLastname;

    private String userFirstname;

    private String gender;

    private String address;

    private Integer age;

    private String userPassword;

    private String userAccount;

    private String userQuestion;

    private String userSafeKey;


    public void updateByMap(Map<String, Object> body) {
        for (Map.Entry<String, Object> item : body.entrySet()) {
            String key = item.getKey();
            Object value = item.getValue();
            switch (key) {
                case "phoneNumber":
                    this.setPhoneNumber(value.toString());
                    break;
                case "email":
                    this.setEmail(value.toString());
                    break;
                case "userLastname":
                    this.setUserLastname(value.toString());
                    break;
                case "userFirstname":
                    this.setUserFirstname(value.toString());
                    break;
                case "gender":
                    this.setGender(value.toString());
                    break;
                case "address":
                    this.setAddress(value.toString());
                    break;
                case "age":
                    this.setAge((int) value);
                    break;
                case "userPassword":
                    this.setUserPassword(value.toString());
                    break;
                case "userQuestion":
                    this.setUserQuestion(value.toString());
                    break;
                case "userSafeKey":
                    this.setUserSafeKey(value.toString());
                    break;
            }
        }
    }
}
