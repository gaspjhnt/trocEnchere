package fr.eni.ecole.trocenchere.bll.detailsArticle;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.dal.detailsArticle.DAODetailsArticleFact;
import fr.eni.ecole.trocenchere.dal.detailsArticle.TrocEnchereDAODetailsArticle;

public class DetailsArticleManagerImpl implements DetailsArticleManager{
	
	TrocEnchereDAODetailsArticle dao = DAODetailsArticleFact.getVendreArticleDAO();

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
