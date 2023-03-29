package fr.eni.ecole.trocenchere.bll.lstEnchere;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.dal.lstEnchere.LstEnchereDAO;
import fr.eni.ecole.trocenchere.dal.lstEnchere.LstEnchereDAOFactory;

public class lstEnchereManagerImpl implements lstEnchereManager{

	private LstEnchereDAO dao = LstEnchereDAOFactory.getlstEnchereDAO();

	@Override
	public List<Article> getAllArticlesByDate(LocalDate date) throws TrocEnchereException {
		return dao.selectArticleByDateFin(date);
	}

}
