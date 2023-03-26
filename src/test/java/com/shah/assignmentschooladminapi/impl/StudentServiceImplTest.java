package com.shah.assignmentschooladminapi.impl;

import com.shah.assignmentschooladminapi.entity.Student;
import com.shah.assignmentschooladminapi.entity.Teacher;
import com.shah.assignmentschooladminapi.model.dto.StudentDto;
import com.shah.assignmentschooladminapi.repository.StudentRepository;
import com.shah.assignmentschooladminapi.util.ExistingDataCheck;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.LinkedList;

import static com.shah.assignmentschooladminapi.impl.TeacherServiceImplTest.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ExistingDataCheck existingDataCheck;

    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentDto studentDto;
    private Student student;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        studentDto = StudentDto.builder()
                .email(STUDENT_EMAIL1)
                .build();
        student = Student.builder()
                .email(STUDENT_EMAIL1)
                .build();
        teacher = Teacher.builder()
                .email(TEACHER_EMAIL1)
                .name(TEACHER_NAME)
                .students(new LinkedList<>(Arrays.asList(student)))
                .build();
    }

    @Test
    void addStudentSuccess() {
        studentService.addStudent(studentDto);
        verify(studentRepository, times(1)).save(any());
    }
}