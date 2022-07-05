package com.springmvc.service.impl;

import com.springmvc.constant.SystemConstant;
import com.springmvc.dto.MyUser;
import com.springmvc.entity.RoleEntity;
import com.springmvc.entity.UserEntity;
import com.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByUserNameAndStatus(username, SystemConstant.USER_ACTIVE_STATUS);
        if(userEntity == null){
            throw new UsernameNotFoundException("User not found");
        }
        //push thông tin vào trong security để duy trì thông tin user khi login vào hệ thống : session
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(RoleEntity role:userEntity.getRoleList()){
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        MyUser user = new MyUser(userEntity.getUserName(),userEntity.getPassWord(),true,true,true,true,authorities);
        user.setFullName(userEntity.getFullName());

        return user;
    }
}
