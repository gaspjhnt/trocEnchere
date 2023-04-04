package fr.eni.ecole.trocenchere.dal;


public class DAOArticleFact {
	public static DAOArticle getDAOArticle() {
		return new DAOArticleImpl();
	}
}