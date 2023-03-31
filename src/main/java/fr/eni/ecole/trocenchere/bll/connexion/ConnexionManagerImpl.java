package fr.eni.ecole.trocenchere.bll.connexion;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.connexion.ConnexionDAO;
import fr.eni.ecole.trocenchere.dal.connexion.ConnexionDAOFactory;

public class ConnexionManagerImpl implements ConnexionManager {
	
	private ConnexionDAO connexionDAO = ConnexionDAOFactory.getConnexionDAO();

	@Override
	public Utilisateur login(String pseudoOuEmail, String mdp) throws TrocEnchereException {
		
		Utilisateur user = connexionDAO.selectMdpAndPseudo(pseudoOuEmail, mdp);
		return user;
	}

	

	
	
}
