package com.shah.assignmentschooladminapi.util;

import com.shah.assignmentschooladminapi.exception.MyException;
import com.shah.assignmentschooladminapi.repository.StudentRepository;
import com.shah.assignmentschooladminapi.repository.TeacherRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NORUL
 */
@Slf4j
@Service
@Data
public class ExistingDataCheck {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public void ifDataIsInDb(String email) {
        studentRepository
                .findByEmail(email)
                .ifPresent(i -> {
                    throw new MyException(i.getEmail() + " already exists");
                });
        teacherRepository
                .findByEmail(email)
                .ifPresent(i -> {
                    throw new MyException(i.getEmail() + " already exists");
                });
    }
}
