package fr.eni.ecole.trocenchere.dal.ALIBABA;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

/**
 * @author crobin2023
 *
 */
public interface TrocEnchereDAOUpdate {

	/**
	 * Permet de modifier un article
	 * @param article
	 * @throws TrocEnchereException
	 */
	public void updateArticle (Article article) throws TrocEnchereException;
	/**
	 * Permet de modifier un utilisateur
	 * @param utilisateur
	 * @throws TrocEnchereException
	 */
	public void updateUtilisateur (Utilisateur utilisateur) throws TrocEnchereException;
	/**
	 * Permet de modifier un retrait
	 * @param retrait
	 * @throws TrocEnchereException
	 */
	public void updateRetrait (Retrait retrait) throws TrocEnchereException;
	/**
	 * permet de modifier une catégorie
	 * @param categorie
	 * @throws TrocEnchereException
	 */
	public void updateCategorie (Categorie categorie) throws TrocEnchereException;
	/**
	 * Permet de modifier une enchère
	 * @param enchere
	 * @throws TrocEnchereException
	 */
	public void updateEnchere (Enchere enchere) throws TrocEnchereException;
	
	
	
	
	
}
