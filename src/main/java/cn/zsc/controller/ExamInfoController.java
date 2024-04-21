package cn.zsc.controller;

import cn.zsc.entity.ExamArrangement;
import cn.zsc.service.ExamArrangementService;
import cn.zsc.service.StudentService;
import cn.zsc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/examinfo")
public class ExamInfoController {

    @Autowired
    private ExamArrangementService examArrangementService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/class/{stu_num}")
    public ResponseEntity<List<ExamArrangement>> getExamArrangementByClassName(@PathVariable int stu_num) {
        try {
            String className = studentService.findClassByStuNum(stu_num);
            if (className == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<ExamArrangement> examArrangements = examArrangementService.findByClassName(className);

            // 添加日志或调试语句来验证数据
            System.out.println("Exam arrangements: " + examArrangements);

            if (examArrangements.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(examArrangements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Map<String, Object>>> getSupervisedExamsByTeacher(@PathVariable int teacherId) {
        String teacherIdentity = teacherService.getTeacherIdentityByNum(teacherId);
        List<ExamArrangement> supervisedExams = examArrangementService.getSupervisedExamsByTeacherIdentity(teacherIdentity);

        if (supervisedExams == null || supervisedExams.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Map<String, Object>> examsWithRoles = supervisedExams.stream().map(exam -> {
            Map<String, Object> examMap = new HashMap<>();
            examMap.put("exam_id", exam.getExam_id());
            examMap.put("exam_major_name", exam.getExam_major_name());
            examMap.put("exam_course", exam.getExam_course());
            examMap.put("exam_class_name", exam.getExam_class_name());
            examMap.put("exam_classroom_name", exam.getExam_classroom_name());
            examMap.put("exam_term", exam.getExam_term());
            examMap.put("exam_date", exam.getExam_date());
            examMap.put("exam_main_teacher", exam.getExam_main_teacher());
            examMap.put("exam_sub_teacher1", exam.getExam_sub_teacher1());
            examMap.put("exam_sub_teacher2", exam.getExam_sub_teacher2());
            examMap.put("exam_form", exam.getExam_form());

            // 确定监考角色并放入map
            String role = "UNKNOWN"; // 默认值
            if (teacherIdentity.equals(exam.getExam_main_teacher())) {
                role = "MAIN"; // 主监考
            } else if (teacherIdentity.equals(exam.getExam_sub_teacher1()) || teacherIdentity.equals(exam.getExam_sub_teacher2())) {
                role = "ASSISTANT"; // 副监考
            }
            examMap.put("supervisionRole", role);

            return examMap;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(examsWithRoles);
    }

    @GetMapping("/search/major")
    public ResponseEntity<List<ExamArrangement>> getExamArrangementsByMajor(@RequestParam String major) {
        List<ExamArrangement> examArrangements = examArrangementService.getExamArrangementsByMajor(major);
        if (examArrangements.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(examArrangements);
        }
    }

    @GetMapping("/search/classname")
    public ResponseEntity<List<ExamArrangement>> getExamArrangementsByClassName(@RequestParam("classname") String className) {
        List<ExamArrangement> examArrangements = examArrangementService.findByClassName(className);
        if (examArrangements == null || examArrangements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(examArrangements);
    }

}