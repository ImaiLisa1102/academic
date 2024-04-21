package cn.zsc.mapper;

import cn.zsc.entity.Manager;
import cn.zsc.entity.Teacher;
import cn.zsc.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {


    @Select("SELECT * FROM teacher WHERE tea_num = #{tea_num}")
    @Results({
            @Result(property = "tea_num", column = "tea_num"),
            @Result(property = "tea_identity", column = "tea_identity"),
            @Result(property = "tea_username", column = "tea_username"),
            @Result(property = "tea_password", column = "tea_password"),
            @Result(property = "tea_email", column = "tea_email")
    })
    Teacher findByTeacherNumber(int tea_num);
    @Select("SELECT * FROM teacher WHERE tea_num = #{username}")
    User findUserByUsername(int username);

    @Select("SELECT * FROM teacher LIMIT #{size} OFFSET #{offset}")
    @Results({
            @Result(property = "tea_num", column = "tea_num"),
            @Result(property = "tea_identity", column = "tea_identity"),
            @Result(property = "tea_username", column = "tea_username"),
            @Result(property = "tea_password", column = "tea_password"),
            @Result(property = "tea_email", column = "tea_email")
    })
    List<Teacher> findAll(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM teacher")
    int count();

    @Delete("DELETE FROM teacher WHERE tea_num=#{tea_num}")
    void deleteTeacher(Long tea_num);

    @Insert("INSERT INTO teacher (tea_username, tea_password, tea_num, tea_email, tea_identity) VALUES (#{tea_username}, #{tea_password}, #{tea_num}, #{tea_email}, #{tea_identity})")
    void insertTeacher(Teacher teacher);

    @Select("SELECT tea_identity FROM teacher")
    List<String> getAllTeacherNames();

    @Select("SELECT tea_identity FROM teacher WHERE tea_num = #{teaNum}")
    String findIdentityByNum(@Param("teaNum") int teaNum);

    @Select("SELECT * FROM teacher WHERE tea_num = #{teaNum}")
    @Results({
            @Result(property = "tea_num", column = "tea_num"),
            @Result(property = "tea_identity", column = "tea_identity"),
            @Result(property = "tea_username", column = "tea_username"),
            @Result(property = "tea_password", column = "tea_password"),
            @Result(property = "tea_email", column = "tea_email")
    })
    Teacher findByTeaNum(int teaNum);

    @Update("UPDATE teacher SET tea_username = #{tea_username}, tea_password = #{tea_password}, " +
            "tea_email = #{tea_email}, tea_identity = #{tea_identity} " +
            "WHERE tea_num = #{tea_num}")
    int updateTeacher(Teacher teacher);

    @Insert({
            "<script>",
            "INSERT INTO teacher (tea_num, tea_username, tea_password, tea_email, tea_identity)",
            "VALUES ",
            "<foreach collection='teachers' item='teacher' index='index' separator=','>",
            "(#{teacher.tea_num}, #{teacher.tea_username}, #{teacher.tea_password}, ",
            "#{teacher.tea_email}, #{teacher.tea_identity})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("teachers") List<Teacher> teachers);

    //根据编号查找老师密码
    @Select("SELECT tea_password FROM teacher WHERE tea_num = #{tea_num}")
    String findPasswordByNum(int tea_num);

    //根据编号修改老师密码
    @Update("UPDATE teacher SET tea_password = #{tea_password} WHERE tea_num = #{tea_num}")
    int updatePasswordByNum(@Param("tea_num") int teaNum, @Param("tea_password") String newPassword);

}

