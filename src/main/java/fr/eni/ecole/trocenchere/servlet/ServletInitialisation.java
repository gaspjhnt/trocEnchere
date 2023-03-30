package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.vendrearticle.VendreArticleManager;
import fr.eni.ecole.trocenchere.bll.vendrearticle.VendreArticleManagerSing;
import fr.eni.ecole.trocenchere.bo.Categorie;

/**
 * Servlet implementation class ServletInitialisation
 */
@WebServlet("/ServletInitialisation")
public class ServletInitialisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInitialisation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VendreArticleManager dao = VendreArticleManagerSing.getInstance();
		
		Categorie informatique = new Categorie("Informatique");
		Categorie ameublement = new Categorie("Ameublement");
		Categorie vetement = new Categorie("Vêtement");
		Categorie sportEtLoisirs = new Categorie("Sport & Loisirs");
		
		try {
			dao.insertCategorie(informatique);
			dao.insertCategorie(ameublement);
			dao.insertCategorie(vetement);
			dao.insertCategorie(sportEtLoisirs);
		} catch (TrocEnchereException e) {
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
