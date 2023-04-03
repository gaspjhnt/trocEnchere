package fr.eni.ecole.trocenchere.bll.Utilisateur;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface UtilisateurManager {

	public Utilisateur selectById(int id) throws TrocEnchereException;
	
	public void updateUtilisateur (Utilisateur utilisateur) throws TrocEnchereException;
	
	public void deleteUtilisateur (int noUtilisateur) throws TrocEnchereException;
}
