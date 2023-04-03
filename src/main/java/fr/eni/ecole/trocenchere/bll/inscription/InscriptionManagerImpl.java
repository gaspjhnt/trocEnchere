package fr.eni.ecole.trocenchere.bll.inscription;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.inscription.InscriptionDAO;
import fr.eni.ecole.trocenchere.dal.inscription.InscriptionDAOFactory;

public class InscriptionManagerImpl implements InscriptionManager {
	
	private InscriptionDAO inscriptionDAO = InscriptionDAOFactory.getInscriptionDAO();

	@Override
	public Utilisateur ajouter(String pseudo, String nom, String prenom, String email, String numTel, String rue,
			String codePostal, String ville, String mdp) throws TrocEnchereException {
			
		TrocEnchereException exception = new TrocEnchereException();
		Utilisateur utilisateur = new Utilisateur(pseudo,nom,prenom,email,numTel,rue,codePostal,ville,mdp);
		
		this.validerPseudo(utilisateur, exception);
		this.validerNom(utilisateur, exception);
		this.validerPrenom(utilisateur, exception);
		this.validerEmail(utilisateur, exception);
		this.validerNumTel(utilisateur, exception);
		this.validerRue(utilisateur, exception);
		this.validerCodePostal(utilisateur, exception);
		this.validerVille(utilisateur, exception);
		this.validerMdp(utilisateur, exception);
		
		if(!exception.hasErreurs()) {
			this.inscriptionDAO.insertUtilisateur(utilisateur);
		} else {
			throw exception;
		}
		
		return utilisateur;
	}
	//Méthode pour valider le pseudo (on ajoute des conditions pour valider le pseudo)
	private void validerPseudo(Utilisateur utilisateur, TrocEnchereException exception) {
			
			//Si le pseudo utilisateur est null, exception.
			if (utilisateur.getPseudo() == null) {
				exception.ajouterErreur("Vous devez inscrire un pseudo !");
			}
			
			//Si le pseudo utilisateur a une taille de caractères inférieur à 2, exception.
			if (utilisateur.getPseudo().length()<2) {
				exception.ajouterErreur("Pseudo trop court !");
			}
			
				//Si le pseudo utilisateur a une taille de caractères supérieur à 25, exception.
			if (utilisateur.getPseudo().length()>25) {
				exception.ajouterErreur("Pseudo trop long ! Moins de 25 caractères svp");
			} 
			try {
				for(String mesPseudo : inscriptionDAO.getAllPseudo()) {
					if(utilisateur.getPseudo().equals(mesPseudo)) {
						exception.ajouterErreur("Le pseudo existe déjà !");
					}
				}
				} catch (TrocEnchereException e) {
					e.printStackTrace();
				}
	}
	
	//Méthode pour valider le nom (on ajoute des conditions pour valider le nom)
	private void validerNom(Utilisateur utilisateur, TrocEnchereException exception) {
			//Si le nom utilisateur est null, exception.
		if (utilisateur.getNom() == null) {
			exception.ajouterErreur("Vous devez ajouter un nom !");
		}
			//Si le nom de l'utilisateur a une taille de caractères inférieur à 2, exception.
		if (utilisateur.getNom().length()<2) {
			exception.ajouterErreur("Nom trop court ! Plus de 2 caractères svp !");
		}	
			//Si le nom de l'utilisateur a une taille de caractères supérieur à 25, exception.
		if (utilisateur.getNom().length()>25) {
			exception.ajouterErreur("Nom trop long ! Moins de 25 caractères svp");
		}
		if(isDigit(utilisateur.getNom())) {
			exception.ajouterErreur("Pas de chiffres dans votre nom stop troll svp");
		}
	}
	
	private void validerPrenom(Utilisateur utilisateur, TrocEnchereException exception) {
		//Si le prénom est null, exception.
		if(utilisateur.getPrenom() == null) {
			exception.ajouterErreur("Vous devez ajouter un prénom !");
		}
		//Si le prénom a une taille de caractères inférieur à 2, exception.
		if (utilisateur.getPrenom().length()<2) {
			exception.ajouterErreur("Prénom trop court ! Plus de deux caractères svp");
		}
		//Si le prénom a une taille de caractères supérieur à 25, exception.
		if (utilisateur.getPrenom().length()>25) { 
			exception.ajouterErreur("Prenom trop long ! Moins de 25 caractères svp");
		}
	}
	
	private void validerEmail(Utilisateur utilisateur, TrocEnchereException exception) {
		//On vérifie que l'email n'est pas null
		if(utilisateur.getEmail() == null) {
			exception.ajouterErreur("Vous devez insérer une adresse email !");
		}
		//On vérifie que l'email possède bien un @
		if(!utilisateur.getEmail().contains("@")) {
			exception.ajouterErreur("Vous devez entrer une adresse mail valide, @ manquant");
		}
		//On vérifie que l'email possède bien un .
		if(!utilisateur.getEmail().contains(".")){
			exception.ajouterErreur("Vous devez entrer une adresse mail valide, . manquant");
		}
		//On vérifie que l'email fait moins de 10 caractères
		if(utilisateur.getEmail().length()<10) {
			exception.ajouterErreur("Vous devez entrer une adresse mail valide, . manquant");
		}
		//On vérifie que l'email fait moins de 75 caractères
		if(utilisateur.getEmail().length()>75) {
			exception.ajouterErreur("Condition bll non respectée dans l'insertion de l'email !");
		}
		try {
			for(String mesEmail : inscriptionDAO.getAllEmail()) {
				if(utilisateur.getEmail().equals(mesEmail)) {
					exception.ajouterErreur("Le mail existe déjà !");
				}
			}
			} catch (TrocEnchereException e) {
				e.printStackTrace();
			}
	}
	
	//Méthode pour vérifier si il a bien uniquement des numéros dans un String.
	private boolean isDigit(String numtel) {
		try {
			Integer.parseInt(numtel);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	private void validerNumTel(Utilisateur utilisateur, TrocEnchereException exception) {
		//On vérifie que le numéro de téléphone n'est pas null
		if(utilisateur.getTelephone() == null ) {
			exception.ajouterErreur("Le numéro de téléphone doit être renseigné");
		}
		//On vérifie que le numéro de téléphone fait bien 10 caractères.
		if (!(utilisateur.getTelephone().length()== 10)) {
			exception.ajouterErreur("Le numéro de téléphone doit faire 10 chiffres");
		}
		//On vérifie que le numéro de téléphone est bien composé uniquement de chiffres.
		if(!isDigit(utilisateur.getTelephone())) {
			exception.ajouterErreur("Le numéro de téléphone doit être composé uniquement de chiffres");
		}
	}
	
	private void validerRue(Utilisateur utilisateur, TrocEnchereException exception) {
		//On vérifie que la rue n'est pas null
		if(utilisateur.getRue() == null) {
			exception.ajouterErreur("La rue doit être renseignée !");
		}
	}
	
	private void validerCodePostal(Utilisateur utilisateur, TrocEnchereException exception) {
		//On vérifie que le code postal n'est pas null
		if(utilisateur.getCodePostal()== null) {
			exception.ajouterErreur("Le code postal doit être renseigné !");
		}
		if(!(utilisateur.getCodePostal().length()==5)) {
			exception.ajouterErreur("Le code postal doit faire 5 chiffres !");
		}
		if (!isDigit(utilisateur.getCodePostal())) {
			exception.ajouterErreur("Le code postal doit être composé uniquement de chiffres");
		}
	}
	
	private void validerVille (Utilisateur utilisateur, TrocEnchereException exception) {
		//On vérifie que la ville n'est pas null
		if(utilisateur.getVille() == null) {
			exception.ajouterErreur("La rue doit être renseignée !");
		}
	}
	
	private boolean isValidMdp(String mdp) {
		final String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
		final Pattern mdpPattern = Pattern.compile(regex);
			if(mdpPattern.matcher(mdp).matches()) {
				return true;	
		}
			else {
				return false;
			}
		
	}
	
	private void validerMdp (Utilisateur utilisateur, TrocEnchereException exception) {
		//On vérifie que le mot de passe n'est pas null
		if(utilisateur.getMotDePasse()==null) {
			exception.ajouterErreur("Le mot de passe doit être renseigné !");
		}

		if(!isValidMdp(utilisateur.getMotDePasse())) {
			exception.ajouterErreur("Le mot de passe doit correspondre aux critères ! Une majuscule, une minuscule,"
					+ " un caractère spécial et au moins 8 caractères !");
		
		}
	}
	

}
