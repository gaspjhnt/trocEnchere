package fr.eni.ecole.trocenchere;


import java.util.ArrayList;
import java.util.List;

public class TrocEnchereExcepetion extends Exception{
	private static final long serialVersionUID = 1L;
	private List<String> listeErreur;
	
	public TrocEnchereExcepetion() {
		super();
		this.listeErreur=new ArrayList<>();
	}
	
	/**
	 * 
	 * @param code Code de l'erreur. Doit avoir un message associé dans un fichier properties.
	 */
	public void ajouterErreur(String message)
	{
		if(!this.listeErreur.contains(message))
		{
			this.listeErreur.add(message);
		}
	}
	
	public boolean hasErreurs()
	{
		return this.listeErreur.size()>0;
	}
	
	
	public List<String> getListeCodesErreur()
	{
		return this.listeErreur;
	}

}

