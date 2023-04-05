package fr.eni.ecole.trocenchere.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface DAOArticle {

	public List<Article> selectArticleByDateFin(LocalDate date) throws TrocEnchereException;	
	
	/**
	 * Selectionner les articles par son utilisateur (donc par son vendeur)
	 * @param utilisateur
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Article> selectArticleByUser(Utilisateur utilisateur) throws TrocEnchereException;

	/**
	 * Permet de modifier un article
	 * @param article
	 * @throws TrocEnchereException
	 */
	public void updateArticle (Article article) throws TrocEnchereException;
}
