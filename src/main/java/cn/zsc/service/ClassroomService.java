package cn.zsc.service;

import cn.zsc.controller.ExamArrangementController;
import cn.zsc.entity.Classroom;
import cn.zsc.entity.Course;
import cn.zsc.mapper.ClassroomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomMapper classroomMapper;
    private final Logger logger = LoggerFactory.getLogger(ClassroomService.class);


    public Classroom findClassroomById(int collegeId) {
        return classroomMapper.findById(collegeId);
    }

    public List<Classroom> findAll(int offset, int size) {
        return classroomMapper.findAll(offset,size);
    }

    public int count() {
        return classroomMapper.count();
    }

    public void deleteClassroom(Long classroom_id) {
        classroomMapper.deleteClassroom(classroom_id);
    }

    public void insertClassroom(Classroom classroom) {
        classroomMapper.insertClassroom(classroom);
    }

    public List<Classroom> getAllAvailableClassrooms() {
            List<Classroom> classroom = classroomMapper.getAllAvailableClassrooms();
            return classroom;
    }

    public int getCapacity(String classroom_name){
        int classroom_capacity = classroomMapper.getCapacity(classroom_name);
        return classroom_capacity;
    }

    public boolean updateClassroomInfo(Classroom classroom) {
        return classroomMapper.updateClassroom(classroom) > 0;
    }

    public void batchInsertClassrooms(List<Classroom> classrooms) {
        classroomMapper.batchInsert(classrooms);
    }


}