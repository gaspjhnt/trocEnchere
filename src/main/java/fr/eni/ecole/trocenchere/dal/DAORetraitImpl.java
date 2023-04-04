package fr.eni.ecole.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Retrait;

public class DAORetraitImpl implements DAORetrait{

	
	private static final String SELECT_RETRAIT_BY_ARTICLE = "SELECT noRetrait, rue, code_postal, "
			+ "ville, Article_noArticle "
			+ "FROM retrait WHERE Article_noArticle = ?";	
	
	private static final String INSERT_RETRAIT ="INSERT INTO retrait(rue,code_postal,ville,Article_NoArticle) VALUES(?,?,?,?)";	
	
	private static final String UPDATE_RETRAIT = "UPDATE Retrait SET rue=?, code_postal=?, ville=? WHERE noRetrait=?";

	private static final String DELETE_RETRAIT = "DELETE FROM Retrait where noRetrait=?";
	


	@Override
	public Retrait selectRetraitByArticle(Article article) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		Retrait retrait = new Retrait();
		try(Connection con = ConnectionProvider.getConnection()){
		
		PreparedStatement stmt = con.prepareStatement(SELECT_RETRAIT_BY_ARTICLE);
		stmt.setInt(1, article.getNoArticle());
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			retrait.setNoRetrait(rs.getInt("noRetrait"));
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			retrait.setArticle(article);

		}
		
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectRetraitByArticle)");
			throw tee;
		}
		return retrait;
	}



	@Override
	public void insertRetrait(Retrait retrait) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
			
			try(Connection cnx = ConnectionProvider.getConnection()){
				
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, retrait.getRue());
				pstmt.setString(2, retrait.getCodePostal());
				pstmt.setString(3, retrait.getVille());
				pstmt.setInt(4, retrait.getArticle().getNoArticle());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					retrait.setNoRetrait(rs.getInt(1));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				tee.ajouterErreur("Problème à l'insertion des données (insertRetrait)");
				throw tee;
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
	public void deleteRetrait(int idRetrait) throws TrocEnchereException  {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);
			pstmt.setInt(1, idRetrait);
			pstmt.executeUpdate();
		} catch (Exception e) {

		}

	}

}
