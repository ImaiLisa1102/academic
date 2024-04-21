package cn.zsc.service;

import cn.zsc.entity.Manager;
import cn.zsc.entity.Student;
import cn.zsc.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    //根据num查找密码 findPasswordByNum
    public String findPasswordByNum(int man_num) {
        return managerMapper.findPasswordByNum(man_num);
    }

    public Manager findManagerByManagerNumber(int man_num) {
        return managerMapper.findByManagerNumber(man_num);
    }

    public List<Manager> findAll(int offset, int size) {
        return managerMapper.findAll(offset,size);
    }

    public int count() {
        return managerMapper.count();
    }

    public void deleteManager(Long man_num) {
        managerMapper.deleteManager(man_num);
    }


    public void insertManager(Manager manager) {
        managerMapper.insertManager(manager);
    }

    public String getManagerIdentityByNum(int manNum) {
        return managerMapper.findIdentityByNum(manNum);
    }

    public boolean updateManagerInfo(Manager manager) {
        return managerMapper.updateManager(manager) > 0;
    }

    public void batchInsertManagers(List<Manager> managers) {
        managerMapper.batchInsert(managers);
    }


}
