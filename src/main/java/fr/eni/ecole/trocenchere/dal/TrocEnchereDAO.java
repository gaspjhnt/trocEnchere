package fr.eni.ecole.trocenchere.dal;

import java.time.LocalDate;

import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface TrocEnchereDAO {
	
	public Utilisateur SelectUserById(int id);
	
	
	public Article selectArticleByUser(Utilisateur utilisateur);
	
	
	public Article selectArticleByCate(Categorie categorie);
	
	
	public Article selectArticleByDate(LocalDate date);
	
	
	public Enchere selectEnchereByArticle(Article article);

}
