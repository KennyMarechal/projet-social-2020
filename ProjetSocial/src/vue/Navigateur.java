package vue;

import com.sun.media.jfxmedia.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;

public abstract class Navigateur extends Application{ // Application de javafx est une fenetre
	
	protected Stage stade;
		
	private static Navigateur instance = null;
	public static Navigateur getInstance() {return instance;}	
	
	protected Navigateur()
	{
		//TODO instance de toutes les vues et la navigation
		instance = this;
		Logger.setLevel(Logger.INFO);
		VueChatPrive.getInstance().activerControles();
		VueSalons.getInstance().activerControles();
		VueStatistiques.getInstance().activerControles();
		VueParametre.getInstance().activerControles();
	}
	
	public void afficherVue(Vue vue)
	{
		stade.setScene(vue);
		stade.show();
	}
}
