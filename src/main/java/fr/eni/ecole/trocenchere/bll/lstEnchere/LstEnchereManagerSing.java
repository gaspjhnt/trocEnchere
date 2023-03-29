package fr.eni.ecole.trocenchere.bll.lstEnchere;

public class LstEnchereManagerSing {
	public static lstEnchereManager instance;

	public static lstEnchereManager getInstance() {
		if (instance == null) {
			instance = new lstEnchereManagerImpl();
		}
		return instance;
	}
}
