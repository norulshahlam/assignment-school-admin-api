package com.shah.assignmentschooladminapi.model.dto;

import com.shah.assignmentschooladminapi.model.TeacherStudent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllTeachersWithStudentsDto {

    List<TeacherStudent> teachers;
}
