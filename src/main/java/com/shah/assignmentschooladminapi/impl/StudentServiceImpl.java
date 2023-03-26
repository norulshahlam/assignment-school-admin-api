package com.shah.assignmentschooladminapi.impl;

import com.shah.assignmentschooladminapi.entity.Student;
import com.shah.assignmentschooladminapi.model.dto.StudentDto;
import com.shah.assignmentschooladminapi.repository.StudentRepository;
import com.shah.assignmentschooladminapi.repository.TeacherRepository;
import com.shah.assignmentschooladminapi.service.StudentService;
import com.shah.assignmentschooladminapi.util.ExistingDataCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.shah.assignmentschooladminapi.util.DtoToEntityMapper.studentDtoToEntityMapper;

/**
 * @author NORUL
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ExistingDataCheck existingDataCheck;

    @Override
    public void addStudent(StudentDto studentDto) {

        // Check if student is already in the repository
        existingDataCheck.ifDataIsInDb(studentDto.getEmail());
        Student studentEntity = studentDtoToEntityMapper(studentDto);
        studentRepository.save(studentEntity);
    }
}
