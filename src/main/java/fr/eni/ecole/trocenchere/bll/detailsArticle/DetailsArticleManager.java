package fr.eni.ecole.trocenchere.bll.detailsArticle;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;

public interface DetailsArticleManager {
	
	
	public Article selectArticleById(int id) throws TrocEnchereException;
	
	public Retrait selectRetraitByArticle(Article article) throws TrocEnchereException;
	
	public List<Enchere> selectEnchereByArticle(Article article) throws TrocEnchereException;
	
	public void insertRetrait(Retrait retrait) throws TrocEnchereException;
}
