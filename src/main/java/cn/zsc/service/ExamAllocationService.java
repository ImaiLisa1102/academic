package cn.zsc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExamAllocationService {


    @Autowired
    private StudentService studentService;


    // 分配教室的方法
    public List<Map<String, Object>> assignClassrooms(List<Map<String, Object>> allocationResults) {
        List<Map<String, Object>> updatedAllocationResults = new ArrayList<>();

        for (Map<String, Object> exam : allocationResults) {
            // 分割班级名和教室名
            String[] classes = ((String) exam.get("class_name")).split("/");
            String[] classrooms = ((String) exam.get("classroomName")).split("/");

            // 获取班级的学生人数
            long studentCountClass1 = studentService.getStudentCountInClass(classes[0]);
            long studentCountClass2 = studentService.getStudentCountInClass(classes[1]);

            // 根据学生人数分配教室
            if (studentCountClass1 > studentCountClass2) {
                exam.put("classroomForClass1", classrooms[0]); // 人数多的班级到第一个教室
                exam.put("classroomForClass2", classrooms[1]); // 人数少的班级到第二个教室
            } else {
                exam.put("classroomForClass1", classrooms[1]); // 人数多的班级到第二个教室
                exam.put("classroomForClass2", classrooms[0]); // 人数少的班级到第一个教室
            }

            updatedAllocationResults.add(exam);
        }

        return updatedAllocationResults;
    }
}