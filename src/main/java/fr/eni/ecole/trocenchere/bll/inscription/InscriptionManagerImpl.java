package fr.eni.ecole.trocenchere.bll.inscription;

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
		
		return null;
	}
	//Méthode pour valider le pseudo (on ajoute des conditions pour valider le pseudo)
	private void validerPseudo(Utilisateur utilisateur, TrocEnchereException exception) {
			
			//Si le pseudo utilisateur est null, exception.
			if (utilisateur.getPseudo() == null) {
				exception.ajouterErreur("Vous devez inscrire un pseudo !");
			}
			
			//Si le pseudo utilisateur a une taille de caractères inférieur à 2, exception.
			else if (utilisateur.getPseudo().length()<2) {
				exception.ajouterErreur("Pseudo trop court !");
			}
			
				//Si le pseudo utilisateur a une taille de caractères supérieur à 25, exception.
			else if (utilisateur.getPseudo().length()>25) {
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
				
			{
				
			}
	}
	//Méthode pour valider le nom (on ajoute des conditions pour valider le nom)
	private void validerNom(Utilisateur utilisateur, TrocEnchereException exception) {
			//Si le nom utilisateur est null, exception.
		if (utilisateur.getNom() == null) {
			exception.ajouterErreur("Vous devez ajouter un nom !");
		}
			//Si le nom de l'utilisateur a une taille de caractères inférieur à 2, exception.
		else if (utilisateur.getPseudo().length()<2) {
			exception.ajouterErreur("Nom trop court ! Plus de 2 caractères svp !");
		}
		
			//Si le nom de l'utilisateur a une taille de caractères supérieur à 25, exception.
		else if (utilisateur.getPseudo().length()>25) {
			exception.ajouterErreur("Nom trop long ! Moins de 25 caractères svp");
		}
	}
	
	private void validerPrenom(Utilisateur utilisateur, TrocEnchereException exception) {
		//Si le prénom est null, exception.
		if(utilisateur.getPrenom() == null) {
			exception.ajouterErreur("Vous devez ajouter un prénom !");
		}
		//Si le prénom a une taille de caractères inférieur à 2, exception.
		else if (utilisateur.getPseudo().length()<2) {
			exception.ajouterErreur("Prénom trop court ! Plus de deux caractères svp");
		}
		//Si le prénom a une taille de caractères supérieur à 25, exception.
		else if (utilisateur.getPseudo().length()>25) { 
			exception.ajouterErreur("Prenom trop long ! Moins de 25 caractères svp");
		}
	}
	
	private void validerEmail(Utilisateur utilisateur, TrocEnchereException exception) {
		//On vérifie que l'email n'est pas nul
		if(utilisateur.getEmail() == null) {
			exception.ajouterErreur("Vous devez insérer une adresse email !");
		}
		//On vérifie que l'email possède bien un @
		else if(!utilisateur.getEmail().contains("@")) {
			exception.ajouterErreur("Vous devez entrer une adresse mail valide, @ manquant");
		}
		//On vérifie que l'email possède bien un .
		else if(!utilisateur.getEmail().contains(".")){
			exception.ajouterErreur("Vous devez entrer une adresse mail valide, . manquant");
		}
		//On vérifie que l'email fait moins de 10 caractères
		else if(utilisateur.getEmail().length()<10) {
			exception.ajouterErreur("Vous devez entrer une adresse mail valide, . manquant");
		}
		//On vérifie que l'email fait moins de 75 caractères
		else if(utilisateur.getEmail().length()>75) {
			exception.ajouterErreur("Condition bll non respectée dans l'insertion de l'email !");
		}
	}
	
	private void validerNumTel(Utilisateur utilisateur, TrocEnchereException exception) {
		if(utilisateur.getTelephone() == null ) {
			exception.ajouterErreur("Le numéro de téléphone doit être renseigné");
		}
		else if (!(utilisateur.getTelephone().length()== 10)) {
			exception.ajouterErreur("Le numéro de téléphone doit être renseigné");
		}
	}
	
	private void validerRue(Utilisateur utilisateur, TrocEnchereException exception) {
		if(utilisateur.getRue() == null) {
			exception.ajouterErreur("La rue doit être renseignée !");
		}
	}
	
	private void validerCodePostal(Utilisateur utilisateur, TrocEnchereException exception) {
		if(utilisateur.getCodePostal()== null) {
			exception.ajouterErreur("Le code postal doit être renseigné !");
		}
	}
	
	private void validerVille (Utilisateur utilisateur, TrocEnchereException exception) {
		if(utilisateur.getRue() == null) {
			exception.ajouterErreur("La rue doit être renseignée !");
		}
	}
	
	private void validerMdp (Utilisateur utilisateur, TrocEnchereException exception) {
		if(utilisateur.getMotDePasse()==null) {
			exception.ajouterErreur("Le mot de passe doit être renseigné !");
		}
	}

}
