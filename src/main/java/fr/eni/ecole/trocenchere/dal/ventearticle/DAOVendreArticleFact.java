package fr.eni.ecole.trocenchere.dal.ventearticle;

public class DAOVendreArticleFact {
		
		public static TrocEnchereDAOVendreArticle getVendreArticleDAO() {
			return new TrocEnchereDAOVendreArticleImpl();
		
	}

}
