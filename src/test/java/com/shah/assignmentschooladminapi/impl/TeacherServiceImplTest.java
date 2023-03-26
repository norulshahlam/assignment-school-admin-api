package com.shah.assignmentschooladminapi.impl;

import com.shah.assignmentschooladminapi.model.dto.TeacherDto;
import com.shah.assignmentschooladminapi.entity.Student;
import com.shah.assignmentschooladminapi.entity.Teacher;
import com.shah.assignmentschooladminapi.exception.AdminException;
import com.shah.assignmentschooladminapi.model.request.DeRegisterStudentFromTeacher;
import com.shah.assignmentschooladminapi.model.request.RegisterStudents;
import com.shah.assignmentschooladminapi.repository.StudentRepository;
import com.shah.assignmentschooladminapi.repository.TeacherRepository;
import com.shah.assignmentschooladminapi.util.ExistingDataCheck;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class TeacherServiceImplTest {

    public static final String TEACHER_EMAIL1 = "teacher1@gmail.com";
    public static final String TEACHER_EMAIL2 = "teacher2@gmail.com";
    public static final String TEACHER_NAME = "name";
    public static final String STUDENT_EMAIL1 = "student1@abc.com";
    public static final String STUDENT_EMAIL2 = "student2@abc.com";
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private StudentRepository studentRepository;

    private TeacherDto teacherDto;
    private Student student;
    private Teacher teacher;
    @Mock
    private ExistingDataCheck existingDataCheck;
    @Mock
    private Teacher teacherMock;
    private RegisterStudents registerStudents;
    private DeRegisterStudentFromTeacher deRegisterStudentFromTeacher;
    private List<String> teacherEmails;
    @InjectMocks
    private TeacherServiceImpl teacherService;

    @BeforeEach
    void setUp() {
        teacherDto = TeacherDto.builder()
                .email(TEACHER_EMAIL1)
                .name(TEACHER_NAME)
                .build();

        student = Student.builder()
                .email(STUDENT_EMAIL1)
                .build();

        teacher = Teacher.builder()
                .email(TEACHER_EMAIL1)
                .name(TEACHER_NAME)
                .students(new LinkedList<>(Arrays.asList(student)))
                .build();

        List<String> students = Arrays.asList(STUDENT_EMAIL2);

        registerStudents = RegisterStudents.builder()
                .teacher(TEACHER_EMAIL1)
                .students(students)
                .build();

        deRegisterStudentFromTeacher = DeRegisterStudentFromTeacher.builder()
                .teacher(TEACHER_EMAIL1)
                .student(STUDENT_EMAIL1)
                .build();

        teacherEmails = List.of(TEACHER_EMAIL1, TEACHER_EMAIL2);
    }

    @Test
    void addTeacher_Success() {
        teacherService.addTeacher(teacherDto);
        verify(teacherRepository, times(1)).save(any());

    }

    @Test
    void registerStudentsToTeacher_Success() {
        when(teacherRepository.findByEmail(any())).thenReturn(Optional.of(teacher));
        when(studentRepository.findByEmail(any())).thenReturn(Optional.of(student));
        teacherService.registerStudentsToTeacher(registerStudents);
        verify(teacherRepository, times(1)).findByEmail(teacherDto.getEmail());
    }

    @Test
    void registerStudentsToTeacher_StudentAlreadyRegistered() {
        registerStudents = RegisterStudents.builder()
                .teacher(TEACHER_EMAIL1)
                .students(Arrays.asList(STUDENT_EMAIL1))
                .build();
        when(teacherRepository.findByEmail(any())).thenReturn(Optional.of(teacher));
        when(studentRepository.findByEmail(any())).thenReturn(Optional.of(student));
        assertThrows(AdminException.class, () -> teacherService.registerStudentsToTeacher(registerStudents));
        verify(teacherRepository, times(1)).findByEmail(teacherDto.getEmail());
    }

    @Test
    void registerStudentsToTeacher_TeacherNotFound() {
        when(teacherRepository.findByEmail(any())).thenReturn(Optional.empty());
        assertThrows(AdminException.class, () -> teacherService.registerStudentsToTeacher(registerStudents));
    }

    @Test
    void registerStudentsToTeacher_StudentNotFound() {
        when(teacherRepository.findByEmail(any())).thenReturn(Optional.of(teacher));
        when(studentRepository.findByEmail(any())).thenReturn(Optional.empty());
        assertThrows(AdminException.class, () -> teacherService.registerStudentsToTeacher(registerStudents));
    }

    @Test
    void getTeacherWithStudents_Success() {
        when(teacherRepository.findAll()).thenReturn(List.of(teacher));
        teacherService.getTeacherWithStudents();
        verify(teacherRepository, times(1)).findAll();
    }

    @Test
    void getTeacherWithStudents_Failure() {
        when(teacherRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AdminException.class, () -> teacherService.getTeacherWithStudents());
    }

    @Test
    void deRegisterStudentFromTeacher() {
        when(teacherRepository.findByEmailAndStudentsEmail(any(), any())).thenReturn(Optional.of(teacher));

        teacherService.deRegisterStudentFromTeacher(deRegisterStudentFromTeacher);

        verify(teacherRepository, times(1)).findByEmailAndStudentsEmail(teacherDto.getEmail(), student.getEmail());
    }

    @Test
    void listOfStudentsCommonToAGivenListOfTeachers_CommonStudentNotFound() {
        when(teacherRepository.findByEmail(TEACHER_EMAIL2)).thenReturn(Optional.ofNullable(teacher));
        when(teacherRepository.findByEmail(TEACHER_EMAIL1)).thenReturn(Optional.ofNullable(teacherMock));
        when(teacherMock.getStudents()).thenReturn(List.of(Student.builder().email(STUDENT_EMAIL2).build()));
        teacherService.listOfStudentsCommonToListOfTeachers(List.of(TEACHER_EMAIL1));
        assertThrows(AdminException.class, () -> teacherService.listOfStudentsCommonToListOfTeachers(teacherEmails));
    }

    @Test
    void listOfStudentsCommonToAGivenListOfTeachers_EmptyTeacherEmails() {
        assertThrows(AdminException.class, () -> teacherService.listOfStudentsCommonToListOfTeachers(new ArrayList<>()));
    }
}