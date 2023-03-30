package fr.eni.ecole.trocenchere.dal.detailsArticle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

class TrocEnchereDAODetailsArticleImpl implements TrocEnchereDAODetailsArticle{
	
	
	private static final String SELECT_ARTICLE_BY_ID = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie "
			+ "FROM article "
			+ "WHERE noArticle = ?";
	
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT noCategorie, libelle FROM categorie "
			+ "WHERE noCategorie = ?";
	
	private static final String SELECT_USER_BY_ID = "SELECT noUtilisateur, pseudo, credit FROM utilisateur "
			+ "where noUtilisateur = ?";
	
	private static final String SELECT_RETRAIT_BY_ARTICLE = "SELECT noRetrait, rue, code_postal, "
			+ "ville, Article_noArticle "
			+ "FROM retrait WHERE Article_noArticle = ?";	
	
	private static final String SELECT_ENCHERE_BY_ARTICLE = "SELECT noEnchere, date_enchere, montant_enchere,"
			+ " Utilisateur_noUtilisateur, Article_noArticle"
			+ " FROM enchere "
			+ "WHERE Article_noArticle = ?";
	
	public static final String INSERT_RETRAIT ="INSERT INTO retrait(rue,code_postal,ville,Article_NoArticle) VALUES(?,?,?,?)";

	
	
	
	@Override
	public Article selectArticleById(int id) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		Article art = null;
		try(Connection con = ConnectionProvider.getConnection()){
		
		PreparedStatement stmtArt = con.prepareStatement(SELECT_ARTICLE_BY_ID);
		PreparedStatement stmtCate = con.prepareStatement(SELECT_CATEGORIE_BY_ID );
		PreparedStatement stmtUser = con.prepareStatement(SELECT_USER_BY_ID );
		
		
		stmtArt.setInt(1, id);
		ResultSet rsArt = stmtArt.executeQuery();
		art = new Article();
		if (rsArt.next()) {
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
			
			stmtUser.setInt(1, rsArt.getInt("Utilisateur_noUtilisateur"));
			ResultSet rsUser = stmtUser.executeQuery();
			Utilisateur user = new Utilisateur();
			if (rsUser.next()) {
				user.setNoUtilisateur(rsUser.getInt("noUtilisateur"));
				user.setPseudo(rsUser.getString("pseudo"));
				user.setCredit(rsUser.getInt("credit"));
			}
			
			art.setUtilisateur(user);
		}
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectArticleById)");
			throw tee;
		}
		return art;
	}

	@Override
	public Retrait selectRetraitByArticle(Article article) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		Retrait retrait = new Retrait();
		try(Connection con = ConnectionProvider.getConnection()){
		
		PreparedStatement stmt = con.prepareStatement(SELECT_RETRAIT_BY_ARTICLE);
		stmt.setInt(1, article.getNoArticle());
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			retrait.setNoRetrait(rs.getInt("noRetrait"));
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			retrait.setArticle(article);

		}
		
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectRetraitByArticle)");
			throw tee;
		}
		return retrait;
	}
	
	
	
	@Override
	public List<Enchere> selectEnchereByArticle(Article article) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		List<Enchere> lstEnchere = new  ArrayList<>();
		try(Connection con = ConnectionProvider.getConnection()){
		
		PreparedStatement stmt = con.prepareStatement(SELECT_ENCHERE_BY_ARTICLE);
		

		PreparedStatement stmtUser = con.prepareStatement(SELECT_USER_BY_ID);
		stmt.setInt(1, article.getNoArticle());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Enchere enchere = new Enchere();
			enchere.setNoEnchere(rs.getInt("noEnchere"));
			enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
			enchere.setMontantEnchere(rs.getInt("montant_enchere"));
			enchere.setArticle(article);
			

			stmtUser.setInt(1, rs.getInt("Utilisateur_noUtilisateur"));
			ResultSet rsUser = stmtUser.executeQuery();
			Utilisateur user = new Utilisateur();
			if (rsUser.next()) {
				user.setNoUtilisateur(rsUser.getInt("noUtilisateur"));
				user.setPseudo(rsUser.getString("pseudo"));
				user.setCredit(rsUser.getInt("credit"));
			}
			enchere.setUtilisateur(user);
			lstEnchere.add(enchere);
		}
		
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectEnchereByArticle)");
			throw tee;
		}
		return lstEnchere;
	}


	@Override
	public void insertRetrait(Retrait retrait) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
			
			try(Connection cnx = ConnectionProvider.getConnection()){
				
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, retrait.getRue());
				pstmt.setString(2, retrait.getCodePostal());
				pstmt.setString(3, retrait.getVille());
				pstmt.setInt(4, retrait.getArticle().getNoArticle());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					retrait.setNoRetrait(rs.getInt(1));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				tee.ajouterErreur("Problème à l'insertion des données (insertRetrait)");
				throw tee;
			}
			
		}

}
