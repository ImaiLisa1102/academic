package cn.zsc.mapper;


import cn.zsc.entity.CourseClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseClassMapper {
   @Select("SELECT course_id FROM courseclass WHERE term_id = #{termId} AND class_name = #{className}")
   List<Integer> findCourseIdsByTermAndClassName(@Param("termId") String termId, @Param("className") String className);
}
