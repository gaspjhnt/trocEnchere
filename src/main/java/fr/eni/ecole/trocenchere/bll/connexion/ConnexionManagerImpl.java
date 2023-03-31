package fr.eni.ecole.trocenchere.bll.connexion;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.connexion.ConnexionDAO;
import fr.eni.ecole.trocenchere.dal.connexion.ConnexionDAOFactory;

public class ConnexionManagerImpl implements ConnexionManager {
	
	private ConnexionDAO connexionDAO = ConnexionDAOFactory.getConnexionDAO();

	@Override
	public Utilisateur login(String pseudoOuEmail, String mdp) throws TrocEnchereException{
		TrocEnchereException tee = new TrocEnchereException();
		
		Utilisateur user;
		try {
			user = connexionDAO.selectMdpAndPseudo(pseudoOuEmail, mdp);
			if (user.getNom() == null) {
				tee.ajouterErreur("Pseudo ou mot de passe incorrect");
				throw tee;
			}
			return user;
		} catch (TrocEnchereException e) {
			tee.ajouterErreur("Pseudo ou mot de passe incorrect");
			throw tee;
		}
	}

	

	
	
}
