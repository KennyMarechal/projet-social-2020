package donnee;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import modele.Message;


public class MessageDAO {
	
	public static final String URL_AJOUTER_MESSAGE = "https://social.tikenix.me/AjouterMessage.php";
	public static final String URL_LISTER_MESSAGE = "https://social.tikenix.me/ListeMesage.php";
	
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
			
			envoyeur.write("id=" + message.getId() 
							+ "&moment=" + message.getMoment()
							+ "&text=" + message.getText()
							+ "&salon_id=" + message.getSalon_id()
							+ "&utilisateur_id=" + message.getUtilisateur_id()
			);
			envoyeur.close();

			int codeReponse = connection.getResponseCode();
			InputStream fluxLecture = connection.getInputStream();
			Scanner lecteur = new Scanner(fluxLecture);
			String derniereBalise = "</action>";
			lecteur.useDelimiter(derniereBalise);
			xml = lecteur.next() + derniereBalise;
			lecteur.close();
			
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		decodeurXML.decoderReponseAction(xml);
	}
	
}