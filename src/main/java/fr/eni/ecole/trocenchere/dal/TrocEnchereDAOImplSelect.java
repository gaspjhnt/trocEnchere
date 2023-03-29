package fr.eni.ecole.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.MouseInputListener;

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
			+ "WHERE date_debut_enchere > ? AND date_fin_enchere < ?";
	
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
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			PreparedStatement stmtArticle = con.prepareStatement(SELECT_ARTICLE_BY_USER);
			stmtArticle.setInt(1, id);
			ResultSet rsArt = stmtArticle.executeQuery();
			List<Article> lstArticle = new  ArrayList<>();
			
			PreparedStatement stmtEnchere = con.prepareStatement(SELECT_ENCHERE_BY_USER);
			stmtEnchere.setInt(1, id);
			ResultSet rsEnchere = stmtEnchere.executeQuery();
			List<Enchere> lstEnchere = new  ArrayList<>();
			

			
			PreparedStatement stmtCate = con.prepareStatement(SELECT_CATEGORIE_BY_ID );
			
			
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
				while (rsArt.next()) {
					Article art = new Article();
					art.setNoArticle(rsArt.getInt("noArticle"));
					art.setNomArticle(rsArt.getString("nom"));
					art.setDescription(rsArt.getString("description"));
					art.setDateDebutEnchere(rsArt.getDate("date_debut_enchere").toLocalDate()); 
					art.setDateFinEnchere(rsArt.getDate("date_fin_enchere").toLocalDate()); 
					art.setPrixDepart(rsArt.getInt("prix_depart"));
					art.setPrixVente(rsArt.getInt("prix_vente"));
					if (rsArt.getInt("etat_vente") == 1) {
						art.setEtatVente(true);
					} else {
						art.setEtatVente(false);
					}
					stmtCate.setInt(1, rsArt.getInt("Categorie_noCategorie"));
					ResultSet rsCate = stmtCate.executeQuery();
					Categorie cate = new Categorie();
					if (rsCate.next()) {
						cate.setLibelle(rsCate.getString("libelle"));
						cate.setNoCategorie(rsCate.getInt("noCategorie"));
					}
					art.setCategorie(cate);
					
					art.setUtilisateur(user);
					lstArticle.add(art);
				}
				
				while (rsEnchere.next()) {
					Enchere enchere = new Enchere();
					enchere.setNoEnchere(rsEnchere.getInt("noEnchere"));
					enchere.setDateEnchere(rsEnchere.getDate("date_enchere").toLocalDate());
					enchere.setMontantEnchere(rsEnchere.getInt("montant_enchere"));
					enchere.setUtilisateur(user);
					lstEnchere.add(enchere);
				}
				user.setLstArticle(lstArticle);
				user.setLstEnchere(lstEnchere);
			}
			
			
			
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (SelectUserById)");
			throw tee;
		}
		return user;
	}


	@Override
	public List<Article> selectArticleByUser(Utilisateur utilisateur) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		List<Article> lstArticle = new  ArrayList<>();
		try(Connection con = ConnectionProvider.getConnection()){
		
		PreparedStatement stmtArticle = con.prepareStatement(SELECT_ARTICLE_BY_USER);
		stmtArticle.setInt(1, utilisateur.getNoUtilisateur());
		ResultSet rsArt = stmtArticle.executeQuery();
		

		PreparedStatement stmtCate = con.prepareStatement(SELECT_CATEGORIE_BY_ID );
		
		
		while (rsArt.next()) {
			Article art = new Article();
			art.setNoArticle(rsArt.getInt("noArticle"));
			art.setNomArticle(rsArt.getString("nom"));
			art.setDescription(rsArt.getString("description"));
			art.setDateDebutEnchere(rsArt.getDate("date_debut_enchere").toLocalDate()); 
			art.setDateFinEnchere(rsArt.getDate("date_fin_enchere").toLocalDate()); 
			art.setPrixDepart(rsArt.getInt("prix_depart"));
			art.setPrixVente(rsArt.getInt("prix_vente"));
			if (rsArt.getInt("etat_vente") == 1) {
				art.setEtatVente(true);
			} else {
				art.setEtatVente(false);
			}
			stmtCate.setInt(1, rsArt.getInt("Categorie_noCategorie"));
			ResultSet rsCate = stmtCate.executeQuery();
			Categorie cate = new Categorie();
			if (rsCate.next()) {
				cate.setLibelle(rsCate.getString("libelle"));
				cate.setNoCategorie(rsCate.getInt("noCategorie"));
			}
			art.setCategorie(cate);
			
			art.setUtilisateur(utilisateur);
			lstArticle.add(art);
		}
	}catch (SQLException e){
		e.printStackTrace();
		tee.ajouterErreur("Problème à la selection des données (SelectArticleByUser)");
		throw tee;
	}
	return lstArticle;
}

	@Override
	public List<Article> selectArticleByCate(Categorie categorie) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		List<Article> lstArticle = new  ArrayList<>();
		try(Connection con = ConnectionProvider.getConnection()){
		
		PreparedStatement stmtArticle = con.prepareStatement(SELECT_ARTICLE_BY_CATE);
		stmtArticle.setInt(1, categorie.getNoCategorie());
		ResultSet rsArt = stmtArticle.executeQuery();
		
		
		
		while (rsArt.next()) {
			Article art = new Article();
			art.setNoArticle(rsArt.getInt("noArticle"));
			art.setNomArticle(rsArt.getString("nom"));
			art.setDescription(rsArt.getString("description"));
			art.setDateDebutEnchere(rsArt.getDate("date_debut_enchere").toLocalDate()); 
			art.setDateFinEnchere(rsArt.getDate("date_fin_enchere").toLocalDate()); 
			art.setPrixDepart(rsArt.getInt("prix_depart"));
			art.setPrixVente(rsArt.getInt("prix_vente"));
			if (rsArt.getInt("etat_vente") == 1) {
				art.setEtatVente(true);
			} else {
				art.setEtatVente(false);
			}
			art.setCategorie(categorie);
			
			lstArticle.add(art);
		}
	}catch (SQLException e){
		e.printStackTrace();
		tee.ajouterErreur("Problème à la selection des données (SelectArticleByUser)");
		throw tee;
	}
	return lstArticle;
}

	// renvoie tous les articles avec une date de fin d'enchère > a la date DATE (donc les articles avec une fin
	// d'enchères après la date donnée
	@Override
	public List<Article> selectArticleByDateFin(LocalDate date) {
		return null;
//		TrocEnchereException tee = new TrocEnchereException();
//		List<Article> lstArticle = new  ArrayList<>();
//		try(Connection con = ConnectionProvider.getConnection()){
//		
//		PreparedStatement stmtArticle = con.prepareStatement(SELECT_ARTICLE_BY_CATE);
//		stmtArticle.setInt(1, categorie.getNoCategorie());
//		ResultSet rsArt = stmtArticle.executeQuery();
//		
//		
//		
//		while (rsArt.next()) {
//			Article art = new Article();
//			art.setNoArticle(rsArt.getInt("noArticle"));
//			art.setNomArticle(rsArt.getString("nom"));
//			art.setDescription(rsArt.getString("description"));
//			art.setDateDebutEnchere(rsArt.getDate("date_debut_enchere").toLocalDate()); 
//			art.setDateFinEnchere(rsArt.getDate("date_fin_enchere").toLocalDate()); 
//			art.setPrixDepart(rsArt.getInt("prix_depart"));
//			art.setPrixVente(rsArt.getInt("prix_vente"));
//			if (rsArt.getInt("etat_vente") == 1) {
//				art.setEtatVente(true);
//			} else {
//				art.setEtatVente(false);
//			}
//			art.setCategorie(categorie);
//			
//			lstArticle.add(art);
//		}
//	}catch (SQLException e){
//		e.printStackTrace();
//		tee.ajouterErreur("Problème à la selection des données (SelectArticleByUser)");
//		throw tee;
//	}
//	return lstArticle;
}

	
	
	// renvoie tous les articles avec une date de début d'enchère < a la date DATE (donc les articles avec une début
	// d'enchères avant la date donnée
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
