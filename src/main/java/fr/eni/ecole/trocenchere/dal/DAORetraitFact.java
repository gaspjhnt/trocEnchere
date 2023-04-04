package fr.eni.ecole.trocenchere.dal;


public class DAORetraitFact {
	public static DAORetrait getDAORetrait() {
		return new DAORetraitImpl();
	}
}