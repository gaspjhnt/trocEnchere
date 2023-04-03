package fr.eni.ecole.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Categorie;

public class DAOCategorieImpl implements DAOCategorie {

	private static final String SELECT_CATEGORIE_BY_ID = "SELECT noCategorie, libelle FROM categorie "
			+ "WHERE noCategorie = ?";

	@Override
	public Categorie selectById(int id) throws TrocEnchereException {

		Categorie cate = null;

		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmtCate = con.prepareStatement(SELECT_CATEGORIE_BY_ID);
			stmtCate.setInt(1, id);
			ResultSet rsCate = stmtCate.executeQuery();

			if (rsCate.next()) {
				cate = new Categorie();
				cate.setLibelle(rsCate.getString("libelle"));
				cate.setNoCategorie(rsCate.getInt("noCategorie"));
			}
		} catch (SQLException e) {
			TrocEnchereException tee = new TrocEnchereException();
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectArticleByDateFin)");
			throw tee;
		}
		return cate;
	}

}
