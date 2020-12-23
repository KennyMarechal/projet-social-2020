package donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import controleur.Controleur;
import modele.PageStatistique;
import modele.Salon;
import modele.StatistiqueUtilisateur;

public class StatistiqueDAO {
	private static final String URL = "https://dvmax.xyz/social/statistique.php?utilisateur=" + Controleur.ID_UTILISATEUR;
	private static final String BALISE_FERMETURE = "</statistiques>";
	
	private static final String ID = "id";
	private static final String TITRE = "titre";
	
	private static StatistiqueDAO instance;
	
	private PageStatistique pageStatistique;

	public void rafraichir() {
		Document document = XmlDAO.getXmlParUrl(URL, BALISE_FERMETURE);
		pageStatistique = transformerDocumentEnPageStatistique(document);
	}
	
	public PageStatistique getPage() {
		if (pageStatistique == null)
			this.rafraichir();
		return pageStatistique;
	}
	
	private PageStatistique transformerDocumentEnPageStatistique(Document document){
		if (document == null) return null;
		
		List<StatistiqueUtilisateur> listeStatistiqueUtilisateur = new ArrayList<StatistiqueUtilisateur>();
		int heureMinimale = Integer.parseInt(document.getElementsByTagName("heureMinimale").item(0).getTextContent());
		int valeurGraphiqueMaximale = Integer.parseInt(document.getElementsByTagName("valeurGraphiqueMaximale").item(0).getTextContent());
		int statistiquePersonelle = Integer.parseInt(document.getElementsByTagName("statistiqueMessage").item(0).getTextContent());
		
		NodeList listeUtilisateurs = document.getElementsByTagName("listeStatistiqueUtilisateur").item(0).getChildNodes();
		for (int i=0; i < listeUtilisateurs.getLength(); i++) {
			Node utilisateur = listeUtilisateurs.item(i);
			if (utilisateur.getNodeType() == Node.ELEMENT_NODE) {
				listeStatistiqueUtilisateur.add(recupererStatistiqueUtilisateur(utilisateur));
			}
		}
		return new PageStatistique(listeStatistiqueUtilisateur, heureMinimale, valeurGraphiqueMaximale, statistiquePersonelle);
	}
	
	private StatistiqueUtilisateur recupererStatistiqueUtilisateur(Node utilisateur) {
		if (utilisateur.getNodeType() == Node.ELEMENT_NODE) {
			Element elementUtilisateur = (Element) utilisateur;
			
			String nom = elementUtilisateur.getElementsByTagName("nom").item(0).getTextContent();
			NodeList listeNodeStatistique = elementUtilisateur.getElementsByTagName("listeStatistiqueGraphique").item(0).getChildNodes();
			List<HashMap<String, Integer>> listeStatistique = new ArrayList<HashMap<String, Integer>>();
			
			for (int j=0; j < listeNodeStatistique.getLength(); j++) {
				Node nodeStatistique = listeNodeStatistique.item(j);
				if (nodeStatistique.getNodeType() == Node.ELEMENT_NODE) {
					Element elementstatistique = (Element)nodeStatistique;
					if (elementstatistique.getTagName() == "statistiqueGraphique") {
						HashMap<String, Integer> statistique = new HashMap<String, Integer>();
						statistique.put("heure", Integer.parseInt(elementstatistique.getElementsByTagName("heure").item(0).getTextContent()));
						statistique.put("frequence", Integer.parseInt(elementstatistique.getElementsByTagName("frequence").item(0).getTextContent()));
						listeStatistique.add(statistique);
					}
				}
			}
			
			return new StatistiqueUtilisateur(nom, listeStatistique);
		} else { return null;}
	}
	
	public static StatistiqueDAO getInstance() {
		if (instance == null)
			instance = new StatistiqueDAO();
		
		return instance;
	}
}