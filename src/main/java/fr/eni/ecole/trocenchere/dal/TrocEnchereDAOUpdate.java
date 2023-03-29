package fr.eni.ecole.trocenchere.dal;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface TrocEnchereDAOUpdate {

	public void updateArticle (Article article) throws TrocEnchereException;
	public void updateUtilisateur (Utilisateur utilisateur) throws TrocEnchereException;
	public void updateRetrait (Retrait retrait) throws TrocEnchereException;
	public void updateCategorie (Categorie categorie) throws TrocEnchereException;
	public void updateEnchere (Enchere enchere) throws TrocEnchereException;
	
	
	
	
	
}
