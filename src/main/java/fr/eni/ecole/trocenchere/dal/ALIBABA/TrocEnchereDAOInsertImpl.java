package fr.eni.ecole.trocenchere.dal.ALIBABA;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class TrocEnchereDAOInsertImpl implements TrocEnchereDAOInsert {
	
	public static final String INSERT_UTILISATEUR = "INSERT INTO utilisateur(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_ARTICLE ="INSERT INTO article(nom,description,date_debut_enchere,date_fin_enchere,prix_depart,etat_vente,Utilisateur_noUtilisateur,Categorie_noCategorie) VALUES(?,?,?,?,?,?,?,?)";
	public static final String INSERT_ENCHERE ="INSERT INTO enchere(date_enchere,montant_enchere,Utilisateur_noUtilisateur,Article_noArticle) VALUES(?,?,?,?)";
	public static final String INSERT_CATEGORIE ="INSERT INTO categorie(libelle) VALUES(?)";
	public static final String INSERT_RETRAIT ="INSERT INTO retrait(rue,code_postal,ville,Article_NoArticle) VALUES(?,?,?,?)";
	
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereException {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
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
			pstmt.setBoolean(6, article.isEtatVente());
			pstmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(8, article.getCategorie().getNoCategorie());
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
	public void insertEnchere(Enchere enchere) throws TrocEnchereException {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(2, enchere.getMontant_enchere());
			pstmt.setInt(3, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(4, enchere.getArticle().getNoArticle());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				enchere.setNoEnchere(rs.getInt(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
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
	public void insertRetrait(Retrait retrait) throws TrocEnchereException {
		
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
		}
		
	}
}