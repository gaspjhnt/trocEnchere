package fr.eni.ecole.trocenchere.servlet.alibaba;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.ALIBABA.TrocEnchereDAOImplDelete;
import fr.eni.ecole.trocenchere.dal.ALIBABA.TrocEnchereDAOInsertImpl;

/**
 * Servlet implementation class ServletTestDAODelete
 */
@WebServlet("/ServletTestDAODelete")
public class ServletTestDAODelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestDAODelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TrocEnchereDAOImplDelete testDelete = new TrocEnchereDAOImplDelete();
		try {
			testDelete.deleteArticle(6);
			testDelete.deleteUtilisateur(19);
			testDelete.deleteCategorie(14);
			testDelete.deleteEnchere(3);
			testDelete.deleteRetrait(4);
		} catch (TrocEnchereException e) {
			// TODO Auto-generated catch block
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
