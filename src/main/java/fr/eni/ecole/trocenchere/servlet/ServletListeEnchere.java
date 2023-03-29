package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.lstEnchere.LstEnchereManagerSing;
import fr.eni.ecole.trocenchere.bll.lstEnchere.lstEnchereManager;

/**
 * Servlet implementation class ServletListeEnchere
 */
@WebServlet("/ServletListeEnchere")
public class ServletListeEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListeEnchere() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		lstEnchereManager dao = LstEnchereManagerSing.getInstance();
		
		try {
			System.out.println(dao.getAllArticlesByDate(LocalDate.now()));
			request.setAttribute("article", dao.getAllArticlesByDate(LocalDate.now()));
		} catch (TrocEnchereException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspListeEnchere.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
