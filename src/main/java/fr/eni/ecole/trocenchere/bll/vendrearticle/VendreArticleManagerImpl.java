package fr.eni.ecole.trocenchere.bll.vendrearticle;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.ventearticle.DAOVendreArticleFact;
import fr.eni.ecole.trocenchere.dal.ventearticle.TrocEnchereDAOVendreArticle;

class VendreArticleManagerImpl implements VendreArticleManager{

	// recupération de la Factory DAO
	TrocEnchereDAOVendreArticle dao = DAOVendreArticleFact.getVendreArticleDAO();
	
	
	
	/**
	 * Methode permettant de faire les verifications necessaires (voir dans Interface) 
	 * avant d'inserer en base de donnée une catégorie
	 */
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

	
	/**
	 * Methode permettant de faire les verifications necessaires (voir dans Interface) 
	 * avant d'inserer en base de donnée un article
	 */
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
	

	/**
	 * Methode permettant de faire les verifications necessaires (voir dans Interface) 
	 * avant d'inserer en base de donnée un utilisateur
	 */
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereException {
		
		dao.insertUtilisateur(utilisateur);
	}

	
	/**
	 * Methode permettant de faire les verifications necessaires (voir dans Interface) 
	 * avant d'inserer en base de donnée un retrait
	 */
	@Override
	public void insertRetrait(Retrait retrait) throws TrocEnchereException{
		TrocEnchereException tee = new TrocEnchereException();
		
		verifRetrait(retrait, tee);
		
		
		if (!tee.hasErreurs()) {
			dao.insertRetrait(retrait);
		} else {
			throw tee;
		}
	}

	
	/**
	 * Methode permettant de faire les verifications necessaires (voir dans Interface) 
	 * avant de selectionner toutes les catégories dans la BDD.
	 */
	@Override
	public List<Categorie> selectAllCategorie() throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		
		if (dao.selectAllCategorie().size() < 0) {
			tee.ajouterErreur("Aucune catégorie disponible.");
		}
		
		if (!tee.hasErreurs()) {
			return dao.selectAllCategorie();
		} else {
			throw tee;
		}
		
	}

	
	/**
	 * Methode permettant de faire les verifications necessaires (voir dans Interface) 
	 * avant de selectionner une catégorie via son ID en BDD.
	 */
	@Override
	public Categorie selectCategorieById(int id) throws TrocEnchereException {
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
			return dao.selectCategorieById(id);
		} else {
			throw tee;
		}
	}
	
	

	
	
	
	// VERIFICATIONS 
		

	/**
	 * Permet de réaliser les verifications nécessaire pour inserer une catégorie en BDD.
	 * @param categorie
	 * @param tee
	 * @throws TrocEnchereException
	 */
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
	

	/**
	 * Permet de réaliser les verifications nécessaire pour inserer un article en BDD.
	 * @param article
	 * @param tee
	 */
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
