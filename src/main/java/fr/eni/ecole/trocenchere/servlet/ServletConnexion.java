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
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspConnexion.jsp");
	    rd.include(request, response);
	}
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String username = request.getParameter("name");
	        String password = request.getParameter("password");
	        
	        ConnexionManager dao = ConnexionManagerSing.getInstance();


	        try {
				if (dao.login(username, password) != null) {
					HttpSession session = request.getSession();	            
					if (session != null) {
				    	session.setAttribute("Utilisateur", dao.login(username, password));
				        Cookie message = new Cookie("message", "Bienvenue");
				        response.addCookie(message);
				    }

				    response.sendRedirect("http://localhost:8080/trocEnchere/ServletListeEnchere");

				}
					
				
			} catch (TrocEnchereException e) {
				request.setAttribute("lstErreur", e.getListeCodesErreur());
				System.out.println("Erreur " + e.getListeCodesErreur());
			    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspConnexion.jsp");
			    rd.include(request, response);
			}		
	           
	    }
	    
}
