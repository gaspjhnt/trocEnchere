package fr.eni.ecole.trocenchere.bo;

import java.util.List;

public class Utilisateur {
	private Integer noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostale;
	private String Ville;
	private String motDePasse;
	private Integer credit;
	private Boolean administrateur; 
	private List<Enchere> lstEnchere;
	private List<Article> lstArticle;

	/**
	 * Constructeur vide pour initialiser un utilisateur
	 */
	public Utilisateur() {
		this.credit = 200;
	}

	/**
	 * Constructeur pour initialiser un utilisateur sans listes sans crédit sans
	 * admin et sans id INFO : obligatoire a la création d'un utilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostale
	 * @param ville
	 * @param motDePasse
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostale, String ville, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostale = codePostale;
		Ville = ville;
		this.motDePasse = motDePasse;
		this.credit = 200;
	}

	/**
	 * Constructeur pour initialiser un utilisateur sans sans ID sans crédit INFO :
	 * Obligatoire pour créer un admin
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostale
	 * @param ville
	 * @param motDePasse
	 * @param administrateur
	 * @param lstEnchere
	 * @param lstArticle
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostale, String ville, String motDePasse, Boolean administrateur, List<Enchere> lstEnchere,
			List<Article> lstArticle) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostale = codePostale;
		Ville = ville;
		this.motDePasse = motDePasse;
		this.credit = 200;
		this.administrateur = administrateur;
		this.lstEnchere = lstEnchere;
		this.lstArticle = lstArticle;
	}

	/**
	 * Constructeur entier
	 * @param noUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostale
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param administrateur
	 * @param lstEnchere
	 * @param lstArticle
	 */
	public Utilisateur(Integer noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostale, String ville, String motDePasse, Integer credit, Boolean administrateur,
			List<Enchere> lstEnchere, List<Article> lstArticle) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostale = codePostale;
		Ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.lstEnchere = lstEnchere;
		this.lstArticle = lstArticle;
	}

	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	public String getVille() {
		return Ville;
	}

	public void setVille(String ville) {
		Ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Boolean administrateur) {
		this.administrateur = administrateur;
	}

	public List<Enchere> getLstEnchere() {
		return lstEnchere;
	}

	public void setLstEnchere(List<Enchere> lstEnchere) {
		this.lstEnchere = lstEnchere;
	}

	public List<Article> getLstArticle() {
		return lstArticle;
	}

	public void setLstArticle(List<Article> lstArticle) {
		this.lstArticle = lstArticle;
	}

	@Override

	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostale="
				+ codePostale + ", Ville=" + Ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + ", lstEnchere=" + lstEnchere + ", lstArticle=" + lstArticle
				+ "]";
	}

}
