package com.jonfriend.java50exampreptemplatetestone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonfriend.java50exampreptemplatetestone.models.StudentMdl;
import com.jonfriend.java50exampreptemplatetestone.models.TeacherMdl;
import com.jonfriend.java50exampreptemplatetestone.repositories.StudentRpo;

@Service
public class StudentSrv {
	
	// adding the student repository as a dependency
	private final StudentRpo studentRpo;
	
	public StudentSrv (StudentRpo studentRpo) {this.studentRpo = studentRpo;}
	
	// creates one student >> rename as createNew
	public StudentMdl addStudent(StudentMdl x) {
		return studentRpo.save(x);
	}

	// updates one student >> rename as update
	public StudentMdl updateStudent(StudentMdl x) {
		return studentRpo.save(x);
	}
	
	// delete student by id >> rename as delete
	// JRF: this srv is very different from myu publicationSrv.delete.  what gives? 
	public void deleteStudent(StudentMdl x) {
		studentRpo.delete(x);
	}
	
	// returns one student by id >> no need rename
	public StudentMdl findById(Long id) {
		Optional<StudentMdl> optionalStudent = studentRpo.findById(id);
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		}else {
			return null;
		}
	}
	
	// returns all student >> RENAME AS returnAll
//	public List<StudentMdl> allCategories(){
	public List<StudentMdl> returnAll(){
		return studentRpo.findAll();
	}
	
	// get all joined teacher >> rename as returnJoinedTeacher
	public List<StudentMdl> getAssignedTeachers(TeacherMdl x){
		return studentRpo.findAllByTeacherMdl(x);
	}
	
	// get all un-joined teacher >> rename as returnNotJoinedTeacher
	public List<StudentMdl> getUnassignedTeachers(TeacherMdl x){
		return studentRpo.findByTeacherMdlNotContains(x);
	}
	
// end srv
}