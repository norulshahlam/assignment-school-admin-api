package com.shah.assignmentschooladminapi.impl;

import com.shah.assignmentschooladminapi.model.dto.StudentDto;
import com.shah.assignmentschooladminapi.repository.StudentRepository;
import com.shah.assignmentschooladminapi.util.ExistingDataCheck;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.shah.assignmentschooladminapi.impl.TeacherServiceImplTest.STUDENT_EMAIL1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Data
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ExistingDataCheck existingDataCheck;

    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentDto studentDto;

    @BeforeEach
    void setUp() {
        studentDto = StudentDto.builder()
                .email(STUDENT_EMAIL1)
                .build();
    }

    @Test
    void addStudentSuccess() {
        studentService.addStudent(studentDto);
        verify(studentRepository, times(1)).save(any());
    }
}