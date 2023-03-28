package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		TrocEnchereDAOInsertImpl dao = new TrocEnchereDAOInsertImpl();
		try {
			dao.insertUtilisateur(u1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("Coucou ").append(u1.getNoUtilisateur() + "");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
