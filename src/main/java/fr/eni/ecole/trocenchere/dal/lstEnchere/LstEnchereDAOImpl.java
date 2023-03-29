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
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class LstEnchereDAOImpl implements LstEnchereDAO{
	// renvoie tous les articles avec une date de début d'enchère < a la date DATE (donc les articles avec une début
		// d'enchères avant la date donnée
	
	private static final String SELECT_ARTICLE_BY_DATE_FIN = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie "
			+ "FROM article "
			+ "WHERE date_fin_enchere > ?";

	private static final String SELECT_USER_BY_ID = "SELECT noUtilisateur, pseudo, nom, prenom, email, telephone, rue, "
			+ "code_postal, ville, mot_de_passe, credit, "
			+ "administrateur FROM utilisateur "
			+ "where noUtilisateur = ?";
	
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT noCategorie, libelle FROM categorie "
			+ "WHERE noCategorie = ?";

	
	// renvoie tous les articles avec une date de fin d'enchère > a la date DATE (donc les articles avec une fin
		// d'enchères après la date donnée
		@Override
		public List<Article> selectArticleByDateFin(LocalDate date) throws TrocEnchereException {
			TrocEnchereException tee = new TrocEnchereException();
			List<Article> lstArticle = new  ArrayList<>();
			try(Connection con = ConnectionProvider.getConnection()){
			
			PreparedStatement stmtArticle = con.prepareStatement(SELECT_ARTICLE_BY_DATE_FIN);
			stmtArticle.setDate(1, java.sql.Date.valueOf(date));
			ResultSet rsArt = stmtArticle.executeQuery();
			

			PreparedStatement stmt = con.prepareStatement(SELECT_USER_BY_ID);
			PreparedStatement stmtCate = con.prepareStatement(SELECT_CATEGORIE_BY_ID );
			
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
				

				stmt.setInt(1, rsArt.getInt("Utilisateur_noUtilisateur"));
				ResultSet rs = stmt.executeQuery();
				Utilisateur user = new Utilisateur();
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
					}

				art.setUtilisateur(user);
				
				lstArticle.add(art);
			}
		}catch (SQLException e){
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectArticleByDateFin)");
			throw tee;
		}
		return lstArticle;
	}
}
