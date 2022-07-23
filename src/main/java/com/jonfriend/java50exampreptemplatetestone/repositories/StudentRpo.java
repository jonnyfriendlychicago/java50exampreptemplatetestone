package com.jonfriend.java50exampreptemplatetestone.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java50exampreptemplatetestone.models.StudentMdl;
import com.jonfriend.java50exampreptemplatetestone.models.TeacherMdl;

@Repository
public interface StudentRpo extends CrudRepository<StudentMdl, Long> {
	
	List<StudentMdl> findAll();
	
	StudentMdl findByIdIs(Long id);
	
//	List<StudentMdl> findAllByTeachers(TeacherMdl teacherMdl);
	List<StudentMdl> findAllByTeacherMdl(TeacherMdl teacherMdl);
	
//	List<StudentMdl> findByTeachersNotContains(TeacherMdl teacherMdl);
	List<StudentMdl> findByTeacherMdlNotContains(TeacherMdl teacherMdl);
}
