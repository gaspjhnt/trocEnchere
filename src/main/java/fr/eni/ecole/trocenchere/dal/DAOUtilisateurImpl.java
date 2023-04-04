package fr.eni.ecole.trocenchere.dal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class DAOUtilisateurImpl implements DAOUtilisateur {

	private static final String SELECT_USER_BY_ID = "SELECT noUtilisateur, pseudo, nom, prenom, email, telephone, rue, "
			+ "code_postal, ville, mot_de_passe, credit, " + "administrateur FROM utilisateur "
			+ "where noUtilisateur = ?";
	private static final String UPDATE_UTILISATEUR = "UPDATE Utilisateur SET pseudo=?, "
			+ "nom=?, prenom=?, email=?, telephone=?,  rue=?, code_postal=?, ville=? , mot_de_passe=?, credit=? WHERE noUtilisateur=?";
	private static final String DELETE_UTILISATEUR = "DELETE FROM Utilisateur where noUtilisateur=?";

	@Override
	public Utilisateur selectById(int id) throws TrocEnchereException {

		Utilisateur user = null;

		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT_USER_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new Utilisateur();
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
			}
		} catch (SQLException e) {
			TrocEnchereException tee = new TrocEnchereException();
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectArticleByDateFin)");
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
			pstmt.setString(9, sha256(utilisateur.getMotDePasse()));
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
	private static String sha256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
