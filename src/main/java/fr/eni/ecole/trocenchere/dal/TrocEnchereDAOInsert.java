package fr.eni.ecole.trocenchere.dal;

import fr.eni.ecole.trocenchere.TrocEnchereExcepetion;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface TrocEnchereDAOInsert {
	
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereExcepetion;
	public void insertArticle(Article article) throws TrocEnchereExcepetion;
	public void insertEnchere(Enchere enchere) throws TrocEnchereExcepetion;
	public void insertCategorie(Categorie categorie) throws TrocEnchereExcepetion;
	public void insertRetrait(Retrait retrait) throws TrocEnchereExcepetion;
	
}
