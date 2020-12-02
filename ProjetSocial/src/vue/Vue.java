package vue;

import java.io.IOException;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Vue extends Scene {
	
	protected static FXMLLoader parseur = null;
	protected Controleur controleur = null;
	
	public Vue(String fxml)
	{
		super(parser(fxml, null),995,617);
		this.controleur = null;
	}
	
	public Vue(String fxml, Controleur controleur)
	{
		super(parser(fxml, controleur),995,617);
		this.controleur = controleur;
	}
	
	public static Parent parser(String fxml, Controleur controleur)
	{
		parseur = new FXMLLoader();
		parseur.setLocation(VueAcceuil.class.getResource(fxml)); 
		if(null != controleur) parseur.setController(controleur);
		try {
			return parseur.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("fin parser");
		return null;
	}
	
	public void activerControles()
	{	
		Logger.logMsg(Logger.INFO, "activerControles()");
	}	
}