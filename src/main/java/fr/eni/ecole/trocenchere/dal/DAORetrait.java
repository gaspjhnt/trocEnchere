package fr.eni.ecole.trocenchere.dal;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Retrait;

public interface DAORetrait {
	
	/**
	 * Methode qui selectionne un retrait par un article dans la BDD
	 * @param article
	 * @return
	 * @throws TrocEnchereException
	 */
	public Retrait selectRetraitByArticle(Article article) throws TrocEnchereException;
	
	/**
	 * 	Permet d'inserer un retrait dans la BDD
	 * 	@param retrait
	 * @throws TrocEnchereException
	 */
	public void insertRetrait(Retrait retrait) throws TrocEnchereException;
	
	/**
	 * Permet de modifier un retrait
	 * @param retrait
	 * @throws TrocEnchereException
	 */
	public void updateRetrait (Retrait retrait) throws TrocEnchereException;

	/**
	 * Permet de delete un retrait
	 * @param retrait
	 * @throws TrocEnchereException 
	 */
	public void deleteRetrait(int idRetrait) throws TrocEnchereException;
}
