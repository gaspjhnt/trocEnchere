package fr.eni.ecole.trocenchere.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bll.inscription.InscriptionManagerImpl;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		try {
			String pseudo= request.getParameter("pseudo");
			String nom= request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			String mdp = request.getParameter("mdp");
			String mdp1 = request.getParameter("confirmMdp");
			
			InscriptionManagerImpl imp = new InscriptionManagerImpl();
			if(!isEgalMdp(mdp, mdp1)) {
				TrocEnchereException exception = new TrocEnchereException();
				exception.ajouterErreur("Les mots de passe sont différents");
				throw exception;
			}
			Utilisateur utilisateur = imp.ajouter(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
			request.setAttribute("user", utilisateur);
			
			HttpSession session = request.getSession();	            
			if (session != null) {
				// on set l'interval d'innactivié a 30min
				session.setMaxInactiveInterval(1800);
				//On ajoute l'utilisateur a la session
		    	session.setAttribute("Utilisateur", utilisateur);
		    	session.setAttribute("mdp", sha256(mdp));
		    }
			response.sendRedirect("http://localhost:8080/trocEnchere/ServletListeEnchere");
		
		} catch(TrocEnchereException e) {
			request.setAttribute("lstErreurs", e.getListeCodesErreur());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
			rd.forward(request, response);
		}
		
	}
	
	private boolean isEgalMdp(String mdp, String mdp1) {
		if(mdp.equals(mdp1)) {
			return true;
		}
		else {
			return false;
		}
	}
	private static String sha256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
