package com.intiformation.tpcomptes.modele;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="comptes")
@NamedQuery(name="Compte.findAll", query="SELECT c FROM Compte c")
public class Compte {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_compte;
	
	private double solde;
	
	@OneToOne(mappedBy="compte", cascade=CascadeType.ALL)
	private Client client;
	
	public Compte()
	{
		solde = 0.0;
	}
	
	public Compte(int id_compte)
	{
		this.id_compte = id_compte;
		solde = 0.0;
	}
	
	
	
	public Compte(int id_compte, double solde) {
		this.id_compte = id_compte;
		this.solde = solde;
	}
	
	public Compte(double solde) {
		this.solde = solde;
	}

	/**
	 * @return the solde
	 */
	public Double getSolde() {
		return solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(Double solde) {
		this.solde = solde;
	}
	
	public int getId_compte() {
		return id_compte;
	}

	public void setId_compte(int id_compte) {
		this.id_compte = id_compte;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String toString()
	{
		return "Solde : " + solde + "€";		
	}

}
