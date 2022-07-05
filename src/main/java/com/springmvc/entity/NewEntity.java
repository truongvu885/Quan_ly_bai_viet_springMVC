package com.springmvc.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "new")
@Getter
@Setter
public class NewEntity extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "shortDe",columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    @Column(name = "categoryid")
    private Long categoryId;


}
