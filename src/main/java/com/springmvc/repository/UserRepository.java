package com.springmvc.repository;

import com.springmvc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findOneByUserNameAndStatus(String name, int status);
    UserEntity findUserEntityByUserNameAndStatus(String name, int status);
}
