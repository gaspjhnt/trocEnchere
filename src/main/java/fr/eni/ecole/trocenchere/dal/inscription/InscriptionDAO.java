package fr.eni.ecole.trocenchere.dal.inscription;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface InscriptionDAO {
	
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereException;
	public List<String> getAllPseudo() throws TrocEnchereException;
	public List<String> getAllEmail() throws TrocEnchereException;
	
}
