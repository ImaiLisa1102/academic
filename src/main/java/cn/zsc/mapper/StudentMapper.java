package cn.zsc.mapper;

import cn.zsc.entity.Student;
import cn.zsc.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {


   @Select("SELECT * FROM student WHERE stu_num = #{stu_num}")
   @Results({
           @Result(property = "stu_username", column = "stu_username"),
           @Result(property = "stu_password", column = "stu_password"),
           @Result(property = "stu_num", column = "stu_num"),
           @Result(property = "stu_email", column = "stu_email"),
           @Result(property = "stu_class", column = "stu_class"),
           @Result(property = "stu_identity", column = "stu_identity")
   })
   Student findByStudentNumber(int stu_num);

   @Select("SELECT * FROM student WHERE stu_num = #{username}")
   User findUserByUsername(int username);

   @Insert("INSERT INTO student(stu_username, stu_password, stu_num, stu_email, stu_class, stu_identity) VALUES(#{stu_username}, #{stu_password}, #{stu_num}, #{stu_email}, #{stu_class}, #{stu_identity})")
   void insertall(Student student);

   @Select("SELECT * FROM student LIMIT #{size} OFFSET #{offset}")
   @Results({
           @Result(property = "stu_username", column = "stu_username"),
           @Result(property = "stu_password", column = "stu_password"),
           @Result(property = "stu_num", column = "stu_num"),
           @Result(property = "stu_email", column = "stu_email"),
           @Result(property = "stu_class", column = "stu_class"),
           @Result(property = "stu_identity", column = "stu_identity")
   })
   List<Student> findAll(@Param("offset") int offset, @Param("size") int size);

   @Select("SELECT COUNT(*) FROM student")
   int count();


   @Delete("DELETE FROM student WHERE stu_num=#{stu_num}")
   void deleteStudent(Long stu_num);

   @Insert("INSERT INTO student (stu_username, stu_password, stu_num, stu_email, stu_class, stu_identity) VALUES (#{stu_username}, #{stu_password}, #{stu_num}, #{stu_email}, #{stu_class}, #{stu_identity})")
   void insertStudent(Student student);

   // 查询班级内学生数量
   @Select("SELECT COUNT(*) FROM student WHERE stu_class = #{className}")
   Long countStudentsInClass(@Param("className") String className);

   @Select("SELECT stu_identity FROM student WHERE stu_num = #{stuNum}")
   String findIdentityByNum(@Param("stuNum") int stuNum);

   @Select("SELECT * FROM student WHERE stu_num = #{stuNum}")
   @Results({
           @Result(property = "stu_username", column = "stu_username"),
           @Result(property = "stu_password", column = "stu_password"),
           @Result(property = "stu_num", column = "stu_num"),
           @Result(property = "stu_email", column = "stu_email"),
           @Result(property = "stu_class", column = "stu_class"),
           @Result(property = "stu_identity", column = "stu_identity")
   })
   Student findByStuNum(int stuNum);

   @Update("UPDATE student SET stu_username = #{stu_username}, stu_password = #{stu_password}, " +
           "stu_email = #{stu_email}, stu_class = #{stu_class}, stu_identity = #{stu_identity} " +
           "WHERE stu_num = #{stu_num}")
   int updateStudent(Student student);

   @Select("SELECT stu_class FROM student WHERE stu_num = #{stu_num}")
   String findClassByStuNum(int stu_num);

   @Insert({
           "<script>",
           "INSERT INTO student (stu_username, stu_password, stu_num, stu_email, stu_class, stu_identity) VALUES",
           "<foreach collection='list' item='student' index='index' separator=','>",
           "(#{student.stu_username}, #{student.stu_password}, #{student.stu_num}, #{student.stu_email}, #{student.stu_class}, #{student.stu_identity})",
           "</foreach>",
           "</script>"
   })
   int batchInsert(@Param("list") List<Student> students);

   //根据学生id查询学生密码
   @Select("SELECT stu_password FROM student WHERE stu_num = #{stu_num}")
   String findPasswordByNum(int stu_num);

   //根据学生id修改密码
   @Update("UPDATE student SET stu_password = #{stu_password} WHERE stu_num = #{stu_num}")
   int updatePasswordById(@Param("stu_num") String stuNum, @Param("stu_password") String newPassword);

}
