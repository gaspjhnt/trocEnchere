package fr.eni.ecole.trocenchere.bll.lstEnchere;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;

public interface lstEnchereManager {
	
	List<Article> getAllArticlesByDate(LocalDate date) throws TrocEnchereException;

}
