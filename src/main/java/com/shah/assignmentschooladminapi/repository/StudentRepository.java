package com.shah.assignmentschooladminapi.repository;

import com.shah.assignmentschooladminapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author NORUL
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
}
