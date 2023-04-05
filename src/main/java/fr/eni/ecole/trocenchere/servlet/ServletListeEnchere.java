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
import javax.servlet.http.HttpSession;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.lstEnchere.LstEnchereManagerSing;
import fr.eni.ecole.trocenchere.bll.lstEnchere.lstEnchereManager;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

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
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("Utilisateur");

		
		//Création d'un cookie "Choix cookie" qui permet de stocker la catégorie choisie par l'utilisateur
		Cookie unCookie = new Cookie("ChoixCookie", request.getParameter("categorie"));
		unCookie.setMaxAge(3600);
		response.addCookie(unCookie);
		
		//Création d'un deuxième cookie pour stocker la valeur entrée par l'utilisateur
		Cookie deuxiemeCookie = new Cookie("RechercheCookie", request.getParameter("recherche"));
		deuxiemeCookie.setMaxAge(3600);
		response.addCookie(deuxiemeCookie);
		
		//Création d'un troisième cookie pour stocker la valeur du bouton achats
		String buttonValue = request.getParameter("bouton-radio");
		if (buttonValue == null) {
		    buttonValue = ""; 
		}
		Cookie troisiemeCookie = new Cookie("boutonRadioCookie", buttonValue);
		troisiemeCookie.setMaxAge(3600);
		response.addCookie(troisiemeCookie);
		
		//Création d'un quatrieme cookie pour stocker la valeur du bouton mesVentes
		String checkboxValue = request.getParameter("checkbox-1");
		if (checkboxValue == null) {
		    checkboxValue = ""; 
		}

		Cookie quatriemeCookie = new Cookie("checkboxesCookie1", checkboxValue);
		quatriemeCookie.setMaxAge(3600);
		response.addCookie(quatriemeCookie);
		
		//Création d'un cinquieme cookie pour stocker la valeur du bouton mesVentes
		String checkboxValue2 = request.getParameter("checkbox-2");
		if (checkboxValue2 == null) {
		    checkboxValue2 = "";
		}
		Cookie cinquiemeCookie = new Cookie("checkboxesCookie2", checkboxValue2);
		cinquiemeCookie.setMaxAge(3600);
		response.addCookie(cinquiemeCookie);
		
		//je Set les attributs clés "ChoixCookie" et "RechercheCookie" avec les paramètres rentrées par l'utilisateur
		request.setAttribute("ChoixCookie", unCookie);
		request.setAttribute("RechercheCookie", deuxiemeCookie);
		request.setAttribute("boutonRadioCookie", troisiemeCookie);
		request.setAttribute("checkboxesCookie1", quatriemeCookie);
		request.setAttribute("checkboxesCookie2", cinquiemeCookie);

		
		
		System.out.println(unCookie.getValue());
		System.out.println(deuxiemeCookie.getValue());
		System.out.println(troisiemeCookie.getValue());
		System.out.println(quatriemeCookie.getValue());
		System.out.println(cinquiemeCookie.getValue());
		
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
