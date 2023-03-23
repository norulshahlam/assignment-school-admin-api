package com.shah.assignmentschooladminapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author NORUL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherStudent {

    @JsonProperty("email")
    private String teacherEmail;

    @JsonProperty("students")
    private List<String> studentEmail;
}
