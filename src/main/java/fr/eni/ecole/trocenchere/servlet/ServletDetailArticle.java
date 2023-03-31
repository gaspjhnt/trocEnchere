package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.detailsArticle.DetailsArticleManager;
import fr.eni.ecole.trocenchere.bll.detailsArticle.DetailsArticleManagerSing;
import fr.eni.ecole.trocenchere.bo.Article;
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
		
		//On récupère l'id article donné par la value du select
//		HttpSession session = request.getSession();
//		int max = (int) session.getAttribute("indexDetailMax");
//		for (int index = 0; index < max; index++ ) {
//			
//		}
		List<Article> article = (List<Article>) request.getAttribute("article");
		System.out.println(article);

		Integer idArticle = Integer.parseInt(request.getParameter("idArticle"));
		try {
			//On envoie l'article
			request.setAttribute("article", dao.selectArticleById(idArticle));
			List<Enchere> encheres = dao.selectEnchereByArticle(dao.selectArticleById(idArticle));
			
			//On verifie si il y a des enchères ou pas
			if (encheres == null || encheres.isEmpty()) {
				//Si non on envoie "pas d'enchère"
				request.setAttribute("Enchere", "Aucune enchères");
				//et on met la proposition la plus basse au prix de base
				request.setAttribute("Proposition", dao.selectArticleById(idArticle).getPrixDepart());
			} else {
				//Si non on récupère la dernière enchère
				Enchere enchere = encheres.get(encheres.size() - 1);
				//On envoie le prix de l'enchère + le nom de l'acheteur
				request.setAttribute("Enchere", enchere.getMontant_enchere() + " pts par " + enchere.getUtilisateur().getPseudo());
				//Et on set la proposition au prix de l'enchère
				request.setAttribute("Proposition", enchere.getMontant_enchere());
			}
			//On va récuperer le retrait de l'article en BDD
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
