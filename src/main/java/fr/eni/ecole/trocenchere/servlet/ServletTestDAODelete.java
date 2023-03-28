package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.dal.TrocEnchereDAOImplDelete;
import fr.eni.ecole.trocenchere.dal.TrocEnchereDAOInsertImpl;

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
		TrocEnchereDAOInsertImpl testInsert = new TrocEnchereDAOInsertImpl();
		
		try {
			testInsert.insertArticle(null, null, null);
			testInsert.insertCategorie(null);
			testInsert.insertEnchere(null, null, null);
			testInsert.insertRetrait(null, null);
			testInsert.insertUtilisateur(null);
		} catch (TrocEnchereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			testDelete.deleteArticle(null);
			testDelete.deleteUtilisateur(null);
			testDelete.deleteCategorie(null);
			testDelete.deleteEnchere(null);
			testDelete.deleteRetrait(null);
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
