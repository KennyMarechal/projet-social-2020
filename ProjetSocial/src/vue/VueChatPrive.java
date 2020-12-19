package vue;

import java.sql.Timestamp;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import modele.Message;

public class VueChatPrive extends Vue{

	public static VueChatPrive instance;
	public static VueChatPrive getInstance() {if(null == instance)instance = new VueChatPrive(); return instance;}
	protected Controleur controleur;
	public Controleur getControleur() {return this.controleur;}

	private VueChatPrive ()
	{
		super("vue_chat_prive.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueChatPrive()");
	}
	
	
	public void activerControles()
	{
		super.activerControles();
	
		//Boutons généraux
		Button actionAccueil = (Button) lookup("#btn-accueil");
		actionAccueil.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	VueAccueil.getInstance().getControleur().actionOuvrirAccueil(VueAccueil.getInstance());
            }
        });
		
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
		
		Button actionStatistiques = (Button) lookup("#btn-statistiques");
		actionStatistiques.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	VueStatistiques.getInstance().getControleur().actionOuvrirStatistiques(VueStatistiques.getInstance());
            }
        });
		
		
		//Bouton de vue
		Button actionEnvoyerMessage = (Button) lookup("#btn-envoyer");
		actionEnvoyerMessage.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				VueChatPrive.getInstance().construireMessage();
			}
		});
	}
	
	private void construireMessage() 
	{
		Message message = new Message();
		TextArea champMessage = (TextArea) lookup("#champ-message");

		message.setMoment(new Timestamp(System.currentTimeMillis()));		
		//TODO Changer le salon dynamiquement selon la liste de salon dans le constructeur
		message.setSalon_id(2);
		message.setText(champMessage.getText());
		
		this.getControleur().notifierEnvoiMessage(message);
	}
}
