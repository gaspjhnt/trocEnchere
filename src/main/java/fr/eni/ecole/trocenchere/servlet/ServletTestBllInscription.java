package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.inscription.InscriptionManagerImpl;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletTestBllInscription
 */
@WebServlet("/ServletTestBllInscription")
public class ServletTestBllInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestBllInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InscriptionManagerImpl incriptionManager = new InscriptionManagerImpl();
		try {
			incriptionManager.ajouter("Haste", "dESNOES", "Jérémie", "bloublou@gmail.com", "0695089324", "Rue du moulin",
					"35170", "Bruz", "CzfzF*dz09");
		} catch (TrocEnchereException e) {
			if(e.hasErreurs()) {
				System.out.println(e.getListeCodesErreur());
			}
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
