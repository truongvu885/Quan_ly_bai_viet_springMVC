package com.springmvc.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "new")
@Getter
@Setter
public class NewEntity extends BaseEntity{

    @Column
    private String title;

    @Column
    private String thumbnail;

    @Column(name = "shortDe",columnDefinition = "TEXT")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "categoryid")
    private Long categoryId;


}
