package com.shah.assignmentschooladminapi.mapper;

import com.shah.assignmentschooladminapi.model.dto.AllTeachersWithStudentsDto;
import com.shah.assignmentschooladminapi.model.dto.StudentDto;
import com.shah.assignmentschooladminapi.model.dto.TeacherDto;
import com.shah.assignmentschooladminapi.entity.Student;
import com.shah.assignmentschooladminapi.entity.Teacher;
import com.shah.assignmentschooladminapi.model.TeacherStudent;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NORUL
 */
public class DtoToEntityMapper {

    public static Teacher teacherDtoToEntityMapper(TeacherDto dto) {

        return Teacher.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .build();
    }

    public static Student studentDtoToEntityMapper(StudentDto dto) {

        return Student.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .build();
    }

    public static AllTeachersWithStudentsDto teacherEntityToTeacherStudentDto(List<Teacher> entityList) {

        List<TeacherStudent> teacherStudent = entityList.stream().map(i ->

                TeacherStudent.builder()
                        .teacherEmail(i.getEmail())
                        .studentEmail(i.getStudents().stream()
                                .map(Student::getEmail)
                                .collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());

        return AllTeachersWithStudentsDto.builder().teachers(teacherStudent).build();
    }
}
