package cn.zsc.service;

import cn.zsc.entity.User;
import cn.zsc.mapper.ManagerMapper;
import cn.zsc.mapper.StudentMapper;
import cn.zsc.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Method not implemented. Use separate methods for students, teachers, and managers.");
    }

    public UserDetails loadUserByIdAndRole(int userId, String role) {
        User user = null;
        if ("student".equals(role)) {
            String password = studentMapper.findPasswordByNum(userId);
            user = new User(userId, password);
        } else if ("teacher".equals(role)) {
            String password = teacherMapper.findPasswordByNum(userId);
            user = new User(userId, password);
        } else if ("manager".equals(role)) {
            String password = managerMapper.findPasswordByNum(userId);
            user = new User(userId, password);
        }

        if (user == null || user.getPassword() == null) {
            throw new UsernameNotFoundException("User not found with id: " + userId + " and role: " + role);
        }

        // Assume roles are prefixed with 'ROLE_' for GrantedAuthorities
        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getUserId()),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
        );
    }
}