package cn.zsc.mapper;

import cn.zsc.entity.School_Class;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface School_ClassMapper {
  @Select("SELECT * FROM school_class WHERE class_name = #{className}")
  @Result(property = "majorId", column = "major_id")
  School_Class findByName(String className);


  @Select("SELECT class_name FROM school_class WHERE major_id = #{majorId}")
  List<String> findClassNamesByMajorId(@Param("majorId") int majorId);

  @Insert("INSERT INTO school_class(class_name, class_year, major_id) VALUES(#{className}, #{classYear}, #{majorId})")
  void insertClass(School_Class schoolClass);

  @Select("SELECT * FROM school_class LIMIT #{size} OFFSET #{offset}")
  @Results({
          @Result(property = "className", column = "class_name"),
          @Result(property = "classYear", column = "class_year"),
          @Result(property = "majorId", column = "major_id")
  })
  List<School_Class> findAll(@Param("offset") int offset, @Param("size") int size);

  @Select("SELECT COUNT(*) FROM school_class")
  int count();

  @Delete("DELETE FROM school_class WHERE class_id=#{classId}")
  void deleteClass(int classId);

  @Select("SELECT class_year FROM school_class WHERE class_name = #{className}")
  int getClassYearByClassName(@Param("className") String className);

  @Update("UPDATE school_class SET class_year = #{classYear}, major_id = #{majorId} " +
          "WHERE class_name = #{className}")
  int updateSchool_Class(School_Class school_class);

  @Insert({
          "<script>",
          "INSERT INTO school_class (class_name, class_year, major_id)",
          "VALUES",
          "<foreach collection='classes' item='class' index='index' separator=','>",
          "(#{class.className}, #{class.classYear}, #{class.majorId})",
          "</foreach>",
          "</script>"
  })
  void batchInsert(@Param("classes") List<School_Class> classes);


}


