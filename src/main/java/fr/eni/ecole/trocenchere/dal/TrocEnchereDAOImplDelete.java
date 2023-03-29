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
	public static final String DELETE_CATEGORIE = "DELETE FROM Categorie where noCategorie=?";
	public static final String DELETE_ENCHERE = "DELETE FROM Enchere where noEnchere=?";
	public static final String DELETE_RETRAIT = "DELETE FROM Retrait where noRetrait=?";

	@Override
	public void deleteArticle(int idArticle) throws TrocEnchereException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, idArticle);
			pstmt.executeUpdate();
		} catch (Exception e) {

		}
	}

	@Override
	public void deleteUtilisateur(int idUtilisateur) throws TrocEnchereException  {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);
			pstmt.setInt(1, idUtilisateur);
			pstmt.executeUpdate();
		} catch (Exception e) {

		}

	}

	@Override
	public void deleteCategorie(int idCategorie)  throws TrocEnchereException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORIE);
			pstmt.setInt(1, idCategorie);
			pstmt.executeUpdate();
		} catch (Exception e) {

		}

	}

	@Override
	public void deleteEnchere(int idEnchere) throws TrocEnchereException  {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ENCHERE);
			pstmt.setInt(1, idEnchere);
			pstmt.executeUpdate();
		} catch (Exception e) {

		}
	}

	@Override
	public void deleteRetrait(int idRetrait) throws TrocEnchereException  {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);
			pstmt.setInt(1, idRetrait);
			pstmt.executeUpdate();
		} catch (Exception e) {

		}

	}
}
