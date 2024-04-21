package cn.zsc.service;

import cn.zsc.entity.Student;
import cn.zsc.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    //根据num查找密码
    public String findPasswordByNum(int num) {
        return studentMapper.findPasswordByNum(num);
    }

    public Student findStudentByStudentNumber(int stu_num) {
        return studentMapper.findByStudentNumber(stu_num);
    }

    public List<Student> findAll(int offset, int size){
        List<Student> studentlist = studentMapper.findAll(offset,size);
        return studentlist;
    }

    public void deleteStudent(Long stu_num) {
        studentMapper.deleteStudent(stu_num);
    }

    public int count() {
        return studentMapper.count();
    }


    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    // 获取班级内学生数量
    public Long getStudentCountInClass(String className) {
        return studentMapper.countStudentsInClass(className);
    }


    public String getStudentIdentityByNum(int stuNum) {
        return studentMapper.findIdentityByNum(stuNum);
    }

    public Student getStudentByStuNum(int stuNum) {
        return studentMapper.findByStuNum(stuNum);
    }

    public boolean updateStudentInfo(Student student) {
        return studentMapper.updateStudent(student) > 0;
    }


    public String findClassByStuNum(int stuNum) {
        return studentMapper.findClassByStuNum(stuNum);
    }

    public void batchInsertStudents(List<Student> students) {
        studentMapper.batchInsert(students);
    }

    //查询学生密码
    public String findPasswordByStuNum(int stuNum) {
        return studentMapper.findPasswordByNum(stuNum);
    }

    //根据id修改学生密码
    public int updatePasswordById(String stuNum, String newPassword) {
        return studentMapper.updatePasswordById(stuNum, newPassword);
    }
}