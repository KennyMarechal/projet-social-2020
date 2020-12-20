package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class VueChatPublic extends Vue{
	
	public static VueChatPublic instance;
	public static VueChatPublic getInstance() {if(null == instance)instance = new VueChatPublic(); return instance;}
	
	protected Controleur controleur;
	public Controleur getControleur() {return this.controleur;}
	
	private VueChatPublic() {
		super("vue_chat_public.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueChatPublic();");
	}
	
	public void activerControles() {
		super.activerControles();
		
		Button actionListeSalon = (Button) lookup("#btn-salons");
		actionListeSalon.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	VueSalons.getInstance().getControleur().actionOuvrirListeSalons(VueSalons.getInstance());
            }
        });
	
		Button actionParametre = (Button) lookup("#btn-parametres");
		actionParametre.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override public void handle(ActionEvent e) 
	        {
	        	VueParametre.getInstance().getControleur().actionOuvrirParametre(VueParametre.getInstance());
	        }
	    });
		
		Button actionChatPrive = (Button) lookup("#btn-chat");
		actionChatPrive.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Suivie Activer");
            	VueChatPrive.getInstance().getControleur().actionOuvrirChatPrive(VueChatPrive.getInstance());
            }
        });
		
		Button actionStatistiques = (Button) lookup("#btn-statistiques");
		actionStatistiques.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override public void handle(ActionEvent e) 
	        {
	        	VueStatistiques.getInstance().getControleur().actionOuvrirStatistiques(VueStatistiques.getInstance());
	        }
	    });
	}
}