package com.ibm.mapping.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/overviewServlet")
public class OverviewServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,FileNotFoundException {
	 RequestDispatcher rd = request.getRequestDispatcher("./Overview.jsp");
		rd.forward(request, response);
}
	
}
