package com.credai.myservlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name ="UserServlet" , urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter out = resp.getWriter();
	
	String first_name = req.getParameter("first_name");

	String last_name = req.getParameter("last_name");
	
	String name = first_name + " " + last_name;

	out.println("<h3>Welcome " + name + " </h3>");
	}
}
