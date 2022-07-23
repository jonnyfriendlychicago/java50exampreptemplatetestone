package com.jonfriend.java50exampreptemplatetestone.controllers;

//import java.util.List;

//import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonfriend.java50exampreptemplatetestone.models.StudentMdl;
import com.jonfriend.java50exampreptemplatetestone.models.TeacherMdl;
import com.jonfriend.java50exampreptemplatetestone.services.StudentSrv;
import com.jonfriend.java50exampreptemplatetestone.services.TeacherSrv;
//import com.jonfriend.java50exampreptemplatetestone.services.UserSrv;

@Controller
public class StudentCtl {

	@Autowired
	private TeacherSrv teacherSrv;
	
	@Autowired
	private StudentSrv studentSrv;
	
//	@Autowired
//	private UserSrv userSrv;
	
	// display create-new student page
//	@GetMapping("/store/student/new")
	@GetMapping("/student/new")
	public String newStudent(
			@ModelAttribute("student") StudentMdl studentMdl
			) {
//		return "store/student/create.jsp";}
		return "student/create.jsp";}
	
	
	// process the create-new student
//	@PostMapping("/store/student/new")
	@PostMapping("/student/new")
	public String addNewStudent(
			@Valid @ModelAttribute("student") StudentMdl studentMdl
			, BindingResult result
			, Model model) {
		
		if(result.hasErrors()) {
//			return "store/student/create.jsp";
			return "student/create.jsp";
		} else {
			studentSrv.addStudent(studentMdl);
//			return "redirect:/store";
			return "redirect:/home";
		}	
	}
	
	// view/manage one student
//	@GetMapping("/store/student/{id}")
	@GetMapping("/student/{id}")
	public String showStudent(
			@PathVariable("id") Long id
			, Model model
			) {
		StudentMdl student = studentSrv.findById(id);
		
		model.addAttribute("student", studentSrv.findById(id));
		model.addAttribute("assignedTeachers", teacherSrv.getAssignedCategories(student));
		model.addAttribute("unassignedTeachers", teacherSrv.getUnassignedCategories(student));
		
//		return "/store/student/record.jsp";
		return "/student/record.jsp";
	}
	
	// process edits to that one student
//	@PostMapping("/store/student/{id}")
	@PostMapping("/student/{id}")
	
	public String editStudent(
			@PathVariable("id") Long id
			, @RequestParam(value="teacherId") Long teacherId
			, Model model
			) {
		
		StudentMdl student = studentSrv.findById(id);
		TeacherMdl teacher = teacherSrv.findById(teacherId);
		
		student.getTeacherMdl().add(teacher);
		studentSrv.updateStudent(student);
		model.addAttribute("assignedTeachers", teacherSrv.getAssignedCategories(student));
		model.addAttribute("unassignedTeachers", teacherSrv.getUnassignedCategories(student));
//		return "redirect:/store/student/" + id;
		return "redirect:/student/" + id;
		
	}

// end ctrl
}
