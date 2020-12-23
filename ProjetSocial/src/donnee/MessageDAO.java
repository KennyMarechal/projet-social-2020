package donnee;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modele.Message;


public class MessageDAO {
	
	private static final String URL_AJOUTER_MESSAGE = "https://social.tikenix.me/AjouterMessage.php";
	private static final String URL_LISTER_MESSAGE = "https://social.tikenix.me/ListeMesage.php";

	private static final String BALISE_FERMETURE = "</messages>";
	
	private static MessageDAO instance;
	public static MessageDAO getInstance() {
		if (instance == null)
			instance = new MessageDAO();
		
		return instance;
	}

	XmlDAO decodeurXML = new XmlDAO();
	
	public void envoyerMessage(Message message)
	{		
		String xml = "";
		try {
						
			URL urlAjouterMessage = new URL(URL_AJOUTER_MESSAGE);
			HttpURLConnection connection = (HttpURLConnection) urlAjouterMessage.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			
			OutputStream fluxEcriture = connection.getOutputStream();
			OutputStreamWriter envoyeur = new OutputStreamWriter(fluxEcriture, "UTF-8");	
			
			envoyeur.write("&text=" + message.getText()
							+ "&salon_id=" + message.getSalon_id()
							+ "&utilisateur_id=" + message.getUtilisateur_id()
			);
			envoyeur.close();

			int codeReponse = connection.getResponseCode();
			InputStream fluxLecture = connection.getInputStream();
			Scanner lecteur = new Scanner(fluxLecture);
			lecteur.useDelimiter(BALISE_FERMETURE);
			xml = lecteur.next() + BALISE_FERMETURE;
			lecteur.close();
			
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		decodeurXML.decoderReponseAction(xml);
	}

	public List<Message> listerMessagesParSalon(int idSalon)
	{
		Document document = XmlDAO.getXmlParUrl(URL_LISTER_MESSAGE, BALISE_FERMETURE);		
		if (document == null) return null;
		
		List<Message> listeMessages = new ArrayList<Message>();
				
		NodeList elementsMessage = document.getElementsByTagName("message");
		for (int i=0; i < elementsMessage.getLength(); i++) 
		{
			Node elementMessage = elementsMessage.item(i);
			if (elementMessage.getNodeType() == Node.ELEMENT_NODE) 
			{
				Message message = new Message();
				Element champElement  = (Element)elementMessage;  

				String valeurIdSalon = champElement.getElementsByTagName("salon_id").item(0).getTextContent();
				if(valeurIdSalon.equals("") || Integer.parseInt(valeurIdSalon) != idSalon)
					continue;
				
				String champId = champElement.getElementsByTagName("id").item(0).getTextContent();
				String champMoment = champElement.getElementsByTagName("moment").item(0).getTextContent();				
				String champText = champElement.getElementsByTagName("text").item(0).getTextContent();
				String champIdSalon = champElement.getElementsByTagName("salon_id").item(0).getTextContent();
				String champIdUtilisateur = champElement.getElementsByTagName("utilisateur_id").item(0).getTextContent();

				//Conversion du timestamp PostgreSQL pour le sql.Timestamp
				String modele = "yyyy-MM-dd HH:mm:ss.SS";
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(modele);
				LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(champMoment.substring(0, 22)));
				
				message.setId(Integer.parseInt(champId));
				message.setMoment(Timestamp.valueOf(localDateTime));
				message.setText(champText);
				message.setSalon_id(Integer.parseInt(champIdSalon));
				message.setUtilisateur_id(Integer.parseInt(champIdUtilisateur));
				
				//System.out.println(message.getId());
				listeMessages.add(message);
			}
		}	
		return listeMessages;
	}
}