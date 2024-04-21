package cn.zsc.entity;

import lombok.Data;

@Data
public class Classroom {
    private int classroom_id;
    private String classroom_name;
    private int classroom_capacity;
    private String classroom_state;
}