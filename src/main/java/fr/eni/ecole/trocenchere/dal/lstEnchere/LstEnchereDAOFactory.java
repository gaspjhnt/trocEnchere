package fr.eni.ecole.trocenchere.dal.lstEnchere;

public class LstEnchereDAOFactory {
	public static LstEnchereDAO getlstEnchereDAO() {
		return new LstEnchereDAOImpl();
	}
}
