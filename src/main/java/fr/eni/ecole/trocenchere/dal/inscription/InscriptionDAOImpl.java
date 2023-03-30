package fr.eni.ecole.trocenchere.dal.inscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class InscriptionDAOImpl implements InscriptionDAO {

	public static final String INSERT_UTILISATEUR = "INSERT INTO utilisateur(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_PSEUDO ="SELECT pseudo FROM utilisateur";
	public static final String SELECT_EMAIL ="SELECT email FROM utilisateur";
	
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereException {
		
		TrocEnchereException tee = new TrocEnchereException();
		
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
		catch(SQLException e) {
			e.printStackTrace();
			tee.ajouterErreur("Probleme à l'ajout d'un utilisateur dans la base de données");
			throw tee;
		}
	}

	@Override
	public List<String> getAllPseudo() throws TrocEnchereException {
		
		TrocEnchereException tee = new TrocEnchereException();
		List<String> lstPseudo = new ArrayList<>();
		
		try(Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(SELECT_PSEUDO);
			ResultSet rs = stmt.executeQuery();
			
		while(rs.next()) {
			lstPseudo.add(rs.getString("pseudo"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			tee.ajouterErreur("Problème au niveau du GetAllPseudo de la base de données");
		}
		return lstPseudo;
		
	}

	@Override
	public List<String> getAllEmail() throws TrocEnchereException {
		
		TrocEnchereException tee = new TrocEnchereException();
		List<String> lstEmail = new ArrayList<>();
		
		try(Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(SELECT_EMAIL);
			ResultSet rs = stmt.executeQuery();
			
		while(rs.next()) {
			lstEmail.add(rs.getString("email"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			tee.ajouterErreur("Problème au niveau du GetAllEmail de la base de données");
		}
		return lstEmail;
	}

}
