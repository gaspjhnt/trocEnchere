package fr.eni.ecole.trocenchere.bll.connexion;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
public interface ConnexionManager {
	
	/**
	 * Methode pour récuperer une utilisateur a l'aide de ses informations de connexions + conditions
	 * @param pseudoOuEmail
	 * @param mdp
	 * @return
	 * @throws TrocEnchereException
	 */
	public Utilisateur login(String pseudoOuEmail, String mdp) throws TrocEnchereException;
}
