package com.shah.assignmentschooladminapi.impl;

import com.shah.assignmentschooladminapi.model.dto.StudentDto;
import com.shah.assignmentschooladminapi.entity.Student;
import com.shah.assignmentschooladminapi.exception.AdminException;
import com.shah.assignmentschooladminapi.repository.StudentRepository;
import com.shah.assignmentschooladminapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.shah.assignmentschooladminapi.mapper.DtoToEntityMapper.studentDtoToEntityMapper;

/**
 * @author NORUL
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void addStudent(StudentDto studentDto) {
        studentRepository
                .findByEmail(studentDto.getEmail())
                .ifPresent(i -> {
                    throw new AdminException(i.getEmail() + " already exists");
                });
        Student studentEntity = studentDtoToEntityMapper(studentDto);
        studentRepository.save(studentEntity);
    }

}
