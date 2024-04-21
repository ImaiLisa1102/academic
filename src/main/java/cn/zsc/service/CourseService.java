package cn.zsc.service;

import cn.zsc.entity.Course;
import cn.zsc.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    public Course findCourseById(int courseId) {
        return courseMapper.findById(courseId);
    }

    public List<Course> findAll(int offset, int size) {
        return courseMapper.findAll(offset,size);
    }


    public int count() {
        return courseMapper.count();
    }

    public void deleteCourse(Long classroom_id) {
        courseMapper.deleteCourse(classroom_id);
    }

    public void insertCourse(Course course) {
        courseMapper.insertCourse(course);
    }

    public boolean updateCourseInfo(Course course) {
        return courseMapper.updateCourse(course) > 0;
    }

    public void batchInsertCourses(List<Course> courses) {
        courseMapper.batchInsert(courses);
    }



}