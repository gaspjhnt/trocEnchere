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
	 private static final String UPDATE_ARTICLE = "UPDATE Article SET nom=?, description=?, date_debut_enchere=?, date_fin_enchere=?, prix_depart=?,  prix_vente=?, etat_vente=? WHERE noArticle=?";
	 private static final String UPDATE_RETRAIT = "UPDATE Retrait SET rue=?, code_postal=?, ville=? WHERE noRetrait=?";
	 private static final String UPDATE_CATEGORIE = "UPDATE Categorie SET libelle=? WHERE noCategorie=?";
	 private static final String UPDATE_ENCHERE = "UPDATE Enchere SET date_enchere=?, montant_enchere=? WHERE noEnchere=?";
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
	
	@Override
	public void updateRetrait(Retrait retrait) throws TrocEnchereException {
		 try (Connection cnx = ConnectionProvider.getConnection()){
			 
	            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_RETRAIT);
	 
	            pstmt.setString(1, retrait.getRue());
	            pstmt.setString(2, retrait.getCodePostal());
	            pstmt.setString(3, retrait.getVille());
	            pstmt.setInt(4, retrait.getNoRetrait());
	             
	            pstmt.executeUpdate();
	            
		 } catch (Exception e) {

			}		
	}

	@Override
	public void updateCategorie(Categorie categorie) throws TrocEnchereException {
		 try (Connection cnx = ConnectionProvider.getConnection()){
			 
	            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CATEGORIE);
	 
	            pstmt.setString(1, categorie.getLibelle());
	            pstmt.setInt(2, categorie.getNoCategorie());
	            pstmt.executeUpdate();
	            
		 } catch (Exception e) {

			}				
	}

	@Override
	public void updateEnchere(Enchere enchere) throws TrocEnchereException {
		 try (Connection cnx = ConnectionProvider.getConnection()){
			 
	            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
	 
	            pstmt.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));
	            pstmt.setInt(2, enchere.getMontant_enchere());
	            pstmt.setInt(3, enchere.getNoEnchere());
	            pstmt.executeUpdate();
	            
		 } catch (Exception e) {

			}			
	}

}
