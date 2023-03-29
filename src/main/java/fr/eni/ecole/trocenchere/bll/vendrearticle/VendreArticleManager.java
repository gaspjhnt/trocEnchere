package fr.eni.ecole.trocenchere.bll.vendrearticle;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;

public interface VendreArticleManager {
	
	/**
	 * Permet d'insérer une nouvelle catégorie si elle n'existe pas déjà (libelle)
	 * et si son libelle est < 45 char et > a 1 char
	 * @param categorie
	 * @throws TrocEnchereException 
	 */
	public void insertCategorie(Categorie categorie) throws TrocEnchereException;
	
	
	/**
	 * Permet d'inserer un nouvel article dans la base de données si:
	 * le nom est < a 45char et > 1 char
	 * la description < 255 char et > 1 char
	 * si la date de debut et de fin non null
	 * si la date de fin n'est pas avant ajd
	 * si le prix de depart est > 0 
	 * si la catégorie renseignée existe est si elle n'est pas null
	 * @param article
	 * @throws TrocEnchereException 
	 */
	public void insertArticle(Article article) throws TrocEnchereException;
	
	/**
	 * Permet de récuperer toutes les catégories disponnible dans la BDD
	 * @param categorie
	 * @throws TrocEnchereException 
	 */
	public void selectAllCategorie(Categorie categorie) throws TrocEnchereException;
	
	/**
	 * Permet de selectionner une catégorie par son ID
	 * @param id
	 * @throws TrocEnchereException 
	 */
	public void selectCategorieById(int id) throws TrocEnchereException;
}
