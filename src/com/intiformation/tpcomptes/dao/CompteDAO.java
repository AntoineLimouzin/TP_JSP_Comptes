package com.intiformation.tpcomptes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.intiformation.tpcomptes.modele.Client;
import com.intiformation.tpcomptes.modele.Compte;
import com.intiformation.tpcomptes.modele.Compte_Courant;

public class CompteDAO implements IDatabaseDAO<Compte> {

	//private Connection co;
	private String PU = "00_TP_JSP_Comptes";
	EntityManagerFactory emf;
	EntityManager manager;
	EntityTransaction transaction;

	/**
	 * Constructeur : initialise la connection à la BDD
	 */
	public CompteDAO() {
		emf = Persistence.createEntityManagerFactory(PU);
		manager = emf.createEntityManager();
		transaction = manager.getTransaction();
	}

	/**
	 * @return la liste des comptes
	 */
	@Override
	public List<Compte> getAll() {
		return manager.createNamedQuery("Compte.findAll").getResultList();
	}

	/**
	 * @return le compte correspondant à l'identifiant id
	 */
	@Override
	public Compte getById(int id) {
		return manager.find(Compte.class, id);
	}

	/**
	 * @return le compte correspondant à l'identifiant client id
	 */
	public Compte getByClientId(int id) {
		return manager.find(Client.class, id).getCompte();
	}

	/**
	 * ajoute le compte c
	 * @return true si l'ajout a fonctionné, false sinon
	 */
	@Override
	public boolean add(Compte c) {
		
		transaction.begin();
		
		try {
			manager.persist(c);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {transaction.rollback();}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * modifie le compte c
	 * @return true si la modification a fonctionnée, false sinon
	 */
	@Override
	public boolean update(Compte c) {
		
		transaction.begin();
		
		try {
			manager.merge(c);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {transaction.rollback();}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * supprime le commpte correspondant à l'identifiant id
	 * @return true si la suppression a fonctionnée, false sinon
	 */
	@Override
	public boolean delete(int id) {
		
		transaction.begin();
		
		try {
			manager.remove(manager.find(Client.class, id));
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {transaction.rollback();}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Associe le Compte com au Client cl
	 * @return true si l'association a fonctionnée, false sinon
	 */
	public boolean setClient(Compte com, Client cl)
	{
		
		transaction.begin();
		
		try {
			cl.setCompte(com);
			manager.merge(cl);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {transaction.rollback();}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retire la somme retrait au Compte compte
	 * @return true si le retrait a fonctionné, false sinon
	 */
	public boolean withdrawMoney(Compte compte, double retrait) {
		double solde = compte.getSolde();
		String nomClasse = compte.getClass().getName();
		double decouvert_autorise = 0.0;

		if (nomClasse == "com.intiformation.tpcomptes.modele.Compte_Courant")
		{
			Compte_Courant cc = (Compte_Courant) compte;
			decouvert_autorise = cc.getDecouvert_autorise();
		}

		if(solde - retrait > -decouvert_autorise)
		{
			compte.setSolde(solde-retrait);
			return update(compte);
		}
		else
		{
			return false;
		}
	}

	/**
	 * Ajoute la somme retrait au Compte compte
	 * @return true si l'ajout a fonctionné, false sinon
	 */
	public boolean addMoney(Compte compte, double depot)
	{
		return withdrawMoney(compte, -depot);
	}

	/**
	 * transfère la somme transfert du Compte crediteur vers le compte débiteur
	 * @return true si le transfert a fonctionné, false sinon
	 */
	public boolean transferMoney(Compte crediteur, Compte debiteur, double transfert)
	{
		if (withdrawMoney(crediteur, transfert)) 
		{
			return addMoney(debiteur, transfert);
		}
		else
		{
			return false;
		}
	}
}
