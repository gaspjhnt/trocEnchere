package fr.eni.ecole.trocenchere.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;


/**
 * @author gjohanet2023
 *
 */
public interface TrocEnchereDAOSelect {
	
	
	
	/**
	 * Select un utilisateur par son id.
	 * @param id
	 * @return
	 * @throws TrocEnchereException 
	 */
	public Utilisateur SelectUserById(int id) throws TrocEnchereException;
	
	
	/**
	 * Selectionner les articles par son utilisateur (donc par son vendeur)
	 * @param utilisateur
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Article> selectArticleByUser(Utilisateur utilisateur) throws TrocEnchereException;
	
	
	/**
	 * Selectionner les articles 
	 * @param categorie
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Article> selectArticleByCate(Categorie categorie) throws TrocEnchereException;
	
	
	
	
	
	/**
	 * Selectionner tous les articles en fonction d'une date (peut être les deux fin et début d'enchère)
	 * @param date
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Article> selectArticleByDateDebut(LocalDate date) throws TrocEnchereException;
	
	
	
	/**
	 * Selectionner tous les articles en fonction d'une date (peut être les deux fin et début d'enchère)
	 * @param date
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Article> selectArticleByDateFin(LocalDate date) throws TrocEnchereException;
	
	
	
	/**
	 * Selectionner toutes les enchères d'un article
	 * @param article
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Enchere> selectEnchereByArticle(Article article) throws TrocEnchereException;
	
	
	/**
	 * Selectionner toutes les enchères d'un utilisateur (acheteur)
	 * @param utilisateur
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Enchere> selectEnchereByUser(Utilisateur utilisateur) throws TrocEnchereException;
	
	
	/**
	 * Selectionner une catégorie par son ID
	 * @param id
	 * @return
	 * @throws TrocEnchereException 
	 */
	public Categorie selectCategorieById(int id) throws TrocEnchereException;
	
	
	/**
	 * Selectionner toutes les catégories
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Categorie> selectAllCategorie() throws TrocEnchereException;
	
	
	/**
	 * Selectionner le retrait d'un article
	 * @param article
	 * @return
	 * @throws TrocEnchereException 
	 */
	public Retrait selectRetraitByArticle(Article article) throws TrocEnchereException;

}
