package fr.eni.ecole.trocenchere.dal.inscription;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface InscriptionDAO {
	
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereException;
	
}
