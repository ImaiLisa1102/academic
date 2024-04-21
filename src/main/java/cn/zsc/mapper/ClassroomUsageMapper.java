package cn.zsc.mapper;

import cn.zsc.entity.ClassroomUsage;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ClassroomUsageMapper {
    
    @Insert("INSERT INTO classroom_usage (classroom_name, exam_date, class_name) " +
            "VALUES (#{classroom_name}, #{examDate}, #{class_name})")
    void insertClassroomUsage(ClassroomUsage classroomUsage);

    @Select("SELECT * FROM classroom_usage WHERE classroom_name = #{classroom_name} AND exam_date = #{examDate} AND class_name = #{class_name}")
    ClassroomUsage getAllClassroomUsage(@Param("classroom_name") String classroom_name, @Param("examDate") String examDate, @Param("class_name") String class_name);

    @Select("SELECT * FROM classroom_usage WHERE classroom_name LIKE CONCAT('%', #{classroom_name}, '%') AND exam_date = #{examDate}")
    ClassroomUsage getClassroomUsageWithClassroomName(@Param("classroom_name") String classroom_name, @Param("examDate") String examDate);

    @Select("SELECT * FROM classroom_usage WHERE class_name LIKE CONCAT('%', #{class_name}, '%') AND exam_date = #{examDate}")
    ClassroomUsage getClassroomUsageWithClassName(@Param("class_name") String class_name, @Param("examDate") String examDate);

}