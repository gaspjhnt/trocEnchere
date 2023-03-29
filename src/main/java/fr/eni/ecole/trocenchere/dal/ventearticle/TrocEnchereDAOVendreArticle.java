package fr.eni.ecole.trocenchere.dal.ventearticle;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;

public interface TrocEnchereDAOVendreArticle {

	/**
	 * Permet d'inserrer une nouvelle catégorie
	 * @param categorie
	 * @throws TrocEnchereException
	 */
	public void insertCategorie(Categorie categorie) throws TrocEnchereException;
	
	/**
	 * Permet d'inserer un nouvel article
	 * @param article
	 * @throws TrocEnchereException
	 */
	public void insertArticle(Article article) throws TrocEnchereException;
	
	/**
	 * Permet de selectionner toutes les catégories disponnibles
	 * @return
	 * @throws TrocEnchereException
	 */
	public List<Categorie> selectAllCategorie() throws TrocEnchereException;
	
	/**
	 * permet de récuperer une seul catégorie par son ID
	 * @param id
	 * @return
	 * @throws TrocEnchereException
	 */
	public Categorie selectCategorieById(int id) throws TrocEnchereException;
	
	
}
