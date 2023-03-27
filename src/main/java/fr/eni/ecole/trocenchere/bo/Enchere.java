package fr.eni.ecole.trocenchere.bo;

import java.time.LocalDate;

public class Enchere {
	private Integer noEnchere;
	private LocalDate dateEnchere;
	private Integer montant_enchere;
	private Utilisateur utilisateur;
	private Article article;
	
	
	/**
	 * Constructeur Vide pour initialiser une enchere vide
	 */
	public Enchere() {
		super();
	}
	
	
	
	/**
	 * Constructeur Complet
	 */
	public Enchere(Integer noEnchere, LocalDate dateEnchere, Integer montant_enchere, Utilisateur utilisateur,
			Article article) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
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
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
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
				+ montant_enchere + ", utilisateur=" + utilisateur + ", article=" + article + "]";
	}
	
	

	
}
