package cn.zsc.service;

import cn.zsc.entity.Teacher;
import cn.zsc.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

   public Teacher findTeacherByTeacherNumber(int tea_num) {
      return teacherMapper.findByTeacherNumber(tea_num);
   }

   public List<Teacher> findAll(int offset, int size) {
      return teacherMapper.findAll(offset, size);
   }

   public int count() {
      return teacherMapper.count();
   }

   public void deleteTeacher(Long stu_num) {
         teacherMapper.deleteTeacher(stu_num);
   }

   public void insertTeacher(Teacher teacher) {
      teacherMapper.insertTeacher(teacher);
   }

   public List<String> getAllTeacherNames() {
      return teacherMapper.getAllTeacherNames();
   }


   public String getTeacherIdentityByNum(int teaNum) {
      return teacherMapper.findIdentityByNum(teaNum);
   }

   public Teacher getTeacherByTeaNum(int teaNum) {
      return teacherMapper.findByTeaNum(teaNum);
   }

   public boolean updateTeacherInfo(Teacher teacher) {
      return teacherMapper.updateTeacher(teacher) > 0;
   }

   public void batchInsertTeachers(List<Teacher> teachers) {
      teacherMapper.batchInsert(teachers);
   }

   //根据编号查找老师密码
   public String findTeacherPasswordByNum(int teaNum) {
      return teacherMapper.findPasswordByNum(teaNum);
   }

   //根据编号修改老师密码
   @Transactional
   public int updateTeacherPasswordByNum(int teaNum, String newPassword) {
      return teacherMapper.updatePasswordByNum(teaNum, newPassword);
   }
}