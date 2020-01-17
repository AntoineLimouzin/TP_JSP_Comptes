package com.intiformation.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class pageClientServlet extends HttpServlet {

	ClientService cs = new ClientService();
	CompteService cms = new CompteService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("index".equals(req.getParameter("logout"))) {
			HttpSession session = req.getSession(false);
			session.invalidate();
			req.getRequestDispatcher("accueil.jsp").forward(req, resp);
		}
		else if ("new".equals(req.getParameter("logout"))) {
			HttpSession session = req.getSession(false);
			session.invalidate();
			req.getRequestDispatcher("ajoutClient.jsp").forward(req, resp);
		}
	}

	public boolean depot(HttpServletRequest req, HttpServletResponse resp)
	{
		return cms.ajouterArgent(cms.findByClientId((int)req.getSession(false).getAttribute("id_client")), Double.parseDouble(req.getParameter("inputDepot")));
	}

	public boolean retrait(HttpServletRequest req, HttpServletResponse resp)
	{
		return cms.retirerArgent(cms.findByClientId((int) req.getSession(false).getAttribute("id_client")), Double.parseDouble(req.getParameter("inputRetrait")));
	}

	public boolean transfert(HttpServletRequest req, HttpServletResponse resp)
	{
		Compte debiteur = cms.findByClientId(Integer.parseInt(req.getParameter("personnes")));
		Compte crediteur = cms.findByClientId((int)req.getSession(false).getAttribute("id_client"));

		return cms.transfererArgent(crediteur, debiteur, Double.parseDouble(req.getParameter("inputTransfert")));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String button = req.getParameter("operation");
		HttpSession session = req.getSession(false);
		if ("depot".equals(button)) {
			depot(req, resp);
			req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
		} else if ("retrait".equals(button)) {
			retrait(req, resp);
			req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
		} else if ("transfert".equals(button)) {
			transfert(req, resp);
			req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
		} else if ("chAdresse".equals(button)) {
			Client cl = cs.findById((int)session.getAttribute("id_client"));
			String adresse = req.getParameter("chAdresse");
			//System.out.println(cl);
			//System.out.println(adresse);
			cl.setAdresse(adresse);
			cs.modifier(cl);
			req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
		} else if ("chTel".equals(button)) {
			Client cl = cs.findById((int)session.getAttribute("id_client"));
			String tel = req.getParameter("chTel");
			cl.setTelephone(tel);
			cs.modifier(cl);
			req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
		} else if ("chCodePostal".equals(button)) {
			Client cl = cs.findById((int)session.getAttribute("id_client"));
			String cpS = req.getParameter("chCodePostal");
			int cp = Integer.parseInt(cpS);
			cl.setCode_postal(cp);
			cs.modifier(cl);
			req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
		} else if ("chVille".equals(button)) {
			Client cl = cs.findById((int)session.getAttribute("id_client"));
			String ville = req.getParameter("chVille");
			cl.setVille(ville);
			cs.modifier(cl);
			req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
		} else if ("chDm".equals(button)) {
			Compte_Courant cm = (Compte_Courant) cms.findByClientId((int)session.getAttribute("id_client"));
			Double decMax = Double.parseDouble(req.getParameter("chDm"));
			if (cm.getSolde() <= -decMax)
			{
				cm.setDecouvert_autorise(decMax);
				cms.modifier(cm);
			}
			req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
		} else if ("chTaux".equals(button)) {
			Compte_Epargne cm = (Compte_Epargne) cms.findByClientId((int)session.getAttribute("id_client"));
			Double taux = Double.parseDouble(req.getParameter("chTaux"));
			cm.setTaux(taux);
			cms.modifier(cm);
			req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
		} else if ("chPwd".equals(button)) {
			Client cl = cs.findById((int)session.getAttribute("id_client"));
			if (cl.getPassword().equals(req.getParameter("oldPwdIn")) && req.getParameter("chPwd") != null)
			{
				cl.setPassword(req.getParameter("chPwd"));
				cs.modifier(cl);
				req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
			}
			else
			{
				req.getRequestDispatcher("pageClient.jsp").forward(req, resp);
			}
		} else if ("suppr".equals(button)) {
			//Compte co = cms.findByClientId((int)session.getAttribute("id_client"));
			cs.supprimer((int)session.getAttribute("id_client"));
//			if (cs.supprimer((int)session.getAttribute("id_client")))
//			{
//				cms.supprimer(co.getId_compte());
//			}
			req.getRequestDispatcher("accueil.jsp").forward(req, resp);
		}
	}

}
