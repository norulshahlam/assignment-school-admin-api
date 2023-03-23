package com.shah.assignmentschooladminapi.impl;

import com.shah.assignmentschooladminapi.model.dto.StudentDto;
import com.shah.assignmentschooladminapi.entity.Student;
import com.shah.assignmentschooladminapi.exception.AdminException;
import com.shah.assignmentschooladminapi.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.shah.assignmentschooladminapi.impl.TeacherServiceImplTest.STUDENT_EMAIL1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentDto studentDto;
    private Student student;

    @BeforeEach
    void setUp() {
        studentDto = StudentDto.builder()
                .email(STUDENT_EMAIL1)
                .build();
        student = Student.builder()
                .email(STUDENT_EMAIL1)
                .build();
    }

    @Test
    void addStudentSuccess() {
        when(studentRepository.findByEmail(any())).thenReturn(Optional.empty());
        studentService.addStudent(studentDto);
        verify(studentRepository, times(1)).findByEmail(studentDto.getEmail());
    }

    @Test
    void addStudentFailure() {
        when(studentRepository.findByEmail(any())).thenReturn(Optional.of(student));
        Assertions.assertThrows(AdminException.class, () -> studentService.addStudent(studentDto));
    }
}