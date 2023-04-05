package fr.eni.ecole.trocenchere.dal.detailsArticle;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;

public interface TrocEnchereDAODetailsArticle {

	
	/**
	 * Methode qui selectionne un article par son ID dans la BDD
	 * @param id
	 * @return
	 * @throws TrocEnchereException
	 */
	public Article selectArticleById(int id) throws TrocEnchereException;
	
	/**
	 * Methode qui selectionne un retrait par un article dans la BDD
	 * @param article
	 * @return
	 * @throws TrocEnchereException
	 */
	public Retrait selectRetraitByArticle(Article article) throws TrocEnchereException;
	
	/**
	 * Methode qui selectionne une enchere par un article dans la BDD
	 * @param article
	 * @return
	 * @throws TrocEnchereException
	 */
	public List<Enchere> selectEnchereByArticle(Article article) throws TrocEnchereException;
	
	/**
	 * 	Permet d'inserer un retrait dans la BDD
	 * 	@param retrait
	 * @throws TrocEnchereException
	 */
	public void insertRetrait(Retrait retrait) throws TrocEnchereException;	
	
	/**
	 * Permet de modifier un article
	 * @param article
	 * @throws TrocEnchereException
	 */
	public void updateArticle (Article article) throws TrocEnchereException;
	
	/**
	 * Permet de modifier un retrait
	 * @param retrait
	 * @throws TrocEnchereException
	 */
	public void updateRetrait (Retrait retrait) throws TrocEnchereException;

	/**
	 * Permet de delete un article
	 * @param article
	 * @throws TrocEnchereException 
	 */
	public void deleteArticle(int idArticle) throws TrocEnchereException;
	
	/**
	 * Permet de delete un retrait
	 * @param retrait
	 * @throws TrocEnchereException 
	 */
	public void deleteRetrait(int idRetrait) throws TrocEnchereException;
	

	/**
	 * Permet de delete une enchère
	 * @param enchere
	 * @throws TrocEnchereException 
	 */
	public void deleteEnchere(int idEnchere) throws TrocEnchereException;
}
