// College实体类
package cn.zsc.entity;

import lombok.Data;

@Data // 使用lombok注解，简化getter和setter方法
public class College {
  private int collegeId; // 学院id
  private String collegeName; // 学院名称
  private String collegeLeader; // 学院负责人
}