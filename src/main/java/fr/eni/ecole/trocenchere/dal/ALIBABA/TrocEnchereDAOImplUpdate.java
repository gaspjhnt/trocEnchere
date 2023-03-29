package fr.eni.ecole.trocenchere.dal.ALIBABA;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class TrocEnchereDAOImplUpdate implements TrocEnchereDAOUpdate {
	 private static final String UPDATE_UTILISATEUR = "UPDATE Utilisateur SET pseudo=?, nom=?, prenom=?, email=?, telephone=?,  rue=?, code_postal=?, ville=? , mot_de_passe=?, credit=?, administrateur=? WHERE noUtilisateur=?";
	 private static final String UPDATE_ARTICLE = "UPDATE Article SET pseudo=?, nom=?, prenom=?, email=?, telephone=?,  rue=?, code_postal=?, ville=? , mot_de_passe=?, credit=?, administrateur=? WHERE noUtilisateur=?";
	 private static final String UPDATE_RETRAIT = "UPDATE Retrait SET pseudo=?, nom=?, prenom=?, email=?, telephone=?,  rue=?, code_postal=?, ville=? , mot_de_passe=?, credit=?, administrateur=? WHERE noUtilisateur=?";
	 private static final String UPDATE_CATEGORIE = "UPDATE Categorie SET pseudo=?, nom=?, prenom=?, email=?, telephone=?,  rue=?, code_postal=?, ville=? , mot_de_passe=?, credit=?, administrateur=? WHERE noUtilisateur=?";
	 private static final String UPDATE_ENCHERE = "UPDATE Enchere SET pseudo=?, nom=?, prenom=?, email=?, telephone=?,  rue=?, code_postal=?, ville=? , mot_de_passe=?, credit=?, administrateur=? WHERE noUtilisateur=?";
	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws TrocEnchereException {
		 try (Connection cnx = ConnectionProvider.getConnection()){
			 
	            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
	 
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
	            pstmt.setInt(12, utilisateur.getNoUtilisateur());
	             
	            pstmt.executeUpdate();
	            
		 } catch (Exception e) {

			}
	}

	@Override
	public void updateArticle(Article article) throws TrocEnchereException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRetrait(Retrait retrait) throws TrocEnchereException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCategorie(Categorie categorie) throws TrocEnchereException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnchere(Enchere enchere) throws TrocEnchereException {
		// TODO Auto-generated method stub
		
	}

}
