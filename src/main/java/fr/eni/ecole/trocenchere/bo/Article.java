package fr.eni.ecole.trocenchere.bo;

import java.time.LocalDate;
import java.util.List;

public class Article {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEnchere;
	private LocalDate dateFinEnchere;
	private int prixDepart;
	private int prixVente; // (Prix qui augmente a chaque enchères)
	private boolean etatVente; // (Vendu ou non)
	private Utilisateur utilisateur;
	private List<Enchere> lstEnchere;
	private Categorie categorie;
	private Retrait retrait; // (peux être null pas obliger de le définir dans le constructeur)
	
	

	
	/**
	 * Constructeur vide pour initialiser un article vide
	 */
	public Article() {
	}

	
	/**
	 * Constructeur pour initialiser un article sans adresse de retrait
	 * INFO : Obligatoire pour créer un article SANS RETRAIT
	 */
	public Article(String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere,
			int prixDepart, Categorie categorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixDepart = prixDepart;
		this.categorie = categorie;
	}

	/**
	 * Constructeur pour initialiser un article avec adresse de retrait
	 * INFO : Obligatoire pour créer un article avec RETRAIT
	 */
	public Article(String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere,
			int prixDepart, Categorie categorie, Retrait retrait) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixDepart = prixDepart;
		this.categorie = categorie;
		this.retrait = retrait;
	}


	/**
	 * Constructeur complet
	 */
	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEnchere, int prixDepart, int prixVente, boolean etatVente, Utilisateur utilisateur,
			List<Enchere> lstEnchere, Categorie categorie, Retrait retrait) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixDepart = prixDepart;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.lstEnchere = lstEnchere;
		this.categorie = categorie;
		this.retrait = retrait;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(LocalDate dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public LocalDate getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(LocalDate dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public int getPrixDepart() {
		return prixDepart;
	}

	public void setPrixDepart(int prixDepart) {
		this.prixDepart = prixDepart;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public boolean isEtatVente() {
		return etatVente;
	}

	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Enchere> getLstEnchere() {
		return lstEnchere;
	}

	public void setLstEnchere(List<Enchere> lstEnchere) {
		this.lstEnchere = lstEnchere;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	@Override
	public String toString() {
		return "Article [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEnchere=" + dateDebutEnchere + ", dateFinEnchere=" + dateFinEnchere + ", prixDepart="
				+ prixDepart + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + utilisateur
				+ ", lstEnchere=" + lstEnchere + ", categorie=" + categorie + ", retrait=" + retrait + "]";
	}

}
