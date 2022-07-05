package com.springmvc.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "status")
    private Integer status;

    @ManyToMany(fetch = FetchType.LAZY) // lazy không tự động lấy hết data khi load những vấn đề liên quan @manytomany...
    @JoinTable(name = "user_role", joinColumns =  @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid"))
    List<RoleEntity> roleList = new ArrayList<>();

}
