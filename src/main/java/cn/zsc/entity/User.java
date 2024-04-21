package cn.zsc.entity;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String password;
    public User(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    public User() {
    }
}
