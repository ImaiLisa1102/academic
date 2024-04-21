package cn.zsc.entity;

import lombok.Data;

@Data // 使用lombok注解，简化getter和setter方法
public class Major {
  private int majorId; // 专业id
  private String majorName; // 专业名称
  private int major_collegeId; // 所属学院编号
}