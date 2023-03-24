package com.shah.assignmentschooladminapi.controller;

import com.shah.assignmentschooladminapi.model.dto.AllTeachersWithStudentsDto;
import com.shah.assignmentschooladminapi.model.dto.StudentDto;
import com.shah.assignmentschooladminapi.model.dto.TeacherDto;
import com.shah.assignmentschooladminapi.model.request.DeRegisterStudentFromTeacher;
import com.shah.assignmentschooladminapi.model.request.RegisterStudents;
import com.shah.assignmentschooladminapi.model.response.CommonStudents;
import com.shah.assignmentschooladminapi.service.StudentService;
import com.shah.assignmentschooladminapi.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author NORUL
 */
@RestController
@Slf4j
@RequestMapping("api")
@Validated
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
    @Operation(summary = "add student")
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
    @Operation(summary = "add Teacher")
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
    @Operation(summary = "Register Student to Teacher")
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
    @Operation(summary = "de Register Student From Teacher")
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
    @Operation(summary = "Get list of students common to a given ist of teachers")
    @GetMapping("commonstudents")
    @ResponseStatus(HttpStatus.OK)
    public CommonStudents listOfStudentsCommonToAGivenListOfTeachers(
            @Parameter(description = "list of teacher's email", example = "[\"teacher1@xyz.com\", \"teacher2@xyz.com\"]")
            @RequestParam(value = "teacher", required = false)
            @NotEmpty(message = "Please input at least one teacher email") List<@NotBlank(message = "teacher Email cannot be blank")
            @Email(message = "teacher email address must be in proper format") String> teacherEmails) {
        log.info("in AdminController::listOfStudentsCommonToListOfTeachers");
        log.info("listOfStudentsCommonToListOfTeachers: {}", teacherEmails);

        return teacherService.listOfStudentsCommonToListOfTeachers(teacherEmails);
    }

    /**
     * User story #6
     *
     * @return
     */
    @Operation(summary = "get Teacher With Students List")
    @GetMapping("teachers")
    @ResponseStatus(HttpStatus.OK)
    public AllTeachersWithStudentsDto getTeacherWithStudentsList() {
        log.info("in AdminController::getTeacherWithStudents");
        return teacherService.getTeacherWithStudents();
    }

}
