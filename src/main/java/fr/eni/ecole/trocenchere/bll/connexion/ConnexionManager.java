package fr.eni.ecole.trocenchere.bll.connexion;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
public interface ConnexionManager {
	
	/**
	 * Methode pour récuperer une utilisateur a l'aide de ses informations de connexions + conditions
	 * @param pseudoOuEmail
	 * @param mdp
	 * @return
	 * @throws TrocEnchereException
	 */
	public Utilisateur login(String pseudoOuEmail, String mdp) throws TrocEnchereException;
	
	/**
	 * Selectionner les articles par son utilisateur (donc par son vendeur)
	 * @param utilisateur
	 * @return
	 * @throws TrocEnchereException 
	 */
	public List<Article> selectArticleByUser(Utilisateur utilisateur) throws TrocEnchereException;
	
	/**
	 * Methode pour récuperer une enchère avec son article + conditions
	 * @param article
	 * @return
	 * @throws TrocEnchereException
	 */
	public List<Enchere> selectEnchereByArticle(Article article) throws TrocEnchereException;
	
	/**
	 * Permet de modifier un utilisateur
	 * @param utilisateur
	 * @throws TrocEnchereException
	 */
	public void updateUtilisateur (Utilisateur utilisateur) throws TrocEnchereException;
	
	/**
	 * Permet de modifier un article
	 * @param article
	 * @throws TrocEnchereException
	 */
	public void updateAticle (Article article) throws TrocEnchereException;
	
	
	
}
