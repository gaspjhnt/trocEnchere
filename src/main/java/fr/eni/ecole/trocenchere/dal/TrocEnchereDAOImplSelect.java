package fr.eni.ecole.trocenchere.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class TrocEnchereDAOImplSelect implements TrocEnchereDAOSelect{

	@Override
	public Utilisateur SelectUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Article> selectArticleByUser(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectArticleByCate(Categorie categorie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article selectArticleByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectEnchereByArticle(Article article) {
		// TODO Auto-generated method stub
		return null;
	}

}
