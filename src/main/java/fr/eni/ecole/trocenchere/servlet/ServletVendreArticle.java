package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.vendrearticle.VendreArticleManager;
import fr.eni.ecole.trocenchere.bll.vendrearticle.VendreArticleManagerSing;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Retrait;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletVendreArticle
 */
@WebServlet("/ServletVendreArticle")
public class ServletVendreArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendreArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VendreArticleManager dao = VendreArticleManagerSing.getInstance();
		
		try {
			//Envoie à la JSP la liste de toutes les catégories.
			request.setAttribute("lstCategorie", dao.selectAllCategorie());
		} catch (TrocEnchereException e) {
			
			//Envoie à la JSP la liste des erreurs.
			request.setAttribute("lstErreur", e.getListeCodesErreur());
		}
			
		//Lance la jsp vendre un article.
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Article/VendreArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		VendreArticleManager dao = VendreArticleManagerSing.getInstance();
		
		//Création de l'article
		Article article = new Article();
		
		article.setNomArticle(request.getParameter("nomArticle"));
		article.setDescription(request.getParameter("description"));
		
		//Set la catégorie grâce a la value renvoyer
		try {
			article.setCategorie(dao.selectCategorieById(Integer.parseInt(request.getParameter("categorie"))));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (TrocEnchereException e1) {
			
			//Envoie à la JSP la liste des erreurs.
			request.setAttribute("lstErreur", e1.getListeCodesErreur());
		}
		
		article.setPrixDepart(Integer.parseInt(request.getParameter("prixArticle")));
		article.setDateDebutEnchere(LocalDate.parse(request.getParameter("dateDebutEnchere")));
		article.setDateFinEnchere(LocalDate.parse(request.getParameter("dateFinEnchere")));
		
		//Création du retrait avec soit l'adresse saisie par le user soit son adresse a lui (valeur par defaut)
		Retrait retrait = new Retrait();
		retrait.setArticle(article);
		retrait.setRue(request.getParameter("rueRetrait"));
		retrait.setCodePostal(request.getParameter("codePostalRetrait"));
		retrait.setVille(request.getParameter("villeRetrait"));
		
		//Ajout du retrait dans l'article
		article.setRetrait(retrait);

		
		// *** TEMPORAIRE *** Création d'un utilisateur pour le mettre en tant que vendeur
//		Utilisateur u1 = new Utilisateur("Haste", "Desnoes", "Jérémie", "bloblogmail.com", "0695067182",
//				"Rue du moulin", "35170", "Rennes", "Kilokoko30");// A supprimer quand on pourras avoir l'utilisateur courant

		
		//Récuperation de l'utilisateur de session
		HttpSession session = request.getSession();
		Utilisateur userCourant = (Utilisateur) session.getAttribute("Utilisateur");

		article.setUtilisateur(userCourant);

		try { // A supprimer quand on pourras avoir l'utilisateur courant
			dao.insertArticle(article);
			retrait.setArticle(article);
			dao.insertRetrait(retrait);
			System.out.println("insert fati");
			//Envoie à la JSP un message de succès a retirer quand ça va rediriger vers une autre servlet
			response.sendRedirect("http://localhost:8080/trocEnchere/ServletListeEnchere");
		} catch (TrocEnchereException e) {
			
			//Envoie à la JSP la liste des erreurs.
			request.setAttribute("lstErreur", e.getListeCodesErreur());
		}
		
	}

}
