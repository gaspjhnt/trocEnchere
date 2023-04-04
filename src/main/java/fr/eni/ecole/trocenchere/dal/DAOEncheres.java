package fr.eni.ecole.trocenchere.dal;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Enchere;

public interface DAOEncheres {
	
	public void insertEnchere(Enchere enchere) throws TrocEnchereException;
}
