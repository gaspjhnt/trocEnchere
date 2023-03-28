package fr.eni.ecole.trocenchere.bo;

import java.util.List;
 
public class Categorie {
	private Integer noCategorie;
	private String libelle;
	private List<Article> lstArticle;
	
	
	
	

	/**
	 *  Constructeur vide pour initialiser une catégorie vide
	 */
	public Categorie() {
		super();
	}
	
	

	/**
	 * constructeur sans ID et sans list 
	 * INFO : obligatoire pour créer une catégorie
	 * @param libelle
	 */
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}
	

	/**
	 * constructeur sans ID et sans list 
	 * INFO : obligatoire pour créer une catégorie (+ list d'article)
	 * @param libelle
	 */
	public Categorie(String libelle, List<Article> lstArticle) {
		super();
		this.libelle = libelle;
		this.lstArticle = lstArticle;
		
	}


	/**
	 * Constructeur complet
	 * @param noCategorie
	 * @param libelle
	 * @param lstArticle
	 */
	public Categorie(Integer noCategorie, String libelle, List<Article> lstArticle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.lstArticle = lstArticle;
	}

	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Article> getLstArticle() {
		return lstArticle;
	}

	public void setLstArticle(List<Article> lstArticle) {
		this.lstArticle = lstArticle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]\n";
	}

}
