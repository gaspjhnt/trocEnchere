package fr.eni.ecole.trocenchere.bll.detailsArticle;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.DAOEncheres;
import fr.eni.ecole.trocenchere.dal.DAOEncheresFact;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateur;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateurFactory;
import fr.eni.ecole.trocenchere.dal.detailsArticle.DAODetailsArticleFact;
import fr.eni.ecole.trocenchere.dal.detailsArticle.TrocEnchereDAODetailsArticle;

public class DetailsArticleManagerImpl implements DetailsArticleManager{
	
	TrocEnchereDAODetailsArticle dao = DAODetailsArticleFact.getVendreArticleDAO();
	DAOEncheres daoEnchere = DAOEncheresFact.getDAOEnchere();
	DAOUtilisateur daoUser = DAOUtilisateurFactory.getDAOUtilisateur();

	@Override
	public Article selectArticleById(int id) throws TrocEnchereException {
		try {
			return dao.selectArticleById(id);
		} catch (TrocEnchereException e) {
			e.ajouterErreur("Article introuvable");
			throw e;
		}
	}

	@Override
	public Retrait selectRetraitByArticle(Article article) throws TrocEnchereException {
		try {
			return dao.selectRetraitByArticle(article);
		} catch (TrocEnchereException e) {
			e.ajouterErreur("Retrait introuvable");
			throw e;
		}
	}
	
	@Override
	public List<Enchere> selectEnchereByArticle(Article article) throws TrocEnchereException {
		try {
			return dao.selectEnchereByArticle(article);
		} catch (TrocEnchereException e) {
			return null;
		}
	}

	@Override
	public void insertRetrait(Retrait retrait) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		
		verifRetrait(retrait, tee);
		
		
		if (!tee.hasErreurs()) {
			dao.insertRetrait(retrait);
		} else {
			throw tee;
		}
	}
	
	@Override
	public void insertEnchere(Enchere enchere) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		
		verifInsertEnchere(enchere, tee);
		
		if (!tee.hasErreurs()) {
			Utilisateur user = enchere.getUtilisateur();
			List<Enchere> lstEnchere = dao.selectEnchereByArticle(enchere.getArticle());
			System.out.println(lstEnchere);
			if (lstEnchere.size() >= 1) {
				Utilisateur dernierEnchere= lstEnchere.get(lstEnchere.size() - 1).getUtilisateur();
				dernierEnchere = daoUser.selectById(dernierEnchere.getNoUtilisateur());
				System.out.println("AVANT : " + dernierEnchere.getCredit());
				dernierEnchere.setCredit(dernierEnchere.getCredit() + lstEnchere.get(lstEnchere.size() - 1).getMontant_enchere());
				System.out.println(" APRES : " + dernierEnchere.getCredit());
				daoUser.updateUtilisateur(dernierEnchere);
			}

			user.setCredit(user.getCredit() - enchere.getMontant_enchere());
			daoUser.updateUtilisateur(user);
			daoEnchere.insertEnchere(enchere);
		} else {
			throw tee;
		}

	}


	@Override
	public void updateRetrait(Retrait retrait) throws TrocEnchereException {
		dao.updateRetrait(retrait);
		
	}

	@Override
	public void updateArticle(Article article) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		
		if (dao.selectArticleById(article.getNoArticle()) == null) {
			tee.ajouterErreur("Article inconnu");
		}
		if (!tee.hasErreurs()) {
			dao.updateArticle(article);
		} else {
			throw tee;
		}
	}


	@Override
	public void deleteArticle(int idArticle) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		
		if (dao.selectArticleById(idArticle) == null) {
			tee.ajouterErreur("Article inconnu");
		}
		if (!tee.hasErreurs()) {
			List<Enchere> lstEnchere = dao.selectEnchereByArticle(dao.selectArticleById(idArticle));
			if (lstEnchere.size() >= 1) {
				for (Enchere element : lstEnchere) {
					Utilisateur user = daoUser.selectById(element.getUtilisateur().getNoUtilisateur());
					user.setCredit(user.getCredit() + element.getMontant_enchere());
					dao.deleteEnchere(element.getNoEnchere());
					daoUser.updateUtilisateur(user);
				}
			}

			dao.deleteArticle(idArticle);
		} else {
			throw tee;
		}
	}

	@Override
	public void deleteRetrait(int idRetrait) throws TrocEnchereException {
		dao.deleteRetrait(idRetrait);
		
	}
	
	
	// VERIFICATIONS 
		

	/**
	 * Permet de réaliser les verifications nécessaire pour inserer un retrait en BDD.
	 * @param retrait
	 * @param tee
	 */
	private void verifRetrait(Retrait retrait, TrocEnchereException tee) {
		if (retrait.getRue() == null) {
			tee.ajouterErreur("Adresse non valide.");
		}
		
		if (retrait.getCodePostal() == null) {
			tee.ajouterErreur("Adresse non valide.");
		}
		
		if (retrait.getCodePostal().length()>5) {
			tee.ajouterErreur("Adresse non valide.");
		}
		
		if (!isDigit(retrait.getCodePostal())) {
			tee.ajouterErreur("Adresse non valide.");
		}
		
		if (retrait.getVille() == null) {
			tee.ajouterErreur("Adresse non valide.");
		}
	}

	
	
	/**
	 * Verif. si l'enchère peux être posée (si elle est bien plus haute que les précédentes ou du prix de base)
	 * @param enchere
	 * @param tee
	 */
	private void verifInsertEnchere(Enchere enchere, TrocEnchereException tee) {
		if (enchere.getArticle().getLstEnchere() != null) {		
			for (Enchere current : enchere.getArticle().getLstEnchere()) {
				if (current.getMontant_enchere() > enchere.getMontant_enchere()) {
					tee.ajouterErreur("Enchère trop basse veuillez encherir au dessus de " + current.getMontant_enchere());
				}
			}
		} else {
			if (enchere.getMontant_enchere() < enchere.getArticle().getPrixDepart()) {
				tee.ajouterErreur("Enchère trop basse veuillez encherir au dessus de " + enchere.getArticle().getPrixDepart());
			}
		}
		
		if (enchere.getUtilisateur().getCredit() < enchere.getMontant_enchere()) {
			tee.ajouterErreur("Vous n'avez pas les fonds nécessaires");
		}
		
		if (enchere.getUtilisateur().getCredit() < enchere.getArticle().getPrixDepart()) {
			tee.ajouterErreur("Vous n'avez pas les fonds nécessaires");
		}
		
	}
	
	
	//METHODES EXTERNES
	
	
	/**
	 * Permet de return true si un str est 100% de digit si non false
	 * @param str
	 * @return
	 */
	private boolean isDigit(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}


	
}
