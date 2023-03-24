package com.shah.assignmentschooladminapi.service;

import com.shah.assignmentschooladminapi.model.dto.AllTeachersWithStudentsDto;
import com.shah.assignmentschooladminapi.model.dto.TeacherDto;
import com.shah.assignmentschooladminapi.model.request.DeRegisterStudentFromTeacher;
import com.shah.assignmentschooladminapi.model.request.RegisterStudents;
import com.shah.assignmentschooladminapi.model.response.CommonStudents;

import java.util.List;

/**
 * @author NORUL
 */
public interface TeacherService {

    void addTeacher(TeacherDto teacher);

    void registerStudentsToTeacher(RegisterStudents registerStudents);

    AllTeachersWithStudentsDto getTeacherWithStudents();

    void deRegisterStudentFromTeacher(DeRegisterStudentFromTeacher data);

    CommonStudents listOfStudentsCommonToListOfTeachers(List<String> teacher);
}
