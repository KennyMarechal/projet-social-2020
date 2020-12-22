package vue;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import modele.Salon;
import redis.clients.jedis.Jedis;

public class VueSalons extends Vue{

	public static VueSalons instance;
	public static VueSalons getInstance() {if(null == instance)instance = new VueSalons(); return instance;}
		
	protected Controleur controleur;
	
	private VueSalons () {
		super("vue_salons.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueAcceuil()");
	}
	
	public Controleur getControleur() {return this.controleur;}
	
	public void activerControles(){
		super.activerControles();
		
		Button actionChatPublic = (Button) lookup("#btn-ChatPublic");
		actionChatPublic.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Chat public Activer");
            	VueChatPublic.getInstance().getControleur().actionOuvrirChatPublic(VueChatPublic.getInstance());
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
		
		Button actionParametre = (Button) lookup("#btn-parametres");
		actionParametre.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Suivie Activer");
            	VueParametre.getInstance().getControleur().actionOuvrirParametre(VueParametre.getInstance());
            }
        });
		
		Button actionStatistiques = (Button) lookup("#btn-statistiques");
		actionStatistiques.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Suivie Activer");
            	VueStatistiques.getInstance().getControleur().actionOuvrirStatistiques(VueStatistiques.getInstance());
            }
        });
		
	}
	
	public void afficherSalons(List<String> salons)
	{
		Logger.logMsg(Logger.INFO,"VueSalons.afficherSalons");

		Pane listPane = (Pane)lookup("#liste"); 
        
        int position = 1;
        for(String salon:salons) 
        {	
        	Button btnSalon = (Button)lookup("#salon-" + position);
			btnSalon.setText(salon);
			btnSalon.setId(position +""); // l'id est changé mais on n'a plus besoin de recuperer l'objet
			
			btnSalon.setOnAction(new EventHandler<ActionEvent>() 
			{
	            @Override public void handle(ActionEvent e) 
	            {
	            	Logger.logMsg(Logger.INFO, "Bouton Collection active");
	            	Button bouton = (Button)e.getSource();
	            	controleur.actionOuvrirSalon(Integer.parseInt(bouton.getId()));
	            }
	        });

			position++;
        }

	}
}
