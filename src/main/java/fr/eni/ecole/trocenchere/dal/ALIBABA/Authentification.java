package fr.eni.ecole.trocenchere.dal.ALIBABA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentification {

	public boolean authenticate(String eMail, String motDePasse) {

		final String AUTHENTICATE = "SELECT * FROM users WHERE email=? and password=?";
		Boolean result = false; // Set result to false

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(AUTHENTICATE);
			pstmt.setString(1, eMail); // Set 1st WHERE to eMail
			pstmt.setString(2, motDePasse); // Set 2nd WHERE to motDePasse

			ResultSet rs = pstmt.executeQuery(); // Execute Query

			// On regarde s'il y a un résultat dans la base de donnée qui a le même email et
			// motdepasse
			if (!rs.next() && rs.getRow() == 0) {
				result = false; // S'il n'y a pas de résultat => Set false
				System.out.println("User details are incorrect.");
			} else {
				result = true; // S'il y a un résultat => set true
				System.out.println("User logged in.");
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
}
