package com.jonfriend.java50exampreptemplatetestone.controllers;

//import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonfriend.java50exampreptemplatetestone.models.StudentMdl;
import com.jonfriend.java50exampreptemplatetestone.models.TeacherMdl;
import com.jonfriend.java50exampreptemplatetestone.services.StudentSrv;
import com.jonfriend.java50exampreptemplatetestone.services.TeacherSrv;
import com.jonfriend.java50exampreptemplatetestone.services.UserSrv;

@Controller
public class TeacherCtl {

	@Autowired
	private TeacherSrv teacherSrv;
	
	@Autowired
	private StudentSrv studentSrv;
	
	@Autowired
	private UserSrv userSrv;
	
	// display create-new teacher page
//	@GetMapping("/store/teacher/new")
	@GetMapping("/teacher/new")
	public String newTeacher(
			@ModelAttribute("teacher") TeacherMdl teacherMdl
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
//		return "store/teacher/create.jsp";
		return "teacher/create.jsp";
	}
	
	// process the create-new teacher 
//	@PostMapping("/store/teacher/new")
	@PostMapping("/teacher/new")
	public String addNewTeacher(
			@Valid @ModelAttribute("teacher") TeacherMdl teacherMdl
			, BindingResult result
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		if(result.hasErrors()) {
//			return "store/teacher/create.jsp";
			return "teacher/create.jsp";
		}else {
			teacherSrv.addTeacher(teacherMdl);
//			return "redirect:/store";
			return "redirect:/home";
		}
	}
	
	// view/manage one teacher
//	@GetMapping("/store/teacher/{id}")
	@GetMapping("/teacher/{id}")
	public String showTeacher(
			@PathVariable("id") Long id
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		TeacherMdl intVar = teacherSrv.findById(id);
		
		model.addAttribute("teacher", intVar);
		model.addAttribute("assignedCategories", studentSrv.getAssignedTeachers(intVar));
		model.addAttribute("unassignedCategories", studentSrv.getUnassignedTeachers(intVar));
		
//		return "/store/teacher/record.jsp";
		return "teacher/record.jsp";
	}
	
	// process edits to that one teacher
//	@PostMapping("/store/teacher/{id}")
	@PostMapping("/teacher/{id}")
	public String editTeacher(
			@PathVariable("id") Long id
			, @RequestParam(value="studentId") Long catId // requestParam is only used with regular HTML form 
			,  Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		TeacherMdl teacher = teacherSrv.findById(id);
		StudentMdl student = studentSrv.findById(catId);
		
		teacher.getStudentMdl().add(student);
		
		teacherSrv.updateTeacher(teacher);
		
		model.addAttribute("assignedCategories", studentSrv.getAssignedTeachers(teacher));
		model.addAttribute("unassignedCategories", studentSrv.getUnassignedTeachers(teacher));
//		return "redirect:/store/teacher/" + id;
		return "redirect:/teacher/" + id;
	}
	
	// this is JRF method to remove instance of a cat-pro join record from the teacher. 
	// don't use deleteMapping here... just use a link on the page to call the 'remove' method to remove item from list, something like that. 
	
	
	
//    @DeleteMapping("/store/removeTeacherStudentJoin")
	@DeleteMapping("/removeTeacherStudentJoin")
    public String whackProdCatJoin(
//    		@PathVariable("publicationId") Long publicationId
    		@RequestParam(value="studentId") Long studentId // requestParam is only used with regular HTML form
    		, @RequestParam(value="teacherId") Long teacherId // requestParam is only used with regular HTML form
    		, @RequestParam(value="origin") Long originPath // requestParam is only used with regular HTML form
    		, HttpSession session
    		, RedirectAttributes redirectAttributes
    		) {

    	// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
//		here is the srv to remove this thing
//		but first need to get the some thing via id's coming from param
		
		TeacherMdl teacherObject = teacherSrv.findById(teacherId);
		StudentMdl studentObject  = studentSrv.findById(studentId); 
		
		teacherSrv.removeTeacherStudentJoin(studentObject, teacherObject); 
		
		
		if (originPath == 1) {
//			return "redirect:/store/teacher/" + teacherId;
			return "redirect:/teacher/" + teacherId;
		} else {
//			return "redirect:/store/student/" + studentId;
			return "redirect:/student/" + studentId;
		}
    }
	
	

	
	
// end of ctl
}
