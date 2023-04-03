package fr.eni.ecole.trocenchere.dal;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Categorie;

public interface DAOCategorie {
	public Categorie selectById(int id) throws TrocEnchereException;

}
