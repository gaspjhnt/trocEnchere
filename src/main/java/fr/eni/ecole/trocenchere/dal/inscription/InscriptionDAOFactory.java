package fr.eni.ecole.trocenchere.dal.inscription;

public abstract class InscriptionDAOFactory {
	
	public static InscriptionDAO getInscriptionDAO() {
		
		return new InscriptionDAOImpl();
	}
}
