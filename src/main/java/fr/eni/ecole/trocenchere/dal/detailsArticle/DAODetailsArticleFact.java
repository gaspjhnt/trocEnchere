package fr.eni.ecole.trocenchere.dal.detailsArticle;

public class DAODetailsArticleFact {
		
		public static TrocEnchereDAODetailsArticle getVendreArticleDAO() {
			return new TrocEnchereDAODetailsArticleImpl();
		
	}

}
