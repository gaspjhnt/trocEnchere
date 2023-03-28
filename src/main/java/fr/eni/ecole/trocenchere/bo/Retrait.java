package fr.eni.ecole.trocenchere.bo;

public class Retrait { 
	private Integer noRetrait;
	private String rue;
	private String codePostal;
	private String ville;
	private Article article;

	
	
	
	/**
	 * Constructeur vide pour initialiser un Retrait vide
	 */
	public Retrait() {
		super();
	}
	
	
	/**
	 * Constructeur sans id et article 
	 * INFO : olbigatoire pour créer un retrait
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public Retrait(String rue, String codePostal, String ville, Article article) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.article = article;
	}
	/**
	 * Constructeur complet
	 * @param noRetrait
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param article
	 */
	public Retrait(Integer noRetrait, String rue, String codePostal, String ville, Article article) {
		super();
		this.noRetrait = noRetrait;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.article = article;
	}

	public Integer getNoRetrait() {
		return noRetrait;
	}

	public void setNoRetrait(Integer noRetrait) {
		this.noRetrait = noRetrait;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Retrait [noRetrait=" + noRetrait + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]\n";
	}

	
}
