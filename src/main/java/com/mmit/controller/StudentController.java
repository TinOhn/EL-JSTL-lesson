package com.mmit.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mmit.entity.Student;

@WebServlet({"/add-student"})
@MultipartConfig
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
			Part imgPart=req.getPart("photo");
			
			String imgFileName=imgPart.getSubmittedFileName();//user.jpg
			//System.out.pritln("imgFileName:"+imgFileName);
			String currentName=imgFileName.substring(0, imgFileName.lastIndexOf("."));//user
			//System.out.println("currentName:"+currentName);
			String newName=""+System.currentTimeMillis();//user12345654
			//System.out.println("newName:"+newName);
			imgFileName=imgFileName.replace(currentName, newName);//user.jpg=>user12345654.jpg
			
			//create obj
			Student s=new Student();
			s.setAge(Integer.parseInt(age));
			s.setAddress(address);
			s.setName(name);
			s.setEmail(email);
			s.setDateOfBirth(LocalDate.parse(dob));
			s.setYear(year);
			s.setProfile(imgFileName);
			
			//get Session object
			HttpSession session=req.getSession(true);
			List<Student> student=(List<Student>) session.getAttribute("studentList");
			if(student==null)
				student=new ArrayList<Student>();
			//add course obj to list
			student.add(s);
			//add course obj to session
			session.setAttribute("studentList", student);
			
			//save client upload file in server
			String rootPath=getServletContext().getRealPath("");
			String dirPath=rootPath+File.separator+"imgUploads";
			File rootDir=new File(dirPath);
			if(!rootDir.exists())//check already exist imgUploads folder
				rootDir.mkdirs();
			imgPart.write(rootDir+File.separator+imgFileName);
			
			//invoke other web page
			resp.sendRedirect("students.jsp");
	}
}
