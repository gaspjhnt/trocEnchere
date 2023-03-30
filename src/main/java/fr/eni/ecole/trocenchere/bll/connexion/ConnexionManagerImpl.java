package fr.eni.ecole.trocenchere.bll.connexion;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.connexion.ConnexionDAO;
import fr.eni.ecole.trocenchere.dal.connexion.ConnexionDAOFactory;

public class ConnexionManagerImpl implements ConnexionManager {
	
	private ConnexionDAO connexionDAO = ConnexionDAOFactory.getConnexionDAO();

	@Override
	public boolean selectPseudoAndMdp(String pseudo, String mdp) throws TrocEnchereException {
		
		if(!(this.connexionDAO.selectMdpAndPseudo(pseudo, mdp)==null)) {
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public boolean selectEmailAndMdp(String email, String mdp) throws TrocEnchereException {
		if(!(this.connexionDAO.selectMdpAndEmail(email, mdp)==null)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
