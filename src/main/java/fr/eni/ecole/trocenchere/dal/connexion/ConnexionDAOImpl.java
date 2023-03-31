package fr.eni.ecole.trocenchere.dal.connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class ConnexionDAOImpl implements ConnexionDAO {
	
	//private static final String SELECT_MDP_BY_PSEUDO = "SELECT * FROM utilisateur WHERE pseudo = ? AND mot_de_passe = ?";
	//private static final String SELECT_MDP_BY_EMAIL =  "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?";
	
	private final String SELECT_STRING_MDP = "SELECT * FROM utilisateur WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";
	
	@Override
	public Utilisateur selectMdpAndPseudo(String pseudoOuMail, String mdp) throws TrocEnchereException {
		
		TrocEnchereException tee = new TrocEnchereException();
		Utilisateur user = new Utilisateur();
		
		try(Connection con = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = con.prepareStatement(SELECT_STRING_MDP);
			pstmt.setString(1, pseudoOuMail);
			pstmt.setString(2, pseudoOuMail);
			pstmt.setString(3, mdp);
			ResultSet rs = pstmt.executeQuery();
			
			
		while(rs.next()) {
			user.setPseudo(rs.getString("pseudo"));
			user.setEmail(rs.getString("email"));
			user.setMotDePasse(rs.getString("mot_de_passe"));
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
			tee.ajouterErreur("Erreur pendant le selectMdpByPseudo");
			throw tee;
		}
		return user;
	}
	
//	@Override
//	public Utilisateur selectMdpAndEmail(String email, String mdp) throws TrocEnchereException {
//		
//		TrocEnchereException tee = new TrocEnchereException();
//		Utilisateur user = new Utilisateur();
//		
//		try(Connection con = ConnectionProvider.getConnection()){
//			PreparedStatement pstmt = con.prepareStatement(SELECT_MDP_BY_EMAIL);
//			pstmt.setString(1, email);
//			pstmt.setString(2, mdp);
//			ResultSet rs = pstmt.executeQuery();
//			
//		while(rs.next()) {
//			user.setEmail("email");
//			user.setMotDePasse("mot_de_passe");
//		}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//			tee.ajouterErreur("Erreur pendant le selectMdpByEmail");
//			throw tee;
//		}
//		return user;
//	}
}
