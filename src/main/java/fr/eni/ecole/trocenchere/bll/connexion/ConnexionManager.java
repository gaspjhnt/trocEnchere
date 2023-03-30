package fr.eni.ecole.trocenchere.bll.connexion;

import fr.eni.ecole.trocenchere.TrocEnchereException;
public interface ConnexionManager {
	
	public void selectPseudoAndMdp(String pseudo, String mdp) throws TrocEnchereException;
	public void selectEmailAndMdp(String email, String mdp) throws TrocEnchereException;
}
