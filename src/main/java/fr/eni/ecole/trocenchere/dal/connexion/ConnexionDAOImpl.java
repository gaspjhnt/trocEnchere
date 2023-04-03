package fr.eni.ecole.trocenchere.dal.connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.ConnectionProvider;

public class ConnexionDAOImpl implements ConnexionDAO {

	private static final String SELECT_STRING_MDP = "SELECT noUtilisateur, pseudo, nom, prenom, email, telephone, rue, "
			+ "code_postal, ville, mot_de_passe, credit, "
			+ "administrateur FROM utilisateur WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";
	
	private static final String SELECT_ARTICLE_BY_USER = "SELECT noArticle, nom, description, date_debut_enchere, "
			+ "date_fin_enchere, prix_depart, prix_vente, "
			+ "etat_vente, Utilisateur_noUtilisateur, Categorie_noCategorie "
			+ "FROM article "
			+ "WHERE Utilisateur_noUtilisateur = ?";
	
	private static final String SELECT_ENCHERE_BY_USER = "SELECT noEnchere, date_enchere, montant_enchere,"
			+ " Utilisateur_noUtilisateur, Article_noArticle"
			+ " FROM enchere "
			+ "WHERE Utilisateur_noUtilisateur = ?";
	
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT noCategorie, libelle FROM categorie "
			+ "WHERE noCategorie = ?";
	
	
	@Override
	public Utilisateur selectMdpAndPseudo(String pseudoOuMail, String mdp) throws TrocEnchereException {
		
		TrocEnchereException tee = new TrocEnchereException();
		Utilisateur user = new Utilisateur();
		
		try(Connection con = ConnectionProvider.getConnection()){
			
			//Preparation du select USER by PSEUDO ou EMAIL et MDP
			PreparedStatement pstmt = con.prepareStatement(SELECT_STRING_MDP);
			pstmt.setString(1, pseudoOuMail);
			pstmt.setString(2, pseudoOuMail);
			pstmt.setString(3, mdp);
			ResultSet rs = pstmt.executeQuery();
			
			//Préparation des selects pour remplir le user
			PreparedStatement stmtArticle = con.prepareStatement(SELECT_ARTICLE_BY_USER);
			List<Article> lstArticle = new  ArrayList<>();
			
			PreparedStatement stmtEnchere = con.prepareStatement(SELECT_ENCHERE_BY_USER);
			List<Enchere> lstEnchere = new  ArrayList<>();
			
			PreparedStatement stmtCate = con.prepareStatement(SELECT_CATEGORIE_BY_ID );
			
			
			//Création et ajout des infos au user
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

				//Recherche de sa liste d'article
				stmtArticle.setInt(1, user.getNoUtilisateur());
				ResultSet rsArt = stmtArticle.executeQuery();
				//Création des articles
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
					//Recherche de la catégorie pour l'article
					ResultSet rsCate = stmtCate.executeQuery();
					Categorie cate = new Categorie();
					//Création de l'objet catégorie
					if (rsCate.next()) {
						cate.setLibelle(rsCate.getString("libelle"));
						cate.setNoCategorie(rsCate.getInt("noCategorie"));
					}
					// On l'ajoute a l'article en question
					art.setCategorie(cate);
					
					art.setUtilisateur(user);
					//Ajout de l'article a la liste d'articles
					lstArticle.add(art);
				}
				//Recherche de sa liste d'enchère
				stmtEnchere.setInt(1, user.getNoUtilisateur());
				ResultSet rsEnchere = stmtEnchere.executeQuery();
				//Création des enchères
				while (rsEnchere.next()) {
					Enchere enchere = new Enchere();
					enchere.setNoEnchere(rsEnchere.getInt("noEnchere"));
					enchere.setDateEnchere(rsEnchere.getDate("date_enchere").toLocalDate());
					enchere.setMontantEnchere(rsEnchere.getInt("montant_enchere"));
					enchere.setUtilisateur(user);
					//Ajout de l'enchère a la liste d'enchères
					lstEnchere.add(enchere);
				}
				
				//On ajout les deux listes a l'utilisateur
				user.setLstArticle(lstArticle);
				user.setLstEnchere(lstEnchere);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			tee.ajouterErreur("Problème à la selection des données (selectMdpAndPseudo)");
			throw tee;
		}
		return user;
	}
	

}
