package fr.eni.ecole.trocenchere.dal;

public class DAOEncheresFact {
		public static DAOEncheres getDAOEnchere() {
			return new DAOEncheresImpl();
		}
	}

