package vue;

import controleur.Controleur;
import javafx.stage.Stage;

public class Fenetre extends Navigateur {
	
	@Override
	public void start(Stage stade) throws Exception {
		stade.setWidth(1280);
		stade.setHeight(760);
		stade.setTitle("Application de chat");
		stade.setScene(Controleur.selectionnerVuePrincipale()); // une vue est appliquer a la fenetre
		stade.show();
		this.stade = stade;				
	};
}