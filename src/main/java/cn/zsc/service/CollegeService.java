package cn.zsc.service;

import cn.zsc.entity.College;
import cn.zsc.mapper.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {
  @Autowired
  private CollegeMapper collegeMapper;

  public College findCollegeById(int collegeId) {
    return collegeMapper.findCollegeById(collegeId);
  }

  public List<College> findAll(int offset, int size){
    return collegeMapper.findAll(offset, size);
  }

  public void deleteCollege(int collegeId) {
    collegeMapper.deleteCollege(collegeId);
  }

  public int count() {
    return collegeMapper.count();
  }

  public void insertCollege(College college) {
    collegeMapper.insertCollege(college);
  }

  public boolean updateCollegeInfo(College college) {
    return collegeMapper.updateCollege(college) > 0;
  }

  public void batchInsertColleges(List<College> colleges) {
    collegeMapper.batchInsert(colleges);
  }

}
