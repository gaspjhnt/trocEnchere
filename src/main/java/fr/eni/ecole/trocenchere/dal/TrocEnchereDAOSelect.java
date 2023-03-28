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
/**
 * @author gjohanet2023
 *
 */
/**
 * @author gjohanet2023
 *
 */
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
	 */
	public List<Article> selectArticleByUser(Utilisateur utilisateur);
	
	
	/**
	 * Selectionner les articles 
	 * @param categorie
	 * @return
	 */
	public List<Article> selectArticleByCate(Categorie categorie);
	
	
	
	
	
	/**
	 * Selectionner tous les articles en fonction d'une date (peut être les deux fin et début d'enchère)
	 * @param date
	 * @return
	 */
	public List<Article> selectArticleByDateDebut(LocalDate date);
	
	
	
	/**
	 * Selectionner tous les articles en fonction d'une date (peut être les deux fin et début d'enchère)
	 * @param date
	 * @return
	 */
	public List<Article> selectArticleByDateFin(LocalDate date);
	
	
	
	/**
	 * Selectionner toutes les enchères d'un article
	 * @param article
	 * @return
	 */
	public List<Enchere> selectEnchereByArticle(Article article);
	
	
	/**
	 * Selectionner toutes les enchères d'un utilisateur (acheteur)
	 * @param utilisateur
	 * @return
	 */
	public List<Enchere> selectEnchereByUser(Utilisateur utilisateur);
	
	
	/**
	 * Selectionner une catégorie par son ID
	 * @param id
	 * @return
	 */
	public Categorie selectCategorieById(int id);
	
	
	/**
	 * Selectionner toutes les catégories
	 * @return
	 */
	public List<Categorie> selectAllCategorie();
	
	
	/**
	 * Selectionner le retrait d'un article
	 * @param article
	 * @return
	 */
	public Retrait selectRetraitByArticle(Article article);

}
