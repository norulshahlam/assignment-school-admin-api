package com.shah.assignmentschooladminapi.repository;

import com.shah.assignmentschooladminapi.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author NORUL
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

    Optional<Teacher> findByEmail(String email);

    Optional<List<Teacher>> findAllByEmailIn(List<String> teacher);

    Optional<Teacher> findByEmailAndStudentsEmail(String teacherEmail, String studentEmail);
}
