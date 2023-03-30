package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.detailsArticle.DetailsArticleManager;
import fr.eni.ecole.trocenchere.bll.detailsArticle.DetailsArticleManagerSing;
import fr.eni.ecole.trocenchere.bo.Enchere;

/**
 * Servlet implementation class ServletDetailArticle
 */
@WebServlet("/ServletDetailArticle")
public class ServletDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetailArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DetailsArticleManager dao = DetailsArticleManagerSing.getInstance();
		Integer idArticle = Integer.parseInt(request.getParameter("idArticle"));
		try {
			request.setAttribute("article", dao.selectArticleById(idArticle));
			List<Enchere> encheres = dao.selectEnchereByArticle(dao.selectArticleById(idArticle));
			if (encheres == null || encheres.isEmpty()) {
				request.setAttribute("Enchere", "Aucune enchères");
				request.setAttribute("Proposition", dao.selectArticleById(idArticle).getPrixDepart());
			} else {
				Enchere enchere = encheres.get(encheres.size() - 1);
				request.setAttribute("Enchere", enchere.getMontant_enchere() + " pts par " + enchere.getUtilisateur().getPseudo());
				request.setAttribute("Proposition", enchere.getMontant_enchere());
			}
			request.setAttribute("Retrait", dao.selectRetraitByArticle(dao.selectArticleById(idArticle)));
		} catch (TrocEnchereException e) {
			if (e.hasErreurs()) {
				request.setAttribute("lstErreur", e.getListeCodesErreur());
			}
		}
		
		
		//Lance la jsp vendre un article.
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Article/DetailsArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("proposition"));
	}

}
