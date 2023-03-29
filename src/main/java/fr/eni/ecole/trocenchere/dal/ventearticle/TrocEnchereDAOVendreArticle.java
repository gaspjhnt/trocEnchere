package fr.eni.ecole.trocenchere.dal.ventearticle;

import java.util.List;

import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;

public interface TrocEnchereDAOVendreArticle {

	public void insertCategorie(Categorie categorie);
	
	public void insertArticle(Article article);
	
	public List<Categorie> selectAllCategorie();
	
	public Categorie selectCategorieById(int id);
	
	
}
