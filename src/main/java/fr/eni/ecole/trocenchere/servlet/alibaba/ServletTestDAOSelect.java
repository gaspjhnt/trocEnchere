package fr.eni.ecole.trocenchere.servlet.alibaba;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Categorie;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.ALIBABA.TrocEnchereDAOImplSelect;

/**
 * Servlet implementation class ServletTestDAOSelect
 */
@WebServlet("/ServletTestDAOSelect")
public class ServletTestDAOSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestDAOSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TrocEnchereDAOImplSelect dao = new TrocEnchereDAOImplSelect();
		try {
			Utilisateur test = dao.SelectUserById(4);
			System.out.println(test);
//			System.out.println(test + "\n\n\n\n");
//			System.out.println(dao.selectArticleByUser(test) + "\n\n\n\n");
//			
//			Categorie categorie = dao.selectCategorieById(3);			
//			System.out.println(dao.selectArticleByCate(categorie) + "\n\n\n\n");
//			
//			Article article = dao.selectArticleByCate(categorie).get(1);
//			
//			System.out.println(dao.selectArticleByDateDebut(LocalDate.of(2023, 3, 1)) + "\n\n\n\n");
//			
//			System.out.println(dao.selectEnchereByArticle(article) + "\n\n\n\n\n");
//			System.out.println(dao.selectEnchereByUser(test) + "\n\n\n\n\n");
//			
//			System.out.println(dao.selectAllCategorie());
//			
//			
//			System.out.println(dao.selectRetraitByArticle(article));
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
