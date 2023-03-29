package fr.eni.ecole.trocenchere.dal;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface TrocEnchereDAODelete {

/**
 * Permet de delete un article
 * @param article
 * @throws TrocEnchereException 
 */
public void deleteArticle(int idArticle) throws TrocEnchereException;

/**
 * Permet de delete un utilisateur
 * @param utilisateur
 * @throws TrocEnchereException 
 */
public void deleteUtilisateur(int idUtilisateur) throws TrocEnchereException;

/**
 * Permet de delete une catégorie
 * @param categorie
 * @throws TrocEnchereException 
 */
public void deleteCategorie(int idCategorie) throws TrocEnchereException;

/**
 * Permet de delete une enchère
 * @param enchere
 * @throws TrocEnchereException 
 */
public void deleteEnchere(int idEnchere) throws TrocEnchereException;

/**
 * Permet de delete un retrait
 * @param retrait
 * @throws TrocEnchereException 
 */
public void deleteRetrait(int idRetrait) throws TrocEnchereException;
}
