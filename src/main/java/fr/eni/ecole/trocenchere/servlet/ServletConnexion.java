package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.ALIBABA.TrocEnchereDAOImplSelect;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String username = "admin";
	private final String password = "password";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String username = request.getParameter("name");
	        String password = request.getParameter("password");
	        System.out.println("%%%%%");
	        
	        if(this.username.equals(username) && this.password.equals(password)) {
//	            HttpSession oldSession = request.getSession(false);
//	            if(oldSession != null) {
//	                oldSession.invalidate();
//	            }
	            HttpSession session = request.getSession();
	            TrocEnchereDAOImplSelect dao = new TrocEnchereDAOImplSelect();
	            Utilisateur user = null;
				try {
					user = dao.SelectUserById(20);
				} catch (TrocEnchereException e) {
					System.out.println(e.getListeCodesErreur());
				}
	            if (session != null) {
	            	session.setAttribute("Utilisateur", user);
	            }
//	            newSession.setMaxInactiveInterval(1*60);
	            Cookie message = new Cookie("message", "Bienvenue");
	            response.addCookie(message);
	            
//	            String messag = null;
//	            String sessionID = null;
//	            Cookie[] cookies = request.getCookies();
//	            if(cookies != null){
//	                for(Cookie cookie : cookies){
//	                    if(cookie.getName().equals("message")) messag = cookie.getValue();
//	                    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
//	            }
//	            }
	            
	            
	            
//	            System.out.println("message : " + messag);
//	            System.out.println("Session Id : " + sessionID);
	            
	            //response.sendRedirect("/WEB-INF/JspConnexionSucces.jsp");
	            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspConnexionSucces.jsp");
	            rd.include(request, response);
	        } else {
	            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/JspConnexion.jsp");
	            PrintWriter out = response.getWriter();
	            out.println("<font color=red>Either username or password is wrong.</font>");
	            rd.include(request, response);
	        }
	    }
	    
}
