//package cn.zsc.controller;
//
//import cn.zsc.entity.*;
//import cn.zsc.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class ResourceManagementController {
//
//    @Autowired
//    private ClassroomService classroomService;
//
//    @Autowired
//    private CollegeService collegeService;
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private MajorService majorService;
//
//    @Autowired
//    private School_ClassService schoolClassService;
//
//    @Autowired
//    private TermService termService;
//
//    // 教室管理
//    @GetMapping("/find/classroom/{classroomId}")
//    public ResponseEntity<Classroom> getClassroomById(@PathVariable int classroomId) {
//        try {
//            Classroom classroom = classroomService.findClassroomById(classroomId);
//            return classroom != null ? ResponseEntity.ok(classroom)
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PutMapping("/update/classroom")
//    public ResponseEntity<String> updateClassroom(@RequestBody Classroom classroom) {
//        boolean updated = classroomService.updateClassroomInfo(classroom);
//        return updated ? ResponseEntity.ok("教室信息更新成功")
//            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("更新教室信息失败");
//    }
//
//    @DeleteMapping("/delete/classroom/{classroomId}")
//    public ResponseEntity<Void> deleteClassroom(@PathVariable Long classroomId) {
//        classroomService.deleteClassroom(classroomId);
//        return ResponseEntity.ok().build();
//    }
//
//    // 学院管理
//    @GetMapping("/find/college/{collegeId}")
//    public ResponseEntity<College> getCollegeById(@PathVariable int collegeId) {
//        try {
//            College college = collegeService.findCollegeById(collegeId);
//            return college != null ? ResponseEntity.ok(college)
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PutMapping("/update/college")
//    public ResponseEntity<String> updateCollege(@RequestBody College college) {
//        boolean updated = collegeService.updateCollegeInfo(college);
//        return updated ? ResponseEntity.ok("学院信息更新成功")
//            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("更新学院信息失败");
//    }
//
//    @DeleteMapping("/delete/college/{collegeId}")
//    public void deleteCollege(@PathVariable int collegeId) {
//        collegeService.deleteCollege(collegeId);
//    }
//
//    // 课程管理
//    @GetMapping("/find/course/{courseId}")
//    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {
//        try {
//            Course course = courseService.findCourseById(courseId);
//            return course != null ? ResponseEntity.ok(course)
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PutMapping("/update/course")
//    public ResponseEntity<String> updateCourse(@RequestBody Course course) {
//        boolean updated = courseService.updateCourseInfo(course);
//        return updated ? ResponseEntity.ok("课程信息更新成功")
//            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("更新课程信息失败");
//    }
//
//    @DeleteMapping("/delete/course/{courseId}")
//    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
//        courseService.deleteCourse(courseId);
//        return ResponseEntity.ok().build();
//    }
//
//    // 专业管理
//    @GetMapping("/find/major/{majorId}")
//    public ResponseEntity<Major> getMajorById(@PathVariable int majorId) {
//        try {
//            Major major = majorService.findMajorById(majorId);
//            return major != null ? ResponseEntity.ok(major)
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PutMapping("/update/major")
//    public ResponseEntity<String> updateMajor(@RequestBody Major major) {
//        boolean updated = majorService.updateMajorInfo(major);
//        return updated ? ResponseEntity.ok("专业信息更新成功")
//            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("更新专业信息失败");
//    }
//
//    @DeleteMapping("/delete/major/{majorId}")
//    public ResponseEntity<Void> deleteMajor(@PathVariable int majorId) {
//        majorService.deleteMajor(majorId);
//        return ResponseEntity.ok().build();
//    }
//
//    // 班级管理
//    @GetMapping("/find/class/{className}")
//    public ResponseEntity<School_Class> getSchoolClassByName(@PathVariable String className) {
//        try {
//            School_Class schoolClass = schoolClassService.findSchoolClassByName(className);
//            return schoolClass != null ? ResponseEntity.ok(schoolClass)
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PutMapping("/update/class")
//    public ResponseEntity<String> updateSchoolClass(@RequestBody School_Class schoolClass) {
//        boolean updated = schoolClassService.updateSchool_ClassInfo(schoolClass);
//        return updated ? ResponseEntity.ok("班级信息更新成功")
//            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("更新班级信息失败");
//    }
//
//    @DeleteMapping("/delete/class/{classId}")
//    public ResponseEntity<Void> deleteClass(@PathVariable int classId) {
//        schoolClassService.deleteClass(classId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/singleimport/classroom")
//    public ResponseEntity<String> addClassroom(@RequestBody Classroom classroom) {
//        try {
//            classroomService.insertClassroom(classroom);
//            return ResponseEntity.status(HttpStatus.CREATED).body("教室添加成功。");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加教室失败：" + e.getMessage());
//        }
//    }
//
//    // 添加学院、专业、班级、学期的方法相同逻辑
//    @PostMapping("/singleimport/college")
//    public ResponseEntity<String> addCollege(@RequestBody College college) {
//        try {
//            collegeService.insertCollege(college);
//            return ResponseEntity.status(HttpStatus.CREATED).body("学院添加成功。");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加学院失败：" + e.getMessage());
//        }
//    }
//
//    @PostMapping("/singleimport/major")
//    public ResponseEntity<String> addMajor(@RequestBody Major major) {
//        try {
//            majorService.insertMajor(major);
//            return ResponseEntity.status(HttpStatus.CREATED).body("专业添加成功。");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加专业失败：" + e.getMessage());
//        }
//    }
//
//    @PostMapping("/singleimport/class")
//    public ResponseEntity<String> addClass(@RequestBody School_Class schoolClass) {
//        try {
//            schoolClassService.insertClass(schoolClass);
//            return ResponseEntity.status(HttpStatus.CREATED).body("班级添加成功。");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加班级失败：" + e.getMessage());
//        }
//    }
//
//    @PostMapping("/singleimport/term")
//    public ResponseEntity<String> addTerm(@RequestBody Term term) {
//        try {
//            termService.insertTerm(term);
//            return ResponseEntity.status(HttpStatus.CREATED).body("学期添加成功。");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加学期失败：" + e.getMessage());
//        }
//    }
//    //学期的删改查
//    @GetMapping("/list/term")
//    public Map<String, Object> getTermList(@RequestParam(defaultValue = "1") Integer page,
//                                           @RequestParam(defaultValue = "10") Integer size) {
//        int offset = (page - 1) * size;
//        List<Term> terms = termService.findAll(offset, size);
//        int total = termService.count();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("items", terms);
//        response.put("total", total);
//        return response;
//    }
//
//    @PutMapping("/update/term")
//    public ResponseEntity<String> updateTerm(@RequestBody Term term) {
//        return termService.updateTermInfo(term)
//                ? ResponseEntity.ok("Term updated successfully")
//                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating term");
//    }
//
//    @DeleteMapping("/delete/term/{termId}")
//    public void deleteTerm(@PathVariable String termId) {
//        termService.deleteTerm(termId);
//    }
//
//}
