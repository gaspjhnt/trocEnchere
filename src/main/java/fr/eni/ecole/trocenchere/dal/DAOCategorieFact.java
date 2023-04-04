package fr.eni.ecole.trocenchere.dal;


public class DAOCategorieFact {
	public static DAOCategorie getDAOCategorie() {
		return new DAOCategorieImpl();
	}
}