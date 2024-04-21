package cn.zsc.service;

import cn.zsc.entity.Course;
import cn.zsc.entity.CourseClass;
import cn.zsc.mapper.CourseClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseClassService {

   @Autowired
   private CourseClassMapper courseClassMapper;

   @Autowired
   private CourseService courseService;

   public List<Integer> findCourseIdsByTermAndClassName(String termId, String className) {
      return courseClassMapper.findCourseIdsByTermAndClassName(termId, className);
   }

}
