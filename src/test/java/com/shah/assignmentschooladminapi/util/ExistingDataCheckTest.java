package com.shah.assignmentschooladminapi.util;

import com.shah.assignmentschooladminapi.entity.Student;
import com.shah.assignmentschooladminapi.entity.Teacher;
import com.shah.assignmentschooladminapi.exception.AdminException;
import com.shah.assignmentschooladminapi.repository.StudentRepository;
import com.shah.assignmentschooladminapi.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ExistingDataCheckTest {

    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private ExistingDataCheck existingDataCheck;

    @Test
    void ifDataIsInDb() {

        Teacher teacher = Teacher.builder().teacherId(2L).build();
        Student student = Student.builder().studentId(2L).build();

        // Nothing found in repository
        existingDataCheck.ifDataIsInDb("abc@xyz.com");
        // Teacher found in repository
        when(teacherRepository.findByEmail(any())).thenReturn(Optional.of(teacher));
        assertThrows(AdminException.class, () -> existingDataCheck.ifDataIsInDb("abc@xyz.com"));
        // Student found in repository
        when(studentRepository.findByEmail(any())).thenReturn(Optional.of(student));
        assertThrows(AdminException.class, () -> existingDataCheck.ifDataIsInDb("abc@xyz.com"));
    }
}