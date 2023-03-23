package com.shah.assignmentschooladminapi.controller;

import com.shah.assignmentschooladminapi.model.dto.AllTeachersWithStudentsDto;
import com.shah.assignmentschooladminapi.model.dto.StudentDto;
import com.shah.assignmentschooladminapi.model.dto.TeacherDto;
import com.shah.assignmentschooladminapi.model.request.DeRegisterStudentFromTeacher;
import com.shah.assignmentschooladminapi.model.request.RegisterStudents;
import com.shah.assignmentschooladminapi.model.response.CommonStudents;
import com.shah.assignmentschooladminapi.service.StudentService;
import com.shah.assignmentschooladminapi.service.TeacherService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author NORUL
 */
@RestController
@Slf4j
@RequestMapping("api")
public class AdminController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    /**
     * User story #1
     *
     * @return
     */
    @PostMapping("students")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@Valid @RequestBody StudentDto student) {
        log.info("in AdminController::addStudent");
        log.info("StudentDto: {}", student);
        studentService.addStudent(student);
    }

    /**
     * User story #2
     *
     * @return
     */
    @PostMapping("teachers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeacher(@Valid @RequestBody TeacherDto teacher) {
        log.info("in AdminController::addTeacher");
        log.info("TeacherDto: {}", teacher);
        teacherService.addTeacher(teacher);
    }

    /**
     * User story #3
     *
     * @return
     */
    @PostMapping("register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addStudent(@Valid @RequestBody RegisterStudents registerStudents) {
        log.info("in AdminController::registerStudentsToTeacher");
        log.info("RegisterStudents: {}", registerStudents);
        teacherService.registerStudentsToTeacher(registerStudents);
    }

    /**
     * User story #4
     *
     * @param data
     */
    @PostMapping("deregister")
    @ResponseStatus(HttpStatus.OK)
    public void deRegisterStudentFromTeacher(@Valid @RequestBody DeRegisterStudentFromTeacher data) {
        log.info("in AdminController::deRegisterStudentFromTeacher");
        log.info("DeRegisterStudentFromTeacher: {}", data);
        teacherService.deRegisterStudentFromTeacher(data);
    }

    /**
     * User story #5
     *
     * @return
     */
    @GetMapping("commonstudents")
    @ResponseStatus(HttpStatus.OK)
    public CommonStudents listOfStudentsCommonToAGivenListOfTeachers(
            @Parameter(description = "list of teacher's email", example = "abc@xyz.com")
            @RequestParam(required = false) List<String> teacher) {
        log.info("in AdminController::listOfStudentsCommonToAGivenListOfTeachers");
        log.info("listOfStudentsCommonToAGivenListOfTeachers: {}", teacher);

        return teacherService.listOfStudentsCommonToAGivenListOfTeachers(teacher);
    }

    /**
     * User story #6
     *
     * @return
     */
    @GetMapping("teachers")
    @ResponseStatus(HttpStatus.OK)
    public AllTeachersWithStudentsDto getTeacherWithStudentsList() {
        log.info("in AdminController::getTeacherWithStudents");
        return teacherService.getTeacherWithStudents();
    }


}
