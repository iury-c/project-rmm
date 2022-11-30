package com.project.rmm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"type", "system_id"})
)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "system_id", referencedColumnName = "id")
    private System system;

    private String type;
}
