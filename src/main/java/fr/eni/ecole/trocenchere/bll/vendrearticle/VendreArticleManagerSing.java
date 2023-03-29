package fr.eni.ecole.trocenchere.bll.vendrearticle;

public class VendreArticleManagerSing {
		public static VendreArticleManager instance;

		public static VendreArticleManager getInstance() {
			if (instance == null) {
				instance = new VendreArticleManagerImpl();
			}
			return instance;
		}
	}


