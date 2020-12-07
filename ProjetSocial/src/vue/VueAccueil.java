package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class VueAccueil extends Vue{

	public static VueAccueil instance;
	public static VueAccueil getInstance() {if(null == instance)instance = new VueAccueil(); return instance;}
		
	protected Controleur controleur;
	
	private VueAccueil () {
		super("vue_accueil.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueAcceuil()");
	}
	
	public Controleur getControleur() {return this.controleur;}
	
	public void activerControles()
	{
		
		super.activerControles();
		
		/*Button actionListeSalon = (Button) lookup("#btn-salons");
		actionListeSalon.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Salons Activer");
            	VueSalons.getInstance().getControleur().actionOuvrirListeSalons(VueSalons.getInstance());
            }
        });
		
		Button actionChatPrive = (Button) lookup("#btn-chat");
		actionChatPrive.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton ChatPriver activer");
            	VueChatPrive.getInstance().getControleur().actionOuvrirChatPrive(VueChatPrive.getInstance());
            }
        });
		
		Button actionParametre = (Button) lookup("#btn-reglage");
		actionParametre.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Parametre Activer");
            	VueParametre.getInstance().getControleur().actionOuvrirParametre(VueParametre.getInstance());
            }
        });
		
		Button actionStatistiques = (Button) lookup("#btn-statistiques");
		actionStatistiques.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Statistiques Activer");
            	VueStatistiques.getInstance().getControleur().actionOuvrirStatistiques(VueStatistiques.getInstance());
            }
        });*/
	}
}
