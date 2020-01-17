package com.intiformation.tpcomptes.modele;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="clients")
@NamedQueries({
	@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c"),
	@NamedQuery(name="Client.findAllExcept", query="SELECT c FROM Client c WHERE NOT c.id_client = :id"),
	@NamedQuery(name="Client.findByName", query="SELECT c FROM Client c WHERE c.prenom = :prenom AND c.nom = :nom"),
	@NamedQuery(name="Client.exists", query="SELECT COUNT(c) FROM Client c WHERE c.id_client = :id AND c.password = :pwd")
})
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id_client;
	
	String nom;
	String prenom;
	String adresse;
	int code_postal;
	String ville;
	String telephone;
	String password;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="COMPTE_ID", referencedColumnName="id_compte")
	private Compte compte;
	
	public Client()
	{
		
	}
	
	public Client(int id_client, String nom, String prenom, String adresse, int code_postal, String ville,
			String telephone, String password) {
		super();
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.ville = ville;
		this.telephone = telephone;
		this.password = password;
	}
	
	public Client(String nom, String prenom, String adresse, int code_postal, String ville,
			String telephone, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.ville = ville;
		this.telephone = telephone;
		this.password = password;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(int code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public String toString()
	{
		return prenom + " " + nom + " - " + adresse + ", " + code_postal + ", " + ville + " - " + telephone;
	}

}
