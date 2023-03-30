
package fr.eni.ecole.trocenchere.dal.connexion;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface ConnexionDAO {
	
	public Utilisateur selectMdpAndPseudo(String pseudo, String mdp) throws TrocEnchereException;
	public Utilisateur selectMdpAndEmail(String email, String mdp) throws TrocEnchereException;
}