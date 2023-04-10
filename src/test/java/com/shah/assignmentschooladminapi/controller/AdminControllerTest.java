package com.shah.assignmentschooladminapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shah.assignmentschooladminapi.impl.TeacherServiceImpl;
import com.shah.assignmentschooladminapi.model.dto.StudentDto;
import com.shah.assignmentschooladminapi.model.dto.TeacherDto;
import com.shah.assignmentschooladminapi.model.request.DeRegisterStudentFromTeacher;
import com.shah.assignmentschooladminapi.model.request.RegisterStudents;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Data
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TeacherServiceImpl teacherService;

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void addStudentTest() throws Exception {
        StudentDto student = StudentDto.builder()
                .email("test@test.com")
                .name("Test Student")
                .build();

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void addTeacherTest() throws Exception {
        TeacherDto teacher = TeacherDto.builder()
                .email("test@test.com")
                .name("Test Teacher")
                .build();

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isCreated());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void registerStudentsToTeacherTest() throws Exception {
        RegisterStudents registerStudents = RegisterStudents.builder()
                .teacher("test@test.com")
                .students(Arrays.asList("test1@test.com", "test2@test.com"))
                .build();

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerStudents)))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void deRegisterStudentFromTeacherTest() throws Exception {
        DeRegisterStudentFromTeacher deRegister = DeRegisterStudentFromTeacher.builder()
                .teacher("test@test.com")
                .student("test1@test.com")
                .reason("Cancelled enrollment")
                .build();

        mockMvc.perform(post("/api/deregister")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deRegister)))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void listOfStudentsCommonToAGivenListOfTeachersTest() throws Exception {
        mockMvc.perform(get("/api/commonstudents")
                        .param("teacher", "test1@test.com")
                        .param("teacher", "test2@test.com"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void getTeacherWithStudentsListTest() throws Exception {
        mockMvc.perform(get("/api/teachers"))
                .andExpect(status().isOk());
    }
}
