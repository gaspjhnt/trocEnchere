package fr.eni.ecole.trocenchere.bll.Utilisateur;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateur;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateurFactory;

public class UtilisateurManagerImpl implements UtilisateurManager{

	private DAOUtilisateur dao = DAOUtilisateurFactory.getDAOUtilisateur();
	
	@Override
	public Utilisateur selectById(int id) throws TrocEnchereException {
		// TODO Auto-generated method stub
		return dao.selectById(id);
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws TrocEnchereException {
			dao.updateUtilisateur(utilisateur);
	}

	@Override
	public void deleteUtilisateur(int noUtilisateur) throws TrocEnchereException {
		dao.deleteUtilisateur(noUtilisateur);
	}
}
