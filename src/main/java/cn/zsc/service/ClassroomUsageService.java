package cn.zsc.service;

import cn.zsc.entity.ClassroomUsage;
import cn.zsc.mapper.ClassroomUsageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClassroomUsageService {

    @Autowired
    private ClassroomUsageMapper classroomUsageMapper;

    public void insertClassroomUsage(ClassroomUsage classroomUsage) {
        classroomUsageMapper.insertClassroomUsage(classroomUsage);
    }

    public ClassroomUsage getAllClassroomUsage(String classroom_name, String examDate, String class_name) {
        return classroomUsageMapper.getAllClassroomUsage(classroom_name, examDate, class_name);
    }

    public ClassroomUsage getClassroomUsageWithClassroomName(String classroom_name, String examDate) {
        return classroomUsageMapper.getClassroomUsageWithClassroomName(classroom_name, examDate);
    }

    public ClassroomUsage getClassroomUsageWithClassName(String class_name, String examDate) {
        return classroomUsageMapper.getClassroomUsageWithClassName(class_name, examDate);
    }


}