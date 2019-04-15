package com.example.adminmanage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Entity
public class ObjectInfo {
    private int id;
    private String name;
    private String URI;
    private List<ObjectSemantics> semantics;
    private List<PropertyInfo> properties;
}
