package com.divesh.student;

import java.io.IOException;
import java.util.Map;

import com.divesh.dao.StudentDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class StudentController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		String sid = (session != null) ? (String) session.getAttribute("sid") : null;
		if(sid == null)
		{
			response.sendRedirect("StudentLogin.jsp");
			return;
		}
		
		String password = request.getParameter("password");
		int id = Integer.parseInt(sid);
		try
		{
			StudentDao dao = new StudentDao();
			Map<String, String> student = dao.getStudentDetails(id,password);
			Map<String, String> coach = dao.getCoachDetails(id);
			Map<String, String> sport = dao.getSportsDetails(id);
			
			if (coach.isEmpty()) 
			{
			    coach.put("name", "Not Assigned");
			    coach.put("phoneno", "N/A");
			    coach.put("game", "N/A");
			}

			if (sport.isEmpty()) 
			{
			    sport.put("name", "Not Assigned");
			    sport.put("game", "N/A");
			}
			
			request.setAttribute("student", student);
			request.setAttribute("sport", sport);
			request.setAttribute("coach",coach);
			
			RequestDispatcher rd = request.getRequestDispatcher("StudentDashboard.jsp");
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
