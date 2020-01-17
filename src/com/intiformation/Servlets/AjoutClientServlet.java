package com.intiformation.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.intiformation.tpcomptes.modele.Client;
import com.intiformation.tpcomptes.modele.Compte;
import com.intiformation.tpcomptes.modele.Compte_Courant;
import com.intiformation.tpcomptes.modele.Compte_Epargne;
import com.intiformation.tpcomptes.service.ClientService;
import com.intiformation.tpcomptes.service.CompteService;

public class AjoutClientServlet extends HttpServlet {

	ClientService cs = new ClientService();
	CompteService cms = new CompteService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
			String prenom = req.getParameter("lastName");
			String nom = req.getParameter("firstName");
			String adresse = req.getParameter("address");
			String ville = req.getParameter("city");
			String tel = req.getParameter("phone");
			String pwd = req.getParameter("pwd");
			String pwd_bis = req.getParameter("pwd_bis");
			int codePostal;

			try {
				codePostal = Integer.parseInt(req.getParameter("zip"));
			} catch (Exception e) {
				codePostal = 0;
			}

			Compte compte = null;

			switch (req.getParameter("choixComptes")) {
			case "normal":
				compte = new Compte();
				break;
			case "courant":
				compte = new Compte_Courant();
				break;
			case "epargne":
				compte = new Compte_Epargne();
				break;
			default:
				break;
			}

			cms.ajouter(compte);

			Compte cmp = cms.findAll().get(cms.findAll().size()-1);
			
			String[] pps = {nom, prenom, adresse, ville, tel, pwd};
			ArrayList<String> props = new ArrayList<>();
			
			for (String pr : pps) {
				props.add(pr);
			}
			
			if (props.contains(null) || codePostal == 0 || (!pwd.equals(pwd_bis)))
			{
				req.getRequestDispatcher("accueil.jsp").forward(req, resp);
			}
			else
			{
				Client client = new Client(nom, prenom, adresse, codePostal, ville, tel, pwd);
				
				client.setCompte(cmp);

				cs.ajouter(client);
				
				HttpSession session = req.getSession(true);

				Client clt = cs.findByNom(nom, prenom);
				
				session.setAttribute("id_client",clt.getId_client());

				req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
			}
			
		}
	}




