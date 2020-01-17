package com.intiformation.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.intiformation.tpcomptes.service.ClientService;

public class AccueilServlet extends HttpServlet{

	ClientService cs = new ClientService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String choix = req.getParameter("btn-accueil");
		
		if ("existant".equals(choix))
		{
			if(cs.existe(Integer.parseInt(req.getParameter("users")), req.getParameter("inputPassword")))
			{
				HttpSession session = req.getSession(true);
				session.setAttribute("id_client", Integer.parseInt(req.getParameter("users")));
				//req.getRequestDispatcher("pageClient.jsp?idClient="+req.getParameter("users")).forward(req, resp);
				req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
			}
			else
			{
				resp.getWriter().println("test");
				req.getRequestDispatcher("accueil.jsp").forward(req, resp);
			}
			
		}
		else if ("nouveau".equals(choix))
		{
			req.getRequestDispatcher("ajoutClient.jsp").forward(req, resp);
		}
	}
	
	

}
