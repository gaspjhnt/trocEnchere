package fr.eni.ecole.trocenchere.dal;

import java.util.List;

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
	
	public List<String> getAllPseudo() throws TrocEnchereException;
	public List<String> getAllEmail() throws TrocEnchereException;
	
	public Utilisateur getUtilisateurByMail(String email) throws TrocEnchereException;
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws TrocEnchereException;
}
