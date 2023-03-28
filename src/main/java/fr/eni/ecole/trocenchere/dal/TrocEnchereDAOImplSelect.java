package fr.eni.ecole.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class TrocEnchereDAOImplSelect implements TrocEnchereDAOSelect{
	
	private static final String SELECT_USER_BY_ID = "SELECT noUtilisateur, pseudo, nom, prenom, email, telephone, rue, "
			+ "code_postal, ville, mot_de_passe, credit, "
			+ "administrateur FROM utilisateur "
			+ "where noUtilisateur = ?";
	
	private static final String SELECT_ARTICLE_BY_USER = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie "
			+ "FROM article "
			+ "WHERE Utilisateur_noUtilisateur = ?";
	
	private static final String SELECT_ARTICLE_BY_CATE = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie "
			+ "FROM article "
			+ "WHERE Categorie_noCategorie = ?";
	
	private static final String SELECT_ARTICLE_BY_DATE = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie "
			+ "FROM article "
			+ "WHERE Categorie_noCategorie = ?";
	
	private static final String SELECT_ENCHERE_BY_ARTICLE = "SELECT noEnchere, date_enchere, montant_enchere,"
			+ " Utilisateur_noUtilisateur, Article_noArticle"
			+ " FROM enchere "
			+ "WHERE Article_noArticle = ?";
	
	private static final String SELECT_ENCHERE_BY_USER = "SELECT noEnchere, date_enchere, montant_enchere,"
					+ " Utilisateur_noUtilisateur, Article_noArticle"
					+ " FROM enchere "
					+ "WHERE Utilisateur_noUtilisateur = ?";
	
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT noCategorie, libelle FROM categorie "
			+ "WHERE noCategorie = ?";
	

	private static final String SELECT_ALL_CATEGORIE = "SELECT noCategorie, libelle FROM categorie";
	
	
	private static final String SELECT_RETRAIT_BY_ARTICLE = "SELECT noRetrait, rue, code_postal, "
			+ "ville, Article_noArticle "
			+ "FROM retrait WHERE Article_noArticle = ?";
	
	
	
	@Override
	public Utilisateur SelectUserById(int id) throws TrocEnchereException{
		TrocEnchereException tee = new TrocEnchereException();
		Utilisateur user = new Utilisateur();
		try(Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(SELECT_USER_BY_ID);
			PreparedStatement stmtArticle = con.prepareStatement(SELECT_ARTICLE_BY_USER);
			PreparedStatement stmtEnchere = con.prepareStatement(SELECT_USER_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user.setNoUtilisateur(rs.getInt("noUtilisateur"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setPseudo(rs.getString("pseudo"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostale(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				if (rs.getInt("administrateur") == 1) {
					user.setAdministrateur(true);
				} else {
					user.setAdministrateur(false);
				}
			}
			
			
			
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (SelectArticles)");
			throw tee;
		}
		return user;
	}


	@Override
	public List<Article> selectArticleByUser(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectArticleByCate(Categorie categorie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectArticleByDateFin(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Article> selectArticleByDateDebut(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public List<Enchere> selectEnchereByArticle(Article article) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Enchere> selectEnchereByUser(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Categorie selectCategorieById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Categorie> selectAllCategorie() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Retrait selectRetraitByArticle(Article article) {
		// TODO Auto-generated method stub
		return null;
	}

}
