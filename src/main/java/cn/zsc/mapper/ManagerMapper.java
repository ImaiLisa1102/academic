package cn.zsc.mapper;

import cn.zsc.entity.Manager;
import cn.zsc.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ManagerMapper {
   //根据num查找password
   @Select("SELECT man_password FROM manager WHERE man_num = #{man_num}")
   String findPasswordByNum(int man_num);


   @Select("SELECT * FROM manager WHERE man_num = #{man_num}")
   @Results({
           @Result(property = "man_username", column = "man_username"),
           @Result(property = "man_password", column = "man_password"),
           @Result(property = "man_num", column = "man_num"),
           @Result(property = "man_email", column = "man_email"),
           @Result(property = "man_identity", column = "man_identity")
   })
   Manager findByManagerNumber(int man_num);

   @Select("SELECT * FROM manager WHERE man_num = #{username}")
   User findUserByUsername(int username);

   @Select("SELECT * FROM manager LIMIT #{size} OFFSET #{offset}")
   @Results({
           @Result(property = "man_username", column = "man_username"),
           @Result(property = "man_password", column = "man_password"),
           @Result(property = "man_num", column = "man_num"),
           @Result(property = "man_email", column = "man_email"),
           @Result(property = "man_identity", column = "man_identity")
   })
   List<Manager> findAll(@Param("offset") int offset, @Param("size") int size);


   @Select("SELECT COUNT(*) FROM manager")
   int count();


   @Insert("INSERT INTO manager (man_username, man_password, man_num, man_email, man_identity) VALUES (#{man_username}, #{man_password}, #{man_num}, #{man_email}, #{man_identity})")
   void insertManager(Manager manager);

   @Delete("DELETE FROM manager WHERE man_num=#{man_num}")
   void deleteManager(Long man_num);


   @Select("SELECT man_identity FROM manager WHERE man_num = #{manNum}")
   String findIdentityByNum(@Param("manNum") int manNum);

   @Update("UPDATE manager SET man_username = #{man_username}, man_password = #{man_password}, " +
           "man_email = #{man_email}, man_identity = #{man_identity} " +
           "WHERE man_num = #{man_num}")
   int updateManager(Manager manager);


   @Insert({
           "<script>",
           "INSERT INTO manager (man_num, man_username, man_password, man_email, man_identity)",
           "VALUES",
           "<foreach collection='managers' item='manager' index='index' separator=','>",
           "(#{manager.man_num}, #{manager.man_username}, #{manager.man_password},",
           "#{manager.man_email}, #{manager.man_identity})",
           "</foreach>",
           "</script>"
   })
   void batchInsert(@Param("managers") List<Manager> managers);

}
