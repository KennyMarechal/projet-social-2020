package modele;

import java.util.List;

public class PageStatistique {
	
	private List<StatistiqueUtilisateur> listeStatistiqueUtilisateur;
	private int statistiquePersonelle;
	
	public PageStatistique() {}
	
	public PageStatistique(List<StatistiqueUtilisateur> listeStatistiqueUtilisateur, int statistiquePersonelle) {
		super();
		this.listeStatistiqueUtilisateur = listeStatistiqueUtilisateur;
		this.statistiquePersonelle = statistiquePersonelle;
	}
	
	public List<StatistiqueUtilisateur> getListeStatistiqueUtilisateur() {
		return listeStatistiqueUtilisateur;
	}
	public void setListeStatistiqueUtilisateur(List<StatistiqueUtilisateur> listeStatistiqueUtilisateur) {
		this.listeStatistiqueUtilisateur = listeStatistiqueUtilisateur;
	}
	public int getStatistiquePersonelle() {
		return statistiquePersonelle;
	}
	public void setStatistiquePersonelle(int statistiquePersonelle) {
		this.statistiquePersonelle = statistiquePersonelle;
	}
}