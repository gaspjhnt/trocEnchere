package fr.eni.ecole.trocenchere.dal;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface DAOUtilisateur {
	
	public Utilisateur selectById(int id) throws TrocEnchereException;
	
	/**
	 * Permet de modifier un utilisateur
	 * @param utilisateur
	 * @throws TrocEnchereException
	 */
	public void updateUtilisateur (Utilisateur utilisateur) throws TrocEnchereException;
	
	void deleteUtilisateur(int idUtilisateur) throws TrocEnchereException;
}
