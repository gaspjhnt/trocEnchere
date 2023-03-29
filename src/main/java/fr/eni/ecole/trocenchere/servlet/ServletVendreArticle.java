package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.vendrearticle.VendreArticleManager;
import fr.eni.ecole.trocenchere.bll.vendrearticle.VendreArticleManagerSing;

/**
 * Servlet implementation class ServletVendreArticle
 */
@WebServlet("/ServletVendreArticle")
public class ServletVendreArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendreArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		VendreArticleManager dao = VendreArticleManagerSing.getInstance();
		
		
		try {
			request.setAttribute("lstCategorie", dao.selectAllCategorie());
		} catch (TrocEnchereException e) {
			request.setAttribute("lstErreur", e.getListeCodesErreur());
		}
		
//		Categorie informatique = new Categorie("Informatique");
//		Categorie ameublement = new Categorie("Ameublement");
//		Categorie vetement = new Categorie("Vêtement");
//		Categorie sportEtLoisirs = new Categorie("Sport & Loisirs");
//		
//		try {
//			dao.insertCategorie(informatique);
//			dao.insertCategorie(ameublement);
//			dao.insertCategorie(vetement);
//			dao.insertCategorie(sportEtLoisirs);
//		} catch (TrocEnchereException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/VendreArticle/VendreArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
