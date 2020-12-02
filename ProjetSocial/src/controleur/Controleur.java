package controleur;

import com.sun.media.jfxmedia.logging.Logger;

import vue.Vue;
import vue.VueAcceuil;

public class Controleur {
	
	public Controleur() {
		Logger.logMsg(Logger.INFO, "new Controleur()");
	}
	
	public static Vue selectionnerVuePrincipale() { 
		/*La vue principale c'est la vue qui comporte les listes*/
		return VueAcceuil.getInstance();
	}
}
