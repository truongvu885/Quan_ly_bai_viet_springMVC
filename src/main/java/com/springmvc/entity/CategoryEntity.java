package com.springmvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

    private String name;

    private String code;

    @OneToMany(mappedBy = "categoryId")
    List<NewEntity> newList = new ArrayList<>();
}
