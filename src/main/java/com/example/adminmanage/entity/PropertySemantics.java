package com.example.adminmanage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class PropertySemantics extends Semantics {
    @Column(name = "type")
    private String type;
}
