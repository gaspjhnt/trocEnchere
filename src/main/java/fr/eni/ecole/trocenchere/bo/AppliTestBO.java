package fr.eni.ecole.trocenchere.bo;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class AppliTestBO {

	public static void main(String[] args) {
		Retrait retrait = new Retrait("rue de la motte","35000","rennes");
		Categorie catInfo = new Categorie("Informatique");
		Article article = new Article("Ordinateur gamer","magnifique pc", LocalDate.now(),LocalDate.now(), 100, catInfo, retrait);
		Article article2 = new Article("Ordinateur","jolie pc", LocalDate.of(2022, Month.APRIL, 10),LocalDate.of(2022, Month.APRIL, 15), 100, catInfo, retrait);
		
		System.out.println("Nous avons deux articles : "+ article.toString());
		System.out.println(article2.toString());
		
		List<Article> lstArticle = new ArrayList<>();
		List<Enchere> lstEnchere = new ArrayList<>();
		
		lstArticle.add(article);
		lstArticle.add(article2);
		
		System.out.println("Nous avons donc une liste d'article : ");
		for(Article current: lstArticle) {
			System.out.println(current);
		}
		
		Utilisateur acheteur = new Utilisateur("titi l'acheteur", "l'acheteur", "titi", "titijachete@hotmail.fr", "06XXXXXXXX", "rue", "35000", "Rennes", "motdepasse");
		Utilisateur vendeur = new Utilisateur("titi le vendeur", "le vendeur", "titi", "titijevends@hotmail.fr", "06XXXXXXXX", "rue", "35000", "Rennes", "motdepasse", false,lstEnchere, lstArticle);
		
		System.out.println("Nous avons deux utilisateurs : "+ acheteur.toString());
		System.out.println(vendeur.toString());
		
		Enchere enchere = new Enchere(LocalDate.of(2022,Month.APRIL,10),100,acheteur,article);
		Enchere enchere2 = new Enchere(LocalDate.of(2022,Month.APRIL,10),100,acheteur,article2);
		
		System.out.println("Nous avons deux enchères : ");
		System.out.println(enchere.toString());
		System.out.println(enchere2.toString());
		
		System.out.println("Nous avons donc une liste d'enchères : ");
		
		lstEnchere.add(enchere);
		lstEnchere.add(enchere2);
		for(Enchere current: lstEnchere) {
			System.out.println(current);
		}
		
		
		System.out.println("Les articles pourront être retirés à l'adresse suivante : "+ retrait);
		
		
	}
}
