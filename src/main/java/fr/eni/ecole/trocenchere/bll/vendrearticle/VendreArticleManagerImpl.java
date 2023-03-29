package fr.eni.ecole.trocenchere.bll.vendrearticle;

import java.time.LocalDate;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.dal.ventearticle.DAOVendreArticleFact;
import fr.eni.ecole.trocenchere.dal.ventearticle.TrocEnchereDAOVendreArticle;

public class VendreArticleManagerImpl implements VendreArticleManager{

	TrocEnchereDAOVendreArticle dao = DAOVendreArticleFact.getVendreArticleDAO();
	
	@Override
	public void insertCategorie(Categorie categorie) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		verifInsertCategorie(categorie, tee);

		if (!tee.hasErreurs()) {
			dao.insertCategorie(categorie);
		} else {
			throw tee;
		}
	}

	@Override
	public void insertArticle(Article article) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		
		verifInsertArticle(article, tee);
		
		if (!tee.hasErreurs()) {
			dao.insertArticle(article);
		} else {
			throw tee;
		}
	}
	
	@Override
	public void selectAllCategorie(Categorie categorie) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		
		if (dao.selectAllCategorie().size() < 0) {
			tee.ajouterErreur("Aucune catégorie disponible.");
		}
		
		if (!tee.hasErreurs()) {
			dao.selectAllCategorie();
		} else {
			throw tee;
		}
		
	}

	@Override
	public void selectCategorieById(int id) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		boolean dispo = false;
		
		for (Categorie element : dao.selectAllCategorie()) {
			if (element.getNoCategorie() == id) {
				dispo = true;
			}
		}
		
		if (!dispo) {
			tee.ajouterErreur("ID introuvable.");
		}
		
		if (!tee.hasErreurs()) {
			dao.selectCategorieById(id);
		} else {
			throw tee;
		}
	}
	
	
	
	
	
	// VERIFICATIONS 
		


	private void verifInsertCategorie(Categorie categorie, TrocEnchereException tee) throws TrocEnchereException {
		if (categorie.getLibelle().length()> 45) {
			tee.ajouterErreur("Libelle trop grand. (45 caractères maximum)");
		}
		
		if (categorie.getLibelle().length()<1) {
			tee.ajouterErreur("Libelle trop petit.");
		}
		
		for (Categorie element : dao.selectAllCategorie()) {
			if (element.getLibelle().equals(categorie.getLibelle())) {
				tee.ajouterErreur("Catégorie déjà existante !");
			}
		}
	}
	
	
	
	


	private void verifInsertArticle(Article article, TrocEnchereException tee) {
		if (article.getNomArticle() == null) {
			tee.ajouterErreur("Nom d'article non renseigné.");
		}
		
		if (article.getNomArticle().length() > 45) {
			tee.ajouterErreur("Nom d'article trop grand. (45 caractères maximum)");
		}
		
		if (article.getNomArticle().length() < 1) {
			tee.ajouterErreur("Nom d'article trop petit.");
		}
		
		if (article.getDescription() == null) {
			tee.ajouterErreur("Description non renseignée.");
		}
		
		if (article.getDescription().length() > 255) {
			tee.ajouterErreur("Description trop grande. (255 caractères maximum)");
		}
		
		if (article.getDescription().length() < 1) {
			tee.ajouterErreur("Description trop courte.");
		}
		
		if (article.getDateDebutEnchere() == null) {
			tee.ajouterErreur("Date de début d'enchère non renseignée.");
		}
		
		if (article.getDateFinEnchere() == null) {
			tee.ajouterErreur("Date de fin d'enchère non renseignée.");
		}
		
		if (article.getDateFinEnchere().isBefore(LocalDate.now())) {
			tee.ajouterErreur("Impossible de finir une enchère dans le passé.");
		}
		
		if (article.getPrixDepart() == null){
			tee.ajouterErreur("Prix de départ non renseigné.");
		}
		
		if (article.getPrixDepart() < 0) {
			tee.ajouterErreur("Le prix ne peux pas être négatif.");
		}
		
		if (article.getCategorie() == null) {
			tee.ajouterErreur("Catégorie non renseignée.");
		}
	}


}
