package com.mmit.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mmit.entity.Student;

@WebServlet({"/add-student"})
public class StudentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "addstudent");
		getServletContext().getRequestDispatcher("/student-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//get parameter data
			String name=req.getParameter("studentname");
			String email=req.getParameter("email");
			String age=req.getParameter("age");
			String year=req.getParameter("year");
			String address=req.getParameter("address");
			String dob=req.getParameter("dob");
			//create obj
			Student s=new Student();
			s.setAge(Integer.parseInt(age));
			s.setAddress(address);
			s.setName(name);
			s.setEmail(email);
			s.setDateOfBirth(LocalDate.parse(dob));
			s.setYear(year);
			//get Session object
			HttpSession session=req.getSession(true);
			List<Student> student=(List<Student>) session.getAttribute("studentList");
			if(student==null)
				student=new ArrayList<Student>();
			//add course obj to list
			student.add(s);
			//add course obj to session
			session.setAttribute("studentList", student);
			//invoke other web page
			resp.sendRedirect("students.jsp");
	}
}
