package modele;

import java.util.List;

public class PageStatistique {
	
	private List<StatistiqueUtilisateur> listeStatistiqueUtilisateur;
	private int heureMinimale;
	private int valeurGraphiqueMaximale;
	private int statistiquePersonelle;
	
	public PageStatistique() {}
	
	public PageStatistique(List<StatistiqueUtilisateur> listeStatistiqueUtilisateur, int heureMinimale, int valeurGraphiqueMaximale, int statistiquePersonelle) {
		super();
		this.listeStatistiqueUtilisateur = listeStatistiqueUtilisateur;
		this.heureMinimale = heureMinimale;
		this.valeurGraphiqueMaximale = valeurGraphiqueMaximale;
		this.statistiquePersonelle = statistiquePersonelle;
	}
	
	public List<StatistiqueUtilisateur> getListeStatistiqueUtilisateur() {
		return listeStatistiqueUtilisateur;
	}
	public void setListeStatistiqueUtilisateur(List<StatistiqueUtilisateur> listeStatistiqueUtilisateur) {
		this.listeStatistiqueUtilisateur = listeStatistiqueUtilisateur;
	}
	
	public int getHeureMinimale() {
		return heureMinimale;
	}

	public void setHeureMinimale(int heureMinimale) {
		this.heureMinimale = heureMinimale;
	}

	public int getValeurGraphiqueMaximale() {
		return valeurGraphiqueMaximale;
	}

	public void setValeurGraphiqueMaximale(int valeurGraphiqueMaximale) {
		this.valeurGraphiqueMaximale = valeurGraphiqueMaximale;
	}

	public int getStatistiquePersonelle() {
		return statistiquePersonelle;
	}
	public void setStatistiquePersonelle(int statistiquePersonelle) {
		this.statistiquePersonelle = statistiquePersonelle;
	}
}