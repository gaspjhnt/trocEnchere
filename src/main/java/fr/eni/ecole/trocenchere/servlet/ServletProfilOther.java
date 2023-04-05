package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateur;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateurFactory;


/**
 * Servlet implementation class ServletProfilOther
 */
@WebServlet("/ServletProfilOther")
public class ServletProfilOther extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfilOther() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOUtilisateur daoUser = DAOUtilisateurFactory.getDAOUtilisateur();
		
		Integer userID = Integer.parseInt(request.getParameter("seller"));
		
		
		try {
			Utilisateur user = daoUser.selectById(userID);
			System.out.println("on envoie");
			request.setAttribute("user", user);
		} catch (TrocEnchereException e) {
			System.out.println("ça bug");
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspProfilOther.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.sendRedirect(request.)
	}

}
