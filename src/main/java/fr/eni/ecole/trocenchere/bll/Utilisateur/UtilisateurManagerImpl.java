package fr.eni.ecole.trocenchere.bll.Utilisateur;

import java.util.regex.Pattern;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Utilisateur;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateur;
import fr.eni.ecole.trocenchere.dal.DAOUtilisateurFactory;

public class UtilisateurManagerImpl implements UtilisateurManager{

	private DAOUtilisateur dao = DAOUtilisateurFactory.getDAOUtilisateur();
	
	@Override
	public Utilisateur selectById(int id) throws TrocEnchereException {
		// TODO Auto-generated method stub
		return dao.selectById(id);
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws TrocEnchereException {
		
		String pseudo = utilisateur.getPseudo();
		String nom = utilisateur.getNom();
		String prenom = utilisateur.getPrenom();
		String email = utilisateur.getEmail();
		String numTel = utilisateur.getTelephone();
		String rue = utilisateur.getRue();
		String codePostal = utilisateur.getCodePostal();
		String ville = utilisateur.getVille();
		String mdp = utilisateur.getMotDePasse();
		
		TrocEnchereException exception = new TrocEnchereException();
		Utilisateur monUtilisateur = new Utilisateur(pseudo,nom,prenom,email,numTel,rue,codePostal,ville,mdp);
		monUtilisateur.setNoUtilisateur(utilisateur.getNoUtilisateur());
		
		this.validerPseudo(monUtilisateur, exception);
		this.validerNom(monUtilisateur, exception);
		this.validerPrenom(monUtilisateur, exception);
		this.validerEmail(monUtilisateur, exception);
		this.validerNumTel(monUtilisateur, exception);
		this.validerRue(monUtilisateur, exception);
		this.validerCodePostal(monUtilisateur, exception);
		this.validerVille(monUtilisateur, exception);
		
		if(!exception.hasErreurs()) {
			dao.updateUtilisateur(monUtilisateur);
		} else {
			throw exception;
		}
	}

	@Override
	public void deleteUtilisateur(int noUtilisateur) throws TrocEnchereException {
		dao.deleteUtilisateur(noUtilisateur);
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
					for(String mesPseudo : dao.getAllPseudo()) {
						if(utilisateur.getPseudo().equals(mesPseudo) && utilisateur.getNoUtilisateur() != dao.getUtilisateurByPseudo(mesPseudo).getNoUtilisateur()) {
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
				for(String mesEmail : dao.getAllEmail()) {
					if(utilisateur.getEmail().equals(mesEmail) && utilisateur.getNoUtilisateur() != dao.getUtilisateurByMail(mesEmail).getNoUtilisateur()) {
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
	
}
