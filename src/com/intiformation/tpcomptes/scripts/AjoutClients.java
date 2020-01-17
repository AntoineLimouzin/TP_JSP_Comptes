package com.intiformation.tpcomptes.scripts;

import com.intiformation.tpcomptes.modele.Client;
import com.intiformation.tpcomptes.modele.Compte;
import com.intiformation.tpcomptes.modele.Compte_Courant;
import com.intiformation.tpcomptes.modele.Compte_Epargne;
import com.intiformation.tpcomptes.service.ClientService;

public class AjoutClients {
	
	

	public static void main(String[] args) {
		
		ClientService cs = new ClientService();
		
		Client[] clients = {new Client("Miron", "Robert", "5, boulevard Aristide Briand", 33100, "Le Bouscat", "05.69.90.88.82", "password"),
				new Client("Hétu", "Joséphine", "76, rue des Nations Unies", 97470, "Saint-Benoît", "02.00.76.44.83", "password"),
				new Client("Provencher", "Iven", "94, rue Jean Vilar", 25000, "Besançon", "03.42.20.43.76", "password"),
				new Client("Grivois", "Nicolette", "57, Rue Hubert de Lisle", 59120, "Loos", "03.48.08.77.16", "password"),
				new Client("Maheu", "Bruce", "90, rue de l'Epeule", 92500, "Rueil-Malmaison", "01.26.85.26.41", "password"),
				new Client("Ratté", "Frédérique", "73, rue Pierre Motte", 97180, "Sainte-Anne", "05.16.76.51.32", "password")};
		
		//tab comptes
		
		Compte[] comptes = {new Compte_Courant(), new Compte(), new Compte_Epargne(), new Compte_Courant(), new Compte(), new Compte_Epargne()};
		
		for (int i=0; i<clients.length; i++) {
			clients[i].setCompte(comptes[i]);
			cs.ajouter(clients[i]);
		}


	}

}
