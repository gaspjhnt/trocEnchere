package fr.eni.ecole.trocenchere.bll.Utilisateur;

public class UtilisateurManagerSing {
	public static UtilisateurManager instance;

	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManagerImpl();
		}
		return instance;
	}
}
