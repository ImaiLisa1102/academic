package cn.zsc.service;

import cn.zsc.entity.Major;
import cn.zsc.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    private MajorMapper majorMapper;
    @Transactional
    public Major findMajorById(int majorId) {
        return majorMapper.findById(majorId);
    }

    public List<Major> findAll(int offset, int size){
        return majorMapper.findAll(offset, size);
    }

    public void deleteMajor(int majorId) {
        majorMapper.deleteMajor(majorId);
    }

    public int count() {
        return majorMapper.count();
    }

    public void insertMajor(Major major) {
        majorMapper.insertMajor(major);
    }

    public List<String> getAllMajorNames() {
        return majorMapper.getAllMajorNames();
    }

    public Integer findMajorIdByName(String majorName) {
        return majorMapper.findMajorIdByName(majorName);
    }

    public boolean updateMajorInfo(Major major) {
        return majorMapper.updateMajor(major) > 0;
    }

    public void batchInsertMajors(List<Major> majors) {
        majorMapper.batchInsert(majors);
    }


}
