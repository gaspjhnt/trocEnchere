package fr.eni.ecole.trocenchere.bll.detailsArticle;

public class DetailsArticleManagerSing {
	public static DetailsArticleManager instance;

	public static DetailsArticleManager getInstance() {
		if (instance == null) {
			instance = new DetailsArticleManagerImpl();
		}
		return instance;
	}


}
