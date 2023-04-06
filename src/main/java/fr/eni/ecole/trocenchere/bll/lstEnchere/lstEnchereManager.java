package fr.eni.ecole.trocenchere.bll.lstEnchere;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface lstEnchereManager {
	
	List<Article> getAllArticlesByDate(LocalDate date) throws TrocEnchereException;


	/**
	 * Selectionner toutes les enchères d'un utilisateur (acheteur)
	 * @param utilisateur
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Enchere> selectEnchereByUser(Utilisateur utilisateur) throws TrocEnchereException;
	public List<Article> selectArticleByUser(Utilisateur utilisateur) throws TrocEnchereException;
}
