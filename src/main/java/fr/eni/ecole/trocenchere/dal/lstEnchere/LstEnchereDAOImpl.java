package fr.eni.ecole.trocenchere.dal.lstEnchere;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.dal.DAOArticle;
import fr.eni.ecole.trocenchere.dal.DAOArticleImpl;

public class LstEnchereDAOImpl implements LstEnchereDAO{
	// renvoie tous les articles avec une date de début d'enchère < a la date DATE (donc les articles avec une début
		// d'enchères avant la date donnée
	

	
// renvoie tous les articles avec une date de fin d'enchère > a la date DATE (donc les articles avec une fin
	// d'enchères après la date donnée
	@Override
	public List<Article> selectArticleByDateFin(LocalDate date) throws TrocEnchereException {
		DAOArticle dao = new DAOArticleImpl();
		return dao.selectArticleByDateFin(date);
	}
}
