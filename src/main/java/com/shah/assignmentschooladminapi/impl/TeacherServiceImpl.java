package com.shah.assignmentschooladminapi.impl;

import com.shah.assignmentschooladminapi.model.dto.AllTeachersWithStudentsDto;
import com.shah.assignmentschooladminapi.model.dto.TeacherDto;
import com.shah.assignmentschooladminapi.entity.Student;
import com.shah.assignmentschooladminapi.entity.TeacherWithStudentList;
import com.shah.assignmentschooladminapi.exception.AdminException;
import com.shah.assignmentschooladminapi.model.request.DeRegisterStudentFromTeacher;
import com.shah.assignmentschooladminapi.model.request.RegisterStudents;
import com.shah.assignmentschooladminapi.model.response.CommonStudents;
import com.shah.assignmentschooladminapi.repository.StudentRepository;
import com.shah.assignmentschooladminapi.repository.TeacherRepository;
import com.shah.assignmentschooladminapi.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.shah.assignmentschooladminapi.mapper.DtoToEntityMapper.teacherDtoToEntityMapper;
import static com.shah.assignmentschooladminapi.mapper.DtoToEntityMapper.teacherEntityToTeacherStudentDto;

/**
 * @author NORUL
 */
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void addTeacher(TeacherDto teacher) {

        teacherRepository
                .findByEmail(teacher.getEmail())
                .ifPresent(i -> {
                    throw new AdminException(i.getEmail() + " already exists");
                });
        TeacherWithStudentList teacherWithStudentListEntity = teacherDtoToEntityMapper(teacher);
        teacherRepository.save(teacherWithStudentListEntity);
    }

    @Override
    public void registerStudentsToTeacher(RegisterStudents registerStudents) {

        // Check if teacher exists in DB
        TeacherWithStudentList teacherWithStudentList = getTeacherByEmail(registerStudents.getTeacher());

        // Remove any duplicate emails
        List<String> newEmails = registerStudents.getStudents().stream().distinct().collect(Collectors.toList());

        // Check if student exists in DB
        List<Student> existingStudents = newEmails
                .stream()
                .map(this::getStudentByEmail)
                .collect(Collectors.toList());

        // Check if new students is already registered to teacher
        checkForRegisteredStudents(teacherWithStudentList, newEmails);

        teacherWithStudentList.getStudents().addAll(existingStudents);
        teacherRepository.save(teacherWithStudentList);
    }


    @Override
    public AllTeachersWithStudentsDto getTeacherWithStudents() {
        List<TeacherWithStudentList> teacherWithStudentListList = teacherRepository.findAll();
        if (ObjectUtils.isEmpty(teacherWithStudentListList)) {
            throw new AdminException("No teachers present");
        }
        return teacherEntityToTeacherStudentDto(teacherWithStudentListList);
    }

    @Override
    public void deRegisterStudentFromTeacher(DeRegisterStudentFromTeacher data) {

        // Check if student is already registered to the specified teacher
        TeacherWithStudentList teacherWithStudentList = teacherRepository
                .findByEmailAndStudentsEmail(data.getTeacher(), data.getStudent())
                .orElseThrow(() -> new AdminException("Student not found under specified teacher"));

        Optional<Student> first = teacherWithStudentList.getStudents().stream().filter(i -> i.getEmail().equals(data.getStudent())).findFirst();

        first.ifPresent(student -> teacherWithStudentList.getStudents().remove(student));

        teacherRepository.save(teacherWithStudentList);
    }

    private TeacherWithStudentList getTeacherByEmail(String teacherEmail) {
        return teacherRepository
                .findByEmail(teacherEmail)
                .orElseThrow(() ->
                        new AdminException(teacherEmail + " doesn't exists"));
    }

    private Student getStudentByEmail(String studentEmail) {
        return studentRepository
                .findByEmail(studentEmail)
                .orElseThrow(() ->
                        new AdminException(studentEmail + " doesn't exists"));
    }

    private void checkForRegisteredStudents(TeacherWithStudentList teacherWithStudentList, List<String> newEmails) {
        List<String> oldEmails = teacherWithStudentList.getStudents().stream()
                .map(Student::getEmail)
                .collect(Collectors.toList());
        List<String> duplicates = newEmails.stream()
                .filter(oldEmails::contains)
                .collect(Collectors.toList());
        if (!duplicates.isEmpty()) {
            throw new AdminException(String.join(" and ", duplicates) + " already registered under teacher " + teacherWithStudentList.getEmail());
        }
    }

    @Override
    public CommonStudents listOfStudentsCommonToAGivenListOfTeachers(List<String> teacherEmails) {

        if (ObjectUtils.isEmpty(teacherEmails)) {
            throw new AdminException("Please input at least one teacher email");
        }

        // Check if teacher exists in DB while removing duplicates
        List<TeacherWithStudentList> teachers = teacherEmails.stream().distinct().map(this::getTeacherByEmail).collect(Collectors.toList());

        log.info("List of teachers: {}", teachers);

        // Get the list of student emails for each teacher
        List<List<String>> studentEmailsPerTeacher = teachers.stream()
                .map(teacher -> teacher.getStudents().stream().map(Student::getEmail).collect(Collectors.toList()))
                .collect(Collectors.toList());

        // Find the intersection of all the student email lists.
        List<String> commonStudentEmails = studentEmailsPerTeacher.stream()
                .reduce((list1, list2) -> {
                    list1.retainAll(list2);
                    return list1;
                })
                .orElse(new ArrayList<>());

        if (ObjectUtils.isEmpty(commonStudentEmails)) {
            throw new AdminException("Common student not found");
        }
        if (teacherEmails.size() == 1) {
            commonStudentEmails.add("student_only_under_" + teacherEmails.get(0));
        }
        return new CommonStudents(commonStudentEmails);
    }
}
