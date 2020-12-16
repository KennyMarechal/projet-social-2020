package modele;

import java.util.HashMap;
import java.util.List;

public class StatistiqueUtilisateur {
	
	private String nom;
	private List<HashMap<String, Integer>> listeStatistique;
	
	public StatistiqueUtilisateur() {}
	
	public StatistiqueUtilisateur(String nom, List<HashMap<String, Integer>> listeStatistique) {
		super();
		this.nom = nom;
		this.listeStatistique = listeStatistique;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<HashMap<String, Integer>> getListeStatistique() {
		return listeStatistique;
	}
	public void setListeStatistique(List<HashMap<String, Integer>> listeStatistique) {
		this.listeStatistique = listeStatistique;
	}
}