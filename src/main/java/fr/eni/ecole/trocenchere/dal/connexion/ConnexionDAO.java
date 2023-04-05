package fr.eni.ecole.trocenchere.dal.connexion;

import java.util.List;

import fr.eni.ecole.trocenchere.TrocEnchereException;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.bo.Enchere;
import fr.eni.ecole.trocenchere.bo.Utilisateur;

public interface ConnexionDAO {

	/**
	 * Methode pour récuperer une utilisateur a l'aide de ses informations de connexions
	 * @param pseudoOuMail
	 * @param mdp
	 * @return
	 * @throws TrocEnchereException
	 */
	Utilisateur selectMdpAndPseudo(String pseudoOuMail, String mdp) throws TrocEnchereException;
	
	/**
	 * Methode qui selectionne une enchere par un article dans la BDD
	 * @param article
	 * @return
	 * @throws TrocEnchereException
	 */
	public List<Enchere> selectEnchereByArticle(Article article) throws TrocEnchereException;
	

}
