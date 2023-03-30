package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletSession
 */
@WebServlet("/ServletSession")
public class ServletSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("pseudo", "zboubi");
		session.setAttribute("nom", "le zboub");
		session.setAttribute("prenom", "zboubax");
		session.setAttribute("email", "zboubi@outlook.fr");
		session.setAttribute("telephone", "0294585858");
		session.setAttribute("rue", "rue de la goutte");
		session.setAttribute("codepostal", "35000");
		session.setAttribute("ville", "Rennes");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspProfil.jsp");

		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
