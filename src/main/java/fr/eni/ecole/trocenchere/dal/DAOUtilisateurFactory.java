package fr.eni.ecole.trocenchere.dal;

public class DAOUtilisateurFactory {
		public static DAOUtilisateur getDAOUtilisateur() {
			return new DAOUtilisateurImpl();
		}
	}

