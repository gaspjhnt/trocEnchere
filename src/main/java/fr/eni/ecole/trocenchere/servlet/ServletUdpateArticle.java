package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.detailsArticle.DetailsArticleManager;
import fr.eni.ecole.trocenchere.bll.detailsArticle.DetailsArticleManagerSing;
import fr.eni.ecole.trocenchere.bll.vendrearticle.VendreArticleManager;
import fr.eni.ecole.trocenchere.bll.vendrearticle.VendreArticleManagerSing;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Retrait;

/**
 * Servlet implementation class ServletUdpateArticle
 */
@WebServlet("/ServletUdpateArticle")
public class ServletUdpateArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUdpateArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest reque
	 * st, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DetailsArticleManager dao = DetailsArticleManagerSing.getInstance();
		VendreArticleManager daoVendre = VendreArticleManagerSing.getInstance();
		
		try {
			Article article = dao.selectArticleById(Integer.parseInt(request.getParameter("modifSupp")));
			article.setRetrait(dao.selectRetraitByArticle(article));
			request.setAttribute("article", article);

			System.out.println(article);
			//Envoie à la JSP la liste de toutes les catégories.
			request.setAttribute("lstCategorie", daoVendre.selectAllCategorie());
		} catch (TrocEnchereException e) {

			//Envoie à la JSP la liste des erreurs.
			request.setAttribute("lstErreur", e.getListeCodesErreur());
		}
		//Lance la jsp vendre un article.
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Article/UpdateArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		VendreArticleManager dao = VendreArticleManagerSing.getInstance();	
		DetailsArticleManager daoDetails = DetailsArticleManagerSing.getInstance();
		
		//Création de l'article
		Article article;
		Retrait retrait;
		try {	

			if (request.getParameter("supp") !=null) {
				daoDetails.deleteArticle(Integer.parseInt(request.getParameter("supp")));
				response.sendRedirect("./ServletListeEnchere");
			} else {

				article = daoDetails.selectArticleById(Integer.parseInt(request.getParameter("idArticle")));	
				retrait = daoDetails.selectRetraitByArticle(article);
			if (request.getParameter("supprArticle") == null) {
				article.setNomArticle(request.getParameter("nomArticle"));
				article.setDescription(request.getParameter("description"));
				article.setPrixDepart(Integer.parseInt(request.getParameter("prixArticle")));
				article.setDateDebutEnchere(LocalDate.parse(request.getParameter("dateDebutEnchere")));
				article.setDateFinEnchere(LocalDate.parse(request.getParameter("dateFinEnchere")));
				article.setCategorie(dao.selectCategorieById(Integer.parseInt(request.getParameter("categorie"))));
				
				

					retrait.setRue(request.getParameter("rueRetrait"));
					retrait.setCodePostal(request.getParameter("codePostalRetrait"));
					retrait.setVille(request.getParameter("villeRetrait"));
					
				daoDetails.updateRetrait(retrait);
				article.setRetrait(retrait);
				
				daoDetails.updateArticle(article);
				
				request.setAttribute("valide", "Article modifié !");

				//Lance la jsp vendre un article.
				response.sendRedirect("./ServletListeEnchere");
					
			} else {
				System.out.println("tu veux supprimer?");
				daoDetails.deleteRetrait(retrait.getNoRetrait());
				daoDetails.deleteArticle(article.getNoArticle());
				response.sendRedirect("./ServletListeEnchere");
			}
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (TrocEnchereException e1) {
			
			//Envoie à la JSP la liste des erreurs.
			request.setAttribute("lstErreur", e1.getListeCodesErreur());
			request.setAttribute("valide", null);
		}
		
		
		
		
		
		
	}

}
