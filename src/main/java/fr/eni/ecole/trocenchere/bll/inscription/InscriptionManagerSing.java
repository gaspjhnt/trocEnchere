package fr.eni.ecole.trocenchere.bll.inscription;

public class InscriptionManagerSing {
	
	public static InscriptionManagerImpl instance;
	
	public static InscriptionManagerImpl getInstance() {
		if (instance == null) {
			instance = new InscriptionManagerImpl();
		}
		return instance;
	}
}
