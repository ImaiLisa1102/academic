package cn.zsc.mapper;

import cn.zsc.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("SELECT * FROM course WHERE course_id = #{courseId}")
    @Results({
            @Result(property = "course_id", column = "course_id"),
            @Result(property = "course_name", column = "course_name"),
            @Result(property = "major_id", column = "major_id")
    })
    Course findById(int courseId);


    @Select("SELECT * FROM course LIMIT #{size} OFFSET #{offset}")
    @Results({
            @Result(property = "course_id", column = "course_id"),
            @Result(property = "course_name", column = "course_name"),
            @Result(property = "major_id", column = "major_id")
    })
    List<Course> findAll(@Param("offset") int offset, @Param("size") int size);


    @Select("SELECT COUNT(*) FROM course")
    int count();
    @Delete("DELETE FROM course WHERE course_id = #{course_id}")
    void deleteCourse(@Param("course_id") Long course_id);


    @Insert("INSERT INTO course (course_id, course_name, major_id) VALUES ( #{course_id}, #{course_name}, #{major_id})")
    void insertCourse(Course course);


    @Update("UPDATE course SET course_name = #{course_name}, major_id = #{major_id} " +
            "WHERE course_id = #{course_id}")
    int updateCourse(Course course);

    @Insert({
            "<script>",
            "INSERT INTO course (course_id, course_name, major_id)",
            "VALUES",
            "<foreach collection='courses' item='course' index='index' separator=','>",
            "(#{course.course_id}, #{course.course_name}, #{course.major_id})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("courses") List<Course> courses);

    @Insert("INSERT INTO course (course_id, course_name, major_id) VALUES (#{course_id}, #{course_name}, #{major_id})")
    void insertCourseWithId(Course course);
}