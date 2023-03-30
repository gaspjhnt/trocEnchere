package fr.eni.ecole.trocenchere.bll.connexion;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
public interface ConnexionManager {
	
	public boolean selectPseudoAndMdp(String pseudo, String mdp) throws TrocEnchereException;
	public boolean selectEmailAndMdp(String email, String mdp) throws TrocEnchereException;
}
