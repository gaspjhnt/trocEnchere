package fr.eni.ecole.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.ecole.trocenchere.TrocEnchereExcepetion;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public class TrocEnchereDAOInsertImpl implements TrocEnchereDAOInsert {
	
	public static final String INSERT_UTILISATEUR = "INSERT INTO utilisateur(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_ARTICLE ="INSERT INTO article(nom,description,date_debut_enchere,date_fin_enchere,prix_depart,prix_vente,etat_vente,Utilisateur_noUtilisateur,Categorie_noCategorie) VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_ENCHERE ="INSERT INTO enchere(date_enchere,montant_enchere,Utilisateur_noUtilisateur,Article_noArticle) VALUES(?,?,?,?)";
	public static final String INSERT_CATEGORIE ="INSERT INTO categorie(libelle) VALUES(?)";
	public static final String INSERT_RETRAIT ="INSERT INTO retrait(rue,code_postal,ville,Article_NoArticle) VALUES(?,?,?,?)";
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws TrocEnchereExcepetion {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostale());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			if(utilisateur.isAdministrateur()) {
				pstmt.setInt(11, 1);
			}
			else {
				pstmt.setInt(11, 0);
			}
		}
		catch(Exception e) {
			
		}
		
	}
	@Override
	public void insertArticle(Article article) throws TrocEnchereExcepetion {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insertEnchere(Enchere enchere) throws TrocEnchereExcepetion {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insertCategorie(Categorie categorie) throws TrocEnchereExcepetion {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insertRetrait(Retrait retrait) throws TrocEnchereExcepetion {
		// TODO Auto-generated method stub
		
	}
}
