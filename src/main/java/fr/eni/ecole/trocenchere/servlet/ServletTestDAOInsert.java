package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.time.LocalDate;

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
import fr.eni.ecole.trocenchere.dal.TrocEnchereDAOInsertImpl;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur u1 = new Utilisateur("Haste", "Desnoes", "Jérémie", "bloblogmail.com", "0695067182", "Rue du moulin",
				"35170", "Rennes","Kilokoko30");
		Retrait r1 = new Retrait("Rue du moulin", "35000", "Rennes");
		Categorie c1 = new Categorie("Sport");
		Article a1 = new Article("Velo", "Mon vélo rouge", LocalDate.now(), LocalDate.now(),
				150,c1);
		Enchere e1 = new Enchere(LocalDate.now(),300,u1,a1);
		
		TrocEnchereDAOInsertImpl dao = new TrocEnchereDAOInsertImpl();
		try {
			//dao.insertUtilisateur(u1);
			//dao.insertCategorie(c1);
			dao.insertArticle(a1, u1, c1);
			//dao.insertRetrait(r1, a1);
			
			//dao.insertEnchere(e1, u1, a1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("Coucou ").append(a1.getUtilisateur().getNoUtilisateur() + "");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
