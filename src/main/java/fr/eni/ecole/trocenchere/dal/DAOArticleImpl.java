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
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class DAOArticleImpl implements DAOArticle {

	private static final String SELECT_ARTICLE_BY_DATE_FIN = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie " + "FROM article "
			+ "WHERE date_fin_enchere > ? "; // TODO and date_debut_enchere < ?
	
	
	private static final String SELECT_ARTICLE_BY_USER = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie "
			+ "FROM article "
			+ "WHERE Utilisateur_noUtilisateur = ?";
	
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT noCategorie, libelle FROM categorie "
			+ "WHERE noCategorie = ?";
	
	 private static final String UPDATE_ARTICLE = "UPDATE Article SET nom=?, description=?, date_debut_enchere=?, date_fin_enchere=?, prix_depart=?,  prix_vente=?, etat_vente=? WHERE noArticle=?";
	 

	@Override
	public List<Article> selectArticleByDateFin(LocalDate date) throws TrocEnchereException {

		DAOCategorie daoCat = new DAOCategorieImpl();
		DAOUtilisateur daoUtil = new DAOUtilisateurImpl();

		List<Article> lstArticle = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection()) {

			PreparedStatement stmtArticle = con.prepareStatement(SELECT_ARTICLE_BY_DATE_FIN);
			stmtArticle.setDate(1, java.sql.Date.valueOf(date));
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
				art.setEtatVente(rsArt.getBoolean("etat_vente"));

				art.setCategorie(daoCat.selectById(rsArt.getInt("Categorie_noCategorie")));

				art.setUtilisateur(daoUtil.selectById(rsArt.getInt("Utilisateur_noUtilisateur")));

				lstArticle.add(art);
			}
		} catch (SQLException e) {
			TrocEnchereException tee = new TrocEnchereException();
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectArticleByDateFin)");
			throw tee;
		}
		return lstArticle;
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
			art.setEtatVente(rsArt.getBoolean("etat_vente"));
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
		tee.ajouterErreur("Problème à la selection des données (selectArticleByUser)");
		throw tee;
	}
	return lstArticle;
}

	


	@Override
	public void updateArticle(Article article) throws TrocEnchereException {
		 try (Connection cnx = ConnectionProvider.getConnection()){
			 
	            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
	 
	            pstmt.setString(1, article.getNomArticle());
	            pstmt.setString(2, article.getDescription());
	            pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEnchere()));
	            pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEnchere()));
	            pstmt.setInt(5, article.getPrixDepart());
	            pstmt.setInt(6, article.getPrixVente());
	            pstmt.setBoolean(7, article.isEtatVente());
	            pstmt.setInt(8, article.getNoArticle());
	             
	            pstmt.executeUpdate();
	            
		 } catch (Exception e) {

			}
		
	}

}
