package fr.eni.ecole.trocenchere.dal;

public class DAOFactory {
	
	public static TrocEnchereDAO getTrocEnchereDAO() {
		
		return new TrocEnchereDAOImpl();
	}
}
