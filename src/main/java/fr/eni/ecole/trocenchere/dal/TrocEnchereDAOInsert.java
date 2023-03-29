package fr.eni.ecole.trocenchere.dal;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

/**
 * @author jdesnoes2023
 *
 */
public interface TrocEnchereDAOInsert {
	
	/**
	 * Permet d'insérer un utilisateur dans la base de données
	 * @param utilisateur
	 * @throws TrocEnchereException
	 */
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereException;
	/**
	 * Permet d'insérer un article dans la base de données
	 * @param article
	 * @throws TrocEnchereException
	 */
	public void insertArticle(Article article) throws TrocEnchereException;
	/**
	 * Permet d'insérer une enchère dans la base de données
	 * @param enchere
	 * @throws TrocEnchereException
	 */
	public void insertEnchere(Enchere enchere) throws TrocEnchereException;
	/**
	 * Permet d'insérer une catégorie dans la base de données
	 * @param categorie
	 * @throws TrocEnchereException
	 */
	public void insertCategorie(Categorie categorie) throws TrocEnchereException;
	/**
	 * Permet d'insérer un retrait dans la base de données
	 * @param retrait
	 * @throws TrocEnchereException
	 */
	public void insertRetrait(Retrait retrait) throws TrocEnchereException;
	
}
