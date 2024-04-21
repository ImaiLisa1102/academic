package cn.zsc.entity;

import lombok.Data;
import lombok.ToString;

@Data
public class Manager {
    private int man_num;
    private String man_username;
    private String man_password;
    private String man_email;
    private String man_identity;
}
