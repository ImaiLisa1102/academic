// Term实体类
package cn.zsc.entity;

import lombok.Data;

import java.sql.Date;

@Data // 使用lombok注解，简化getter和setter方法
public class Term {

  private String termId; // 学期id

  private String termName; // 学期名称

  private Date termStartDate; // 学期开始时间

  private Date termEndDate; //学期结束时间

}