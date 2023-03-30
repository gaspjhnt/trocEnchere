package fr.eni.ecole.trocenchere.dal.connexion;

public abstract class ConnexionDAOFactory {
	
	public static ConnexionDAO getConnexionDAO() {
		
		return new ConnexionDAOImpl();
	}
}
