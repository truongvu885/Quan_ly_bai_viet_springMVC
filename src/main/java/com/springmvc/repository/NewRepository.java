package com.springmvc.repository;

import com.springmvc.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepository extends JpaRepository<NewEntity, Long> {

}
