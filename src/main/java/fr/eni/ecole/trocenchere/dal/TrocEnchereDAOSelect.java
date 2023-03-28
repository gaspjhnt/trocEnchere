package fr.eni.ecole.trocenchere.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
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
public interface TrocEnchereDAOSelect {
	
	
	
	/**
	 * Select un utilisateur par son id.
	 * @param id
	 * @return
	 */
	public Utilisateur SelectUserById(int id);
	
	
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
	 * Selectionner 
	 * @param date
	 * @return
	 */
	public Article selectArticleByDate(LocalDate date);
	
	
	public Enchere selectEnchereByArticle(Article article);

}
