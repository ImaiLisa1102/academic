package cn.zsc.entity;

import lombok.Data;
import lombok.ToString;

@Data
public class Teacher {
    private String tea_username;
    private String tea_password;
    private int tea_num;
    private String tea_email;
    private String tea_identity;
}
