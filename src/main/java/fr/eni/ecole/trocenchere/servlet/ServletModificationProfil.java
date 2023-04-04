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
import fr.eni.ecole.trocenchere.bll.Utilisateur.UtilisateurManagerImpl;
import fr.eni.ecole.trocenchere.bll.inscription.InscriptionManagerImpl;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletModificationProfil
 */
@WebServlet("/ServletModificationProfil")
public class ServletModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletModificationProfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspModifProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			String MdpActuel = sha256(request.getParameter("mdpActuel"));
			String mdp = request.getParameter("nouveauMdp");
			String mdp1 = request.getParameter("confirmMdp");

			HttpSession session = request.getSession();

			UtilisateurManagerImpl dao = new UtilisateurManagerImpl();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("Utilisateur");

			if (utilisateurSession.getMotDePasse() != MdpActuel) {
				TrocEnchereException exception1 = new TrocEnchereException();
				exception1.ajouterErreur("Le mot de passe ne correspond pas au mot de passe actuel");

				throw exception1;
				
			} else if (!isEgalMdp(mdp, mdp1)) {
				TrocEnchereException exception = new TrocEnchereException();
				exception.ajouterErreur("Les mots de passe sont différents");

				throw exception;
			}

			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
					mdp);
			dao.updateUtilisateur(utilisateur);

			request.setAttribute("user", utilisateur);

		} catch (TrocEnchereException e) {
			request.setAttribute("lstErreurs", e.getListeCodesErreur());

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JspProfil.jsp");
			rd.forward(request, response);
		}

	}

	private boolean isEgalMdp(String mdp, String mdp1) {
		if (mdp.equals(mdp1)) {
			return true;
		} else {
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
