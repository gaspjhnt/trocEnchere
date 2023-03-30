package fr.eni.ecole.trocenchere.servlet.alibaba;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.ALIBABA.TrocEnchereDAOInsertImpl;

/**
 * Servlet implementation class ServletTestDAOInsert
 */
@WebServlet("/ServletTestDAOInsert")
public class ServletTestDAOInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTestDAOInsert() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur u1 = new Utilisateur("Haste", "Desnoes", "Jérémie", "bloblogmail.com", "0695067182",
				"Rue du moulin", "35170", "Rennes", "Kilokoko30");
		u1.setAdministrateur(true);
		Retrait r1 = new Retrait("Rue du moulin", "35000", "Rennes");
		Categorie c1 = new Categorie("Sport");
		Categorie c2 = new Categorie("Ameublement");
		Categorie c3 = new Categorie("Informatique");
		Categorie c4 = new Categorie("Vetement");
		TrocEnchereDAOInsertImpl dao = new TrocEnchereDAOInsertImpl();
		try {
			dao.insertUtilisateur(u1);
			dao.insertCategorie(c1);
			dao.insertCategorie(c2);
			dao.insertCategorie(c3);
			dao.insertCategorie(c4);

			// Maintenant que l'utilisateur a été inséré, on peut créer et insérer le velo

			Article a1 = new Article("Velo", "Mon vélo vert", LocalDate.now(), LocalDate.of(2023,Month.MARCH,27),150, u1, c1);
			Article a2 = new Article("Velo", "Mon vélo bleu", LocalDate.now(),LocalDate.of(2023,Month.APRIL,30), 150, u1, c1);
			Article a3 = new Article("Pull", "laine bleu", LocalDate.now(),LocalDate.of(2023,Month.APRIL,30), 150, u1, c4);
			Article a4 = new Article("Pc", "pour gros gamer", LocalDate.now(),LocalDate.of(2023,Month.APRIL,30), 150, u1, c3);
			Article a5 = new Article("Table", "pour bien manger", LocalDate.now(),LocalDate.of(2023,Month.APRIL,30), 150, u1, c2);
			dao.insertArticle(a1);
			dao.insertArticle(a2);
			dao.insertArticle(a3);
			dao.insertArticle(a4);
			dao.insertArticle(a5);
			// même chose pour retrait et enchereRetrait
			r1 = new Retrait("rue de la motte", "35000", "Rennes", a1);
			dao.insertRetrait(r1);
			Enchere e1 = new Enchere(LocalDate.now(), 150, u1, a1);
			dao.insertEnchere(e1);
			response.getWriter().append("L'utilisateur a été inséré avec succès et son numéro d'utilisateur est : ")
					.append(String.valueOf(u1.getNoUtilisateur()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
