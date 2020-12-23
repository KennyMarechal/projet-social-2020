package donnee;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class UtilisateurDAO
{
	private static final String URL_LISTER_UTILISATEURS = "https://social.tikenix.me/ListeUtilisateur.php";
	private static final String BALISE_FERMETURE = "</utilisateurs>";
	
	private static UtilisateurDAO instance;
	public static UtilisateurDAO getInstance() {
		if (instance == null)
			instance = new UtilisateurDAO();
		
		return instance;
	}

	public List<String> listerNomUtilisateurs()
	{
		Document document = XmlDAO.getXmlParUrl(URL_LISTER_UTILISATEURS, BALISE_FERMETURE);
		List<String> nomsUtilisateurs = new ArrayList<String>();
		if (document == null) return null;
						
		NodeList elementsUtilisateurs = document.getElementsByTagName("utilisateur");
		for (int i=0; i < elementsUtilisateurs.getLength(); i++) 
		{
			Node elementUtilisateur = elementsUtilisateurs.item(i);
			if (elementUtilisateur.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element champElement  = (Element)elementUtilisateur;  
				nomsUtilisateurs.add(champElement.getElementsByTagName("titre").item(0).getTextContent());
			}
		}	
		
		return nomsUtilisateurs;
	}
}
