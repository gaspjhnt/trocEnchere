package fr.eni.ecole.trocenchere.bll.connexion;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.DAOArticle;
import fr.eni.ecole.trocenchere.dal.DAOArticleFact;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateur;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateurFactory;
import fr.eni.ecole.trocenchere.dal.connexion.ConnexionDAO;
import fr.eni.ecole.trocenchere.dal.connexion.ConnexionDAOFactory;

public class ConnexionManagerImpl implements ConnexionManager {
	
	private ConnexionDAO connexionDAO = ConnexionDAOFactory.getConnexionDAO();
	private DAOArticle daoArt = DAOArticleFact.getDAOArticle();
	private DAOUtilisateur daoUser = DAOUtilisateurFactory.getDAOUtilisateur();

	@Override
	public Utilisateur login(String pseudoOuEmail, String mdp) throws TrocEnchereException{
		
		TrocEnchereException tee = new TrocEnchereException();
		Utilisateur user;
		try {
			
			//On récupère l'utilisateur a l'aide de son pseudo ou email et MDP
			user = connexionDAO.selectMdpAndPseudo(pseudoOuEmail, mdp);
			//Si il a pas de nom, pas vrai compte donc mauvais ID
			if (user.getNom() == null) {
				tee.ajouterErreur("Identifiant ou mot de passe incorrect");
				throw tee;
			}
			return user;
			//Si on arrive pas a récuperer le user, donc innexistant dans la BDD
			//Donc on ajoute une erreur
		} catch (TrocEnchereException e) {
			tee.ajouterErreur("Identifiant ou mot de passe incorrect");
			throw tee;
		}
	}

	@Override
	public List<Article> selectArticleByUser(Utilisateur utilisateur) throws TrocEnchereException {
		return daoArt.selectArticleByUser(utilisateur);
	}

	

	
	@Override
	public List<Enchere> selectEnchereByArticle(Article article) throws TrocEnchereException {
		try {
			return connexionDAO.selectEnchereByArticle(article);
		} catch (TrocEnchereException e) {
			return null;
		}
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws TrocEnchereException {
		daoUser.updateUtilisateur(utilisateur);
	}

	@Override
	public void updateAticle(Article article) throws TrocEnchereException {
		daoArt.updateArticle(article);
		
	}
	
	
	

	
	
}
