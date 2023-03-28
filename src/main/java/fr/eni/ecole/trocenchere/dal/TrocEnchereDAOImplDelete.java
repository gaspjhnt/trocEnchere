package fr.eni.ecole.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class TrocEnchereDAOImplDelete implements TrocEnchereDAODelete {

	public static final String DELETE_ARTICLE = "DELETE FROM Article where noArticle=?";
	public static final String DELETE_UTILISATEUR = "DELETE FROM Utilisateur where noUtilisateur=?";
	public static final String DELETE_CATEGORIE = "DELETE FROM Cattegorie where noCategorie=?";
	public static final String DELETE_ENCHERE = "DELETE FROM Enchere where noEnchere=?";
	public static final String DELETE_RETRAIT = "DELETE FROM Retrait where noRetrait=?";

	@Override
	public void deleteArticle(Article article) throws TrocEnchereException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, article.getNoArticle());
			pstmt.executeUpdate();
		} catch (Exception e) {

		}
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) throws TrocEnchereException  {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
		} catch (Exception e) {

		}

	}

	@Override
	public void deleteCategorie(Categorie categorie)  throws TrocEnchereException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORIE);
			pstmt.setInt(1, categorie.getNoCategorie());
			pstmt.executeUpdate();
		} catch (Exception e) {

		}

	}

	@Override
	public void deleteEnchere(Enchere enchere) throws TrocEnchereException  {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ENCHERE);
			pstmt.setInt(1, enchere.getNoEnchere());
			pstmt.executeUpdate();
		} catch (Exception e) {

		}
	}

	@Override
	public void deleteRetrait(Retrait retrait) throws TrocEnchereException  {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);
			pstmt.setInt(1, retrait.getNoRetrait());
			pstmt.executeUpdate();
		} catch (Exception e) {

		}

	}
}
