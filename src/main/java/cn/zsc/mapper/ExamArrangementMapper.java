package cn.zsc.mapper;

import cn.zsc.entity.ExamArrangement;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExamArrangementMapper {


    @Insert("INSERT INTO exam_arrangement (exam_major_name, exam_course,  exam_class_name, exam_classroom_name, exam_term, exam_date, exam_main_teacher, exam_sub_teacher1, exam_sub_teacher2, exam_form) " +
            "VALUES (#{exam_major_name}, #{exam_course},  #{exam_class_name}, #{exam_classroom_name}, #{exam_term}, #{exam_date}, #{exam_main_teacher}, #{exam_sub_teacher1}, #{exam_sub_teacher2}, #{exam_form})")
    @Options(useGeneratedKeys = true, keyProperty = "exam_id", keyColumn = "exam_id")
    void insertExamArrangement(ExamArrangement examArrangement);


    @Select("SELECT * FROM exam_arrangement WHERE exam_date = #{examDate} AND (exam_main_teacher = #{teacherName} )")
    List<ExamArrangement> getExamByDateAndMainTeacher(@Param("examDate") String examDate, @Param("teacherName") String teacherName);


    @Select("SELECT * FROM exam_arrangement WHERE exam_date = #{examDate} AND (exam_main_teacher = #{teacherName} )")
    @Results({
            @Result(property = "exam_id", column = "exam_id"),
            @Result(property = "exam_major_name", column = "exam_major_name"),
            @Result(property = "exam_course", column = "exam_course"),
            @Result(property = "exam_class_name", column = "exam_class_name"),
            @Result(property = "exam_classroom_name", column = "exam_classroom_name"),
            @Result(property = "exam_term", column = "exam_term"),
            @Result(property = "exam_date", column = "exam_date"),
            @Result(property = "exam_main_teacher", column = "exam_main_teacher"),
            @Result(property = "exam_sub_teacher1", column = "exam_sub_teacher1"),
            @Result(property = "exam_sub_teacher2", column = "exam_sub_teacher2"),
            @Result(property = "exam_form", column = "exam_form")
    })
    List<ExamArrangement> getExamByDateAndSubTeacher1(@Param("examDate") String examDate, @Param("teacherName") String teacherName);

    @Select("SELECT * FROM exam_arrangement WHERE exam_date = #{examDate} AND (exam_main_teacher = #{teacherName} )")
    @Results({
            @Result(property = "exam_id", column = "exam_id"),
            @Result(property = "exam_major_name", column = "exam_major_name"),
            @Result(property = "exam_course", column = "exam_course"),
            @Result(property = "exam_class_name", column = "exam_class_name"),
            @Result(property = "exam_classroom_name", column = "exam_classroom_name"),
            @Result(property = "exam_term", column = "exam_term"),
            @Result(property = "exam_date", column = "exam_date"),
            @Result(property = "exam_main_teacher", column = "exam_main_teacher"),
            @Result(property = "exam_sub_teacher1", column = "exam_sub_teacher1"),
            @Result(property = "exam_sub_teacher2", column = "exam_sub_teacher2"),
            @Result(property = "exam_form", column = "exam_form")
    })
    List<ExamArrangement> getExamByDateAndSubTeacher2(@Param("examDate") String examDate, @Param("teacherName") String teacherName);

    @Select("SELECT * FROM exam_arrangement WHERE exam_class_name = #{examClassName}")
    @Results({
            @Result(property = "exam_id", column = "exam_id"),
            @Result(property = "exam_major_name", column = "exam_major_name"),
            @Result(property = "exam_course", column = "exam_course"),
            @Result(property = "exam_class_name", column = "exam_class_name"),
            @Result(property = "exam_classroom_name", column = "exam_classroom_name"),
            @Result(property = "exam_term", column = "exam_term"),
            @Result(property = "exam_date", column = "exam_date"),
            @Result(property = "exam_main_teacher", column = "exam_main_teacher"),
            @Result(property = "exam_sub_teacher1", column = "exam_sub_teacher1"),
            @Result(property = "exam_sub_teacher2", column = "exam_sub_teacher2"),
            @Result(property = "exam_form", column = "exam_form")
    })
    List<ExamArrangement> findByClassName(@Param("examClassName") String examClassName);

// 您可能需要更新该接口以包含列出主监考和副监考的两个查询方法

    @Select("SELECT *, 'MAIN' AS supervision_role FROM exam_arrangement WHERE exam_main_teacher = #{teacherIdentity}")
    @Results({
            @Result(property = "exam_id", column = "exam_id"),
            @Result(property = "exam_major_name", column = "exam_major_name"),
            @Result(property = "exam_course", column = "exam_course"),
            @Result(property = "exam_class_name", column = "exam_class_name"),
            @Result(property = "exam_classroom_name", column = "exam_classroom_name"),
            @Result(property = "exam_term", column = "exam_term"),
            @Result(property = "exam_date", column = "exam_date"),
            @Result(property = "exam_main_teacher", column = "exam_main_teacher"),
            @Result(property = "exam_sub_teacher1", column = "exam_sub_teacher1"),
            @Result(property = "exam_sub_teacher2", column = "exam_sub_teacher2"),
            @Result(property = "exam_form", column = "exam_form")
    })
    List<ExamArrangement> findMainSupervisionByTeacherIdentity(@Param("teacherIdentity") String teacherIdentity);

    @Select("SELECT * FROM exam_arrangement WHERE exam_sub_teacher1 = #{teacherIdentity} OR exam_sub_teacher2 = #{teacherIdentity}")
    @Results({
            @Result(property = "exam_id", column = "exam_id"),
            @Result(property = "exam_major_name", column = "exam_major_name"),
            @Result(property = "exam_course", column = "exam_course"),
            @Result(property = "exam_class_name", column = "exam_class_name"),
            @Result(property = "exam_classroom_name", column = "exam_classroom_name"),
            @Result(property = "exam_term", column = "exam_term"),
            @Result(property = "exam_date", column = "exam_date"),
            @Result(property = "exam_main_teacher", column = "exam_main_teacher"),
            @Result(property = "exam_sub_teacher1", column = "exam_sub_teacher1"),
            @Result(property = "exam_sub_teacher2", column = "exam_sub_teacher2"),
            @Result(property = "exam_form", column = "exam_form")
    })
    List<ExamArrangement> findAssistantSupervisionByTeacherIdentity(@Param("teacherIdentity") String teacherIdentity);

    @Select("SELECT * FROM exam_arrangement WHERE exam_major_name = #{examMajorName}")
    @Results({
            @Result(property = "exam_id", column = "exam_id"),
            @Result(property = "exam_major_name", column = "exam_major_name"),
            @Result(property = "exam_course", column = "exam_course"),
            @Result(property = "exam_class_name", column = "exam_class_name"),
            @Result(property = "exam_classroom_name", column = "exam_classroom_name"),
            @Result(property = "exam_term", column = "exam_term"),
            @Result(property = "exam_date", column = "exam_date"),
            @Result(property = "exam_main_teacher", column = "exam_main_teacher"),
            @Result(property = "exam_sub_teacher1", column = "exam_sub_teacher1"),
            @Result(property = "exam_sub_teacher2", column = "exam_sub_teacher2"),
            @Result(property = "exam_form", column = "exam_form")
    })
    List<ExamArrangement> findByMajorName(@Param("examMajorName") String examMajorName);



}
