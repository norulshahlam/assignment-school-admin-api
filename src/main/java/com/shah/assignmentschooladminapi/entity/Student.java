package com.shah.assignmentschooladminapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author NORUL
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long studentId;

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String name;
}
