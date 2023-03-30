package fr.eni.ecole.trocenchere.bll.connexion;

public class ConnexionManagerSing {
	
public static ConnexionManagerImpl instance;
	
	public static ConnexionManagerImpl getInstance() {
		if (instance == null) {
			instance = new ConnexionManagerImpl();
		}
		return instance;
	}
	
}
