package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

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
		request.getSession().setAttribute("idArticleDetailsArticle", idArticle);
		try {
			Article article = dao.selectArticleById(idArticle);
			
			//On envoie l'article
			request.setAttribute("article", article);
			
			List<Enchere> lstEnchere = dao.selectEnchereByArticle(article);
			request.setAttribute("lstEnchere", lstEnchere);
			
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
		DetailsArticleManager dao = DetailsArticleManagerSing.getInstance();
		Integer idArticle = (Integer) request.getSession().getAttribute("idArticleDetailsArticle");
		try {
			Article article = dao.selectArticleById(idArticle);
			Enchere enchere = new Enchere();
			
			enchere.setArticle(article);
			enchere.setDateEnchere(LocalDate.now());
			enchere.setMontantEnchere(Integer.parseInt(request.getParameter("proposition")));
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("Utilisateur");
			enchere.setUtilisateur(user);
			
			dao.insertEnchere(enchere);
			request.getSession().setAttribute("lstErreurEnchere", new ArrayList<>());
		} catch (TrocEnchereException e) {			
			if (e.hasErreurs()) {
				request.getSession().setAttribute("lstErreurEnchere", e.getListeCodesErreur());
			}
		}
		List<String> lstErreur = (List<String>) request.getSession().getAttribute("lstErreurEnchere");	
		if (lstErreur.size() <= 0) {
			request.getSession().setAttribute("SuccesDetailsArticle", "Enchère ajoutée !");
			System.out.println("succès");
		} else {
			request.getSession().setAttribute("SuccesDetailsArticle", null);
			System.out.println("nan");
		}
		response.sendRedirect("http://localhost:8080/trocEnchere/ServletListeEnchere");
	}

}
