package fr.eni.ecole.trocenchere.dal.ventearticle;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface TrocEnchereDAOVendreArticle {

	/**
	 * Permet d'inserer une nouvelle catégorie
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
	 * permet d'inserer un utilisateur en attendant la session de connexion     *** TEMPORAIRE ***
	 * @param utilisateur
	 * @throws TrocEnchereException
	 */
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereException;

	

	/**
	 * Pareil permet d'inserer un retrait
	 * @param retrait
	 * @throws TrocEnchereException
	 */
	public void insertRetrait(Retrait retrait) throws TrocEnchereException;
	
	/**
	 * Permet de selectionner toutes les catégories disponibles
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
