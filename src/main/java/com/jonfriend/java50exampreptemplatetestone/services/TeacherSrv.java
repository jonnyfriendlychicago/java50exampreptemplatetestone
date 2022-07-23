package com.jonfriend.java50exampreptemplatetestone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonfriend.java50exampreptemplatetestone.models.StudentMdl;
import com.jonfriend.java50exampreptemplatetestone.models.TeacherMdl;
import com.jonfriend.java50exampreptemplatetestone.repositories.TeacherRpo;

@Service
public class TeacherSrv {
	
	// adding the teacher repository as a dependency
	private final TeacherRpo teacherRpo;
	
	public TeacherSrv(TeacherRpo teacherRpo) {this.teacherRpo = teacherRpo;}
	
	// creates one teacher >> rename as createNew
	public TeacherMdl addTeacher(TeacherMdl x) {
		return teacherRpo.save(x);
	}

	// updates one teacher >> rename as update
	public TeacherMdl updateTeacher(TeacherMdl x) {
		return teacherRpo.save(x);
	}
	
	// delete teacher by id >> rename as delete
	// JRF: this srv is very different from myu publicationSrv.delete.  what gives?
	public void deleteTeacher(TeacherMdl x) {
		teacherRpo.delete(x);
	}
	
	// returns one teacher by id >> no need rename
	public TeacherMdl findById(Long id) {
		Optional<TeacherMdl> optionalTeacher = teacherRpo.findById(id);
		if(optionalTeacher.isPresent()) {
			return optionalTeacher.get();
		}else {
			return null;
		}
	}
	
	// returns all teacher >> RENAME AS returnAll
//	public List<TeacherMdl> allTeachers(){
	public List<TeacherMdl> returnAll(){
		return teacherRpo.findAll();
	}
	
	// get all joined teacher >> rename as returnJoinedStudent
	public List<TeacherMdl> getAssignedCategories(StudentMdl x){
//		return teacherRpo.findAllByCategories(x);
		return teacherRpo.findAllByStudentMdl(x);
	}
	
	// get all un-joined teacher >> rename as returnNotJoinedTeacher
	public List<TeacherMdl> getUnassignedCategories(StudentMdl x){
//		return teacherRpo.findByCategoriesNotContains(x);
		return teacherRpo.findByStudentMdlNotContains(x);
	}
	
	// this is for removing a teacher-student join record/entry
	// we are approaching the targeted bomb site (join entry) from the teacher air force base.
	
	public void removeTeacherStudentJoin(StudentMdl c, TeacherMdl p ) {
		List<StudentMdl> studentList = p.getStudentMdl(); 
		studentList.remove(c); 
		this.teacherRpo.save(p); 
	}
	
	
}