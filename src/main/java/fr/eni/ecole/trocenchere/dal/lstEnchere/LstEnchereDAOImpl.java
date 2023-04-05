package fr.eni.ecole.trocenchere.dal.lstEnchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.ConnectionProvider;
import fr.eni.ecole.trocenchere.dal.DAOArticle;
import fr.eni.ecole.trocenchere.dal.DAOArticleImpl;

public class LstEnchereDAOImpl implements LstEnchereDAO{
	// renvoie tous les articles avec une date de début d'enchère < a la date DATE (donc les articles avec une début
		// d'enchères avant la date donnée

	
	private static final String SELECT_ENCHERE_BY_USER = "SELECT noEnchere, date_enchere, montant_enchere,"
					+ " Utilisateur_noUtilisateur, Article_noArticle"
					+ " FROM enchere "
					+ "WHERE Utilisateur_noUtilisateur = ?";
	

	private static final String SELECT_ARTICLE_BY_ID = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie "
			+ "FROM article "
			+ "WHERE noArticle = ?";
	
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT noCategorie, libelle FROM categorie "
			+ "WHERE noCategorie = ?";

	
// renvoie tous les articles avec une date de fin d'enchère > a la date DATE (donc les articles avec une fin
	// d'enchères après la date donnée
	@Override
	public List<Article> selectArticleByDateFin(LocalDate date) throws TrocEnchereException {
		DAOArticle dao = new DAOArticleImpl();
		return dao.selectArticleByDateFin(date);
	}
	
	


	@Override
	public List<Enchere> selectEnchereByUser(Utilisateur utilisateur) throws TrocEnchereException {
		TrocEnchereException tee = new TrocEnchereException();
		List<Enchere> lstEnchere = new  ArrayList<>();
		try(Connection con = ConnectionProvider.getConnection()){
		
		PreparedStatement stmt = con.prepareStatement(SELECT_ENCHERE_BY_USER);
		

		PreparedStatement stmtArt = con.prepareStatement(SELECT_ARTICLE_BY_ID);
		PreparedStatement stmtCate = con.prepareStatement(SELECT_CATEGORIE_BY_ID );
		
		stmt.setInt(1, utilisateur.getNoUtilisateur());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Enchere enchere = new Enchere();
			enchere.setNoEnchere(rs.getInt("noEnchere"));
			enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
			enchere.setMontantEnchere(rs.getInt("montant_enchere"));
			enchere.setUtilisateur(utilisateur);
			

			stmtArt.setInt(1, rs.getInt("Article_noArticle"));
			ResultSet rsArt = stmtArt.executeQuery();
			Article art = new Article();
			if (rsArt.next()) {
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
		}
			enchere.setArticle(art);

			lstEnchere.add(enchere);
		}
		
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (SelectEncherByArticle)");
			throw tee;
		}
		return lstEnchere;
	}

}
