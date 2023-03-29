package fr.eni.ecole.trocenchere.bll.inscription;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface InscriptionManager {
	
	public Utilisateur ajouter(String pseudo,String nom, String prenom, String email,String numTel,String rue,
			String codePostal, String ville,String mdp) throws TrocEnchereException;
}
