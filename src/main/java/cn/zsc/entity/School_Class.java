package cn.zsc.entity;

import lombok.Data;

@Data // 使用lombok注解，简化getter和setter方法
public class School_Class {

  private String className; // 班级名称
  private int classYear; // 班级年份
  private int majorId; // 所属专业编号
}