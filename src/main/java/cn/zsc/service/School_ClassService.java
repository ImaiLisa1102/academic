package cn.zsc.service;

import cn.zsc.entity.School_Class;
import cn.zsc.mapper.School_ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class School_ClassService {
   @Autowired
   private School_ClassMapper schoolClassMapper;

   public School_Class findSchoolClassByName(String className) {
      return schoolClassMapper.findByName(className);
   }

   public List<School_Class> findAll(int offset, int size){
      return schoolClassMapper.findAll(offset, size);
   }

   public void deleteClass(int classId) {
      schoolClassMapper.deleteClass(classId);
   }

   public int count() {
      return schoolClassMapper.count();
   }

   public void insertClass(School_Class schoolClass) {
      schoolClassMapper.insertClass(schoolClass);
   }


   public List<String> findClassNamesByMajorId(int majorId) {
      return schoolClassMapper.findClassNamesByMajorId(majorId);
   }


   public int getClassYearByClassName(String className) {
      return schoolClassMapper.getClassYearByClassName(className);
   }

   public boolean updateSchool_ClassInfo(School_Class school_class) {
      return schoolClassMapper.updateSchool_Class(school_class) > 0;
   }

   public void batchInsertClasses(List<School_Class> classes) {
      schoolClassMapper.batchInsert(classes);
   }

}
