package fr.eni.ecole.trocenchere.bll.detailsArticle;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;

public interface DetailsArticleManager {
	
	
	/**
	 * Methode pour récuperer un article avec son ID + conditions
	 * @param id
	 * @return
	 * @throws TrocEnchereException
	 */
	public Article selectArticleById(int id) throws TrocEnchereException;
	
	/**
	 * Methode pour récuperer un retrait avec son article + conditions
	 * @param article
	 * @return
	 * @throws TrocEnchereException
	 */
	public Retrait selectRetraitByArticle(Article article) throws TrocEnchereException;
	
	/**
	 * Methode pour récuperer une enchère avec son article + conditions
	 * @param article
	 * @return
	 * @throws TrocEnchereException
	 */
	public List<Enchere> selectEnchereByArticle(Article article) throws TrocEnchereException;
	
	/**
	 * Methode pour insérer un retrait en BDD + conditions
	 * @param retrait
	 * @throws TrocEnchereException
	 */
	public void insertRetrait(Retrait retrait) throws TrocEnchereException;
	
	
	/**
	 * Permet d'ajouter une enchère avec des conditions
	 * @param article
	 * @throws TrocEnchereException
	 */
	public void insertEnchere(Enchere enchere) throws TrocEnchereException;
}
