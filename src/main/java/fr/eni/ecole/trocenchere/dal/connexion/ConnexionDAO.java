package fr.eni.ecole.trocenchere.dal.connexion;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface ConnexionDAO {

	/**
	 * Methode pour récuperer une utilisateur a l'aide de ses informations de connexions
	 * @param pseudoOuMail
	 * @param mdp
	 * @return
	 * @throws TrocEnchereException
	 */
	Utilisateur selectMdpAndPseudo(String pseudoOuMail, String mdp) throws TrocEnchereException;
	

}
