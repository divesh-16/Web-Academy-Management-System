package com.divesh.admin;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String option = request.getParameter("option");
		
		if(option.equals("student"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
			rd.include(request, response);
		}
		else if(option.equals("coach"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
			rd.include(request, response);
		}
		else if(option.equals("accessories"))
		{
			RequestDispatcher rd =request.getRequestDispatcher("AccessoriesPanel.jsp");
			rd.forward(request, response);
		}
		else if(option.equals("batch"))
		{
			RequestDispatcher rd =request.getRequestDispatcher("BatchPanel.jsp");
			rd.forward(request, response);
		}
		else if(option.equals("supplier"))
		{
			RequestDispatcher rd =request.getRequestDispatcher("SupplierPanel.jsp");
			rd.forward(request, response);
		}
		else if(option.equals("order"))
		{
			RequestDispatcher rd =request.getRequestDispatcher("OrderUpdate.jsp");
			rd.forward(request, response);
		}
		else if(option.equals("report"))
		{
			RequestDispatcher rd =request.getRequestDispatcher("./GenerateReports");
			rd.forward(request, response);
		}
		else if(option.equals("mydata"))
		{
			RequestDispatcher rd =request.getRequestDispatcher("./AdminUpdate");
			rd.forward(request, response);
		}
		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
