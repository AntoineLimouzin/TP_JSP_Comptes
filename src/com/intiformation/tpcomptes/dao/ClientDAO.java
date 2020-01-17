package com.intiformation.tpcomptes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.intiformation.tpcomptes.modele.Client;

public class ClientDAO implements IDatabaseDAO<Client> {

	private String PU = "00_TP_JSP_Comptes";
	EntityManagerFactory emf;
	EntityManager manager;
	EntityTransaction transaction;

	/**
	 * Constructeur : initialise la connection à la BDD
	 */
	public ClientDAO() {
		emf = Persistence.createEntityManagerFactory(PU);
		manager = emf.createEntityManager();
		transaction = manager.getTransaction();
	}

	/**
	 * @return la liste des clients
	 */
	@Override
	public List<Client> getAll() {
		return manager.createNamedQuery("Client.findAll").getResultList();
	}
	
	public boolean exists(int id, String password)
	{
		Query query = manager.createNamedQuery("Client.exists");
		query.setParameter("id", id);
		query.setParameter("pwd", password);
		return (((Long)query.getSingleResult())==1);
	}

	public List<Client> getAllExcept(Client c) {
		Query query = manager.createNamedQuery("Client.findAllExcept");
		query.setParameter("id", c.getId_client());
		return query.getResultList();
	}
	
	public Client getByNom(String nom, String prenom) {
		Query query = manager.createNamedQuery("Client.findByName");
		query.setParameter("nom", nom);
		query.setParameter("prenom", prenom);
		return (Client) query.getSingleResult();
	}
	
	/**
	 * @return le client correspondant à l'identifiant id
	 */
	@Override
	public Client getById(int id) {
		return manager.find(Client.class, id);
	}

	/**
	 * ajoute le client c
	 * @return true si l'ajout a fonctionné, false sinon
	 */
	@Override
	public boolean add(Client c) {
		
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
	 * modifie le client c
	 * @return true si la modification a fonctionnée, false sinon
	 */
	@Override
	public boolean update(Client c) {
		
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
	 * supprime le client correspondant à l'identifiant id
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

}
