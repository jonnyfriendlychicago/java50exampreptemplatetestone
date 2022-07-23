package com.jonfriend.java50exampreptemplatetestone.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java50exampreptemplatetestone.models.StudentMdl;
import com.jonfriend.java50exampreptemplatetestone.models.TeacherMdl;

@Repository
public interface TeacherRpo extends CrudRepository<TeacherMdl, Long> {
	
	List<TeacherMdl> findAll();
	
	TeacherMdl findByIdIs(Long id);
	
//	List<TeacherMdl> findAllByCategories(StudentMdl studentMdl);
	List<TeacherMdl> findAllByStudentMdl(StudentMdl studentMdl);
	
//	List<TeacherMdl> findByCategoriesNotContains(StudentMdl studentMdl);
	List<TeacherMdl> findByStudentMdlNotContains(StudentMdl studentMdl);
}
