package com.project.rmm.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;

    private Double cost;

    @ManyToOne(optional = false)
    @JoinColumn(name = "system_id", referencedColumnName = "id")
    private System system;
}
