package donnee;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modele.Salon;

public class SalonDAO {
	private static final String URL = "https://social.tikenix.me/ListeSalon.php";
	private static final String BALISE_FERMETURE = "</salons>";
	
	private static final String ID = "id";
	private static final String TITRE = "titre";
	
	private static SalonDAO instance;
	
	private List<Salon> listeSalons;

	public void rafraichir() {
		Document document = XmlDAO.getXmlParUrl(URL, BALISE_FERMETURE);
		listeSalons = transformerDocumentEnListeSalon(document);
	}
	
	public List<Salon> getListe() {
		if (listeSalons == null)
			this.rafraichir();
		return listeSalons;
	}
	
	private List<Salon> transformerDocumentEnListeSalon(Document document){
		if (document == null) return null;
		
		List<Salon> liste = new ArrayList<Salon>();
		
		NodeList elements = document.getElementsByTagName("salon");
		for (int i=0; i < elements.getLength(); i++) {
			Node element = elements.item(i);
			
			if (element.getNodeType() == Node.ELEMENT_NODE) {
				Salon salon = new Salon();
				NodeList champs = element.getChildNodes();
				for (int j=0; j < champs.getLength(); j++) {
					Node champ = champs.item(j);
					if (champ.getNodeType() == Node.ELEMENT_NODE) {
						Element champElement = (Element)champ;
						
						switch (champElement.getTagName()) {
							case ID : 
								salon.setId(Integer.parseInt(champElement.getTextContent()));
								break;
							case TITRE :
								salon.setTitre(champElement.getTextContent());
								break;
						}
					}
				}
				
				liste.add(salon);
			}
		}	
		return liste;
	}
	
	public static SalonDAO getInstance() {
		if (instance == null)
			instance = new SalonDAO();
		
		return instance;
	}
}
