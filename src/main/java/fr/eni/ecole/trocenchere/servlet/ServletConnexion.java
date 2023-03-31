package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.connexion.ConnexionManager;
import fr.eni.ecole.trocenchere.bll.connexion.ConnexionManagerSing;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.ALIBABA.TrocEnchereDAOImplSelect;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Redirection vers la page de connexion (JSP)
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspConnexion.jsp");
	    rd.include(request, response);
	}
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	// Initialisation des variables de connexion
	    	String username = request.getParameter("name");
	        String password = request.getParameter("password");
	        
	        ConnexionManager dao = ConnexionManagerSing.getInstance();


	        try {
	        	//Appelle a la BLL pour voir si les ID existent
				if (dao.login(username, password) != null) {
					
					//Si oui on créer une session
					HttpSession session = request.getSession();	            
					if (session != null) {
						// on set l'interval d'innactivié a 30min
						session.setMaxInactiveInterval(1800);
						//On ajoute l'utilisateur a la session
				    	session.setAttribute("Utilisateur", dao.login(username, password));
				    }
					//On redirige vers l'accueil en étant connecté
				    response.sendRedirect("http://localhost:8080/trocEnchere/ServletListeEnchere");
				}
					
				
			} catch (TrocEnchereException e) {
				//Impossible d'accéder a la BLL donc on envoie la liste d'erreur
				request.setAttribute("lstErreur", e.getListeCodesErreur());
				
				//Et on reste sur la même page avec les erreurs affichées dans la JSP
			    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspConnexion.jsp");
			    rd.include(request, response);
			}		
	           
	    }
	    
}
