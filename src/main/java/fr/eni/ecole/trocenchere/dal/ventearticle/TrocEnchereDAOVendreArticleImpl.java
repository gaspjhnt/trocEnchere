package fr.eni.ecole.trocenchere.dal.ventearticle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;

class TrocEnchereDAOVendreArticleImpl implements TrocEnchereDAOVendreArticle{
	
	public static final String INSERT_CATEGORIE ="INSERT INTO categorie(libelle) VALUES(?)";
	public static final String INSERT_ARTICLE ="INSERT INTO article(nom,description,date_debut_enchere,date_fin_enchere,prix_depart,prix_vente,etat_vente,Utilisateur_noUtilisateur,Categorie_noCategorie,image) VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SELECT_ALL_CATEGORIE = "SELECT noCategorie, libelle FROM categorie";
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT noCategorie, libelle FROM categorie "
			+ "WHERE noCategorie = ?";
	
	
	
	@Override
	public void insertCategorie(Categorie categorie) throws TrocEnchereException {
		
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_CATEGORIE, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, categorie.getLibelle());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				categorie.setNoCategorie(rs.getInt(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void insertArticle(Article article) throws TrocEnchereException {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, Date.valueOf(article.getDateDebutEnchere()));
			pstmt.setDate(4, Date.valueOf(article.getDateFinEnchere()));
			pstmt.setInt(5, article.getPrixDepart());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setBoolean(7, article.isEtatVente());
			pstmt.setInt(8, article.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(9, article.getCategorie().getNoCategorie());
//			pstmt.setBinaryStream(10, null, img.length);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	


	@Override
	public List<Categorie> selectAllCategorie() throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		List<Categorie> lstCate = new ArrayList<>();
		try(Connection con = ConnectionProvider.getConnection()){
		
		PreparedStatement stmt = con.prepareStatement(SELECT_ALL_CATEGORIE);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Categorie cate = new Categorie();
			cate.setNoCategorie(rs.getInt("noCategorie"));
			cate.setLibelle(rs.getString("libelle"));
			lstCate.add(cate);
		}
		
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectAllCategorie)");
			throw tee;
		}
		return lstCate;
	}


	@Override
	public Categorie selectCategorieById(int id) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		Categorie cate = new Categorie();
		try(Connection con = ConnectionProvider.getConnection()){
		
		PreparedStatement stmt = con.prepareStatement(SELECT_CATEGORIE_BY_ID);
		
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			cate.setNoCategorie(rs.getInt("noCategorie"));
			cate.setLibelle(rs.getString("libelle"));
		}
		
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectCategorieById)");
			throw tee;
		}
		return cate;
	}


}
