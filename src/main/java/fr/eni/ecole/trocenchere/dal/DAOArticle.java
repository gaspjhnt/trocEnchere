package fr.eni.ecole.trocenchere.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;

public interface DAOArticle {

	public List<Article> selectArticleByDateFin(LocalDate date) throws TrocEnchereException;
}
