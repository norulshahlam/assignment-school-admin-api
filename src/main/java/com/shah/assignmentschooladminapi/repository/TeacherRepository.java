package com.shah.assignmentschooladminapi.repository;

import com.shah.assignmentschooladminapi.entity.TeacherWithStudentList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author NORUL
 */
public interface TeacherRepository extends JpaRepository<TeacherWithStudentList, Long> {

    Optional<TeacherWithStudentList> findByEmail(String email);

    List<TeacherWithStudentList> findAllByEmailIn(List<String> teacher);

    Optional<TeacherWithStudentList> findByEmailAndStudentsEmail(String teacherEmail,String studentEmail);


}
