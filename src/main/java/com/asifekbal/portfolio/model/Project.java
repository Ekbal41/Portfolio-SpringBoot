package com.asifekbal.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String title;
    private String tag;
    private String shortdesc;

    @Column(name="notes",columnDefinition="LONGTEXT",nullable = false,  length = 4096)
    private String description;
    private String imagename;
    
}

