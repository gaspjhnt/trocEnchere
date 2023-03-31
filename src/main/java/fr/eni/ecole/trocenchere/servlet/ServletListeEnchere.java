package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.lstEnchere.LstEnchereManagerSing;
import fr.eni.ecole.trocenchere.bll.lstEnchere.lstEnchereManager;

/**
 * Servlet implementation class ServletListeEnchere
 */
@WebServlet("/ServletListeEnchere")
public class ServletListeEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListeEnchere() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Création d'un dao avec le manager pour obtenir les méthodes nécessaires
		lstEnchereManager dao = LstEnchereManagerSing.getInstance();
		
		try {
			// On get les articles par rapport à la date d'aujourd'hui
			//Je set une clé "article" avec la Liste d'article dont la date de fin d'enchère est supérieure à la date d'aujourd'hui
			request.setAttribute("article", dao.getAllArticlesByDate(LocalDate.now()));
		} catch (TrocEnchereException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspListeEnchere.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Création d'un cookie "Choix cookie" qui permet de stocker la catégorie choisi par l'utilisateur
		Cookie unCookie = new Cookie("ChoixCookie", request.getParameter("categorie"));
		unCookie.setMaxAge(3600);
		response.addCookie(unCookie);
		
		//Création d'un deuxième cookie pour stocker la valeur entré par l'utilisateur
		Cookie deuxCookie = new Cookie("RechercheCookie", request.getParameter("recherche"));
		unCookie.setMaxAge(3600);
		response.addCookie(deuxCookie);
		
		//je Set les attributs clés "ChoixCookie" et "RechercheCookie" avec le paramètre rentré par l'utilisateur
		request.setAttribute("ChoixCookie", unCookie);
		request.setAttribute("RechercheCookie", deuxCookie);

		lstEnchereManager dao = LstEnchereManagerSing.getInstance();
		
		try {
			System.out.println(dao.getAllArticlesByDate(LocalDate.now()));
			request.setAttribute("article", dao.getAllArticlesByDate(LocalDate.now()));
		} catch (TrocEnchereException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspListeEnchere.jsp");
		rd.forward(request, response);
	}

}
