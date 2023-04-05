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
	
	private static final String SELECT_USER_BY_ID = "SELECT noUtilisateur, pseudo, nom, prenom, email, telephone, rue, "
			+ "code_postal, ville, mot_de_passe, credit, "
			+ "administrateur FROM utilisateur "
			+ "where noUtilisateur = ?";
	
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
		PreparedStatement stmtUser = con.prepareStatement(SELECT_USER_BY_ID);

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
				
				stmtUser.setInt(1, rsArt.getInt("Utilisateur_noUtilisateur"));
				ResultSet rs1 = stmtUser.executeQuery();
				Utilisateur user = new Utilisateur();
				if (rs1.next()) {
					user.setNoUtilisateur(rs1.getInt("noUtilisateur"));
					user.setNom(rs1.getString("nom"));
					user.setPrenom(rs1.getString("prenom"));
					user.setPseudo(rs1.getString("pseudo"));
					user.setEmail(rs1.getString("email"));
					user.setTelephone(rs1.getString("telephone"));
					user.setRue(rs1.getString("rue"));
					user.setCodePostal(rs1.getString("code_postal"));
					user.setVille(rs1.getString("ville"));
					user.setMotDePasse(rs1.getString("mot_de_passe"));
					user.setCredit(rs1.getInt("credit"));
					user.setAdministrateur(rs1.getBoolean("administrateur"));
				}
				art.setUtilisateur(user);
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
