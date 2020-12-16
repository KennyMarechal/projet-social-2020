package donnee;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

@SuppressWarnings("deprecation")
public class XmlDAO {
	
	public void xmlDAO() {}
	
	@SuppressWarnings("resource")
	public static Document getXmlParUrl(String url, String baliseFermeture) {
		Document doc = null;
		try {
			URL urlDAO = new URL(url);
			InputStream flux = urlDAO.openConnection().getInputStream();
			Scanner lecteur = new Scanner(flux);
			
			lecteur.useDelimiter(baliseFermeture);
			String xml =  lecteur.next() + baliseFermeture;
			
			DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = constructeur.parse(new StringBufferInputStream(xml));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
}
