package fr.eni.ecole.trocenchere.bo;

import java.time.LocalDate;

/**
 * @author crobin2023
 *
 */
public class Enchere { //(Achat) 
	private Integer noEnchere;
	private LocalDate dateEnchere;
	private Integer montantEnchere;
	private Utilisateur utilisateur; // (Acheteur)
	private Article article;
	
	
	/**
	 * Constructeur Vide pour initialiser une enchere vide
	 */ 
	public Enchere() {
		super();
	}
	
	/**
	 * Constructeur Complet
	 * @param noEnchere
	 * @param dateEnchere
	 * @param montant_enchere
	 * @param utilisateur
	 * @param article
	 */
	public Enchere(Integer noEnchere, LocalDate dateEnchere, Integer montant_enchere, Utilisateur utilisateur,
			Article article) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montant_enchere;
		this.utilisateur = utilisateur;
		this.article = article;
	}
	
	
	/**
	 * Constructeur sans noEnchere
	 * @param dateEnchere
	 * @param montant_enchere
	 * @param utilisateur
	 * @param article
	 */
	public Enchere(LocalDate dateEnchere, Integer montant_enchere, Utilisateur utilisateur,
			Article article) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montant_enchere;
		this.utilisateur = utilisateur;
		this.article = article;
	}

	public Integer getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontant_enchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(Integer montant_enchere) {
		this.montantEnchere = montant_enchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", dateEnchere=" + dateEnchere + ", montant_enchere="
				+ montantEnchere + " " + article.getNomArticle();
	}
	
	

	
}
