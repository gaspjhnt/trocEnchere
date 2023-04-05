package fr.eni.ecole.trocenchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Enchere;

public class DAOEncheresImpl implements DAOEncheres{
	

	public static final String INSERT_ENCHERE ="INSERT INTO enchere(date_enchere,montant_enchere,Utilisateur_noUtilisateur,Article_noArticle) VALUES(?,?,?,?)";

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

}
