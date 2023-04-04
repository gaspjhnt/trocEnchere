package fr.eni.ecole.trocenchere.dal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import fr.eni.ecole.trocenchere.bo.Utilisateur;



public class DAOUtilisateurImpl implements DAOUtilisateur {

	private static final String SELECT_USER_BY_ID = "SELECT noUtilisateur, pseudo, nom, prenom, email, telephone, rue, "
			+ "code_postal, ville, mot_de_passe, credit, " + "administrateur FROM utilisateur "
			+ "where noUtilisateur = ?";
	private static final String UPDATE_UTILISATEUR = "UPDATE Utilisateur SET pseudo=?, "
			+ "nom=?, prenom=?, email=?, telephone=?,  rue=?, code_postal=?, ville=? , mot_de_passe=?, credit=? WHERE noUtilisateur=?";
	private static final String DELETE_UTILISATEUR = "DELETE FROM Utilisateur where noUtilisateur=?";
	
	private static final String SELECT_ARTICLE_BY_USER = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie "
			+ "FROM article "
			+ "WHERE Utilisateur_noUtilisateur = ?";
	
	private static final String SELECT_ENCHERE_BY_USER = "SELECT noEnchere, date_enchere, montant_enchere,"
			+ " Utilisateur_noUtilisateur, Article_noArticle"
			+ " FROM enchere "
			+ "WHERE Utilisateur_noUtilisateur = ?";
	
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT noCategorie, libelle FROM categorie "
			+ "WHERE noCategorie = ?";

	@Override
	public Utilisateur selectById(int id) throws TrocEnchereException {
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
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
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
	public void updateUtilisateur(Utilisateur utilisateur) throws TrocEnchereException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

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
			pstmt.setInt(11, utilisateur.getNoUtilisateur());

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
//	private static String sha256(String password) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
