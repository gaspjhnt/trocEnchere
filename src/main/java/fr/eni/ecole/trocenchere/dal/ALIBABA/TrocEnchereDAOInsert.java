package fr.eni.ecole.trocenchere.dal.ALIBABA;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface TrocEnchereDAOInsert {
	
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereException;
	public void insertArticle(Article article) throws TrocEnchereException;
	public void insertEnchere(Enchere enchere) throws TrocEnchereException;
	public void insertCategorie(Categorie categorie) throws TrocEnchereException;
	public void insertRetrait(Retrait retrait) throws TrocEnchereException;
	
}
