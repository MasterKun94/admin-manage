package com.example.adminmanage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public abstract class Semantics {
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
}
