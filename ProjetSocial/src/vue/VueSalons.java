package vue;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

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
		
		Button actionAccueil = (Button) lookup("#btn-accueil");
		actionAccueil.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Accueil Activer");
            	VueAccueil.getInstance().getControleur().actionOuvrirAccueil(VueAccueil.getInstance());
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
	
	/*public void afficherCroisieres(List<Salon> salons)
	{
		
		Logger.logMsg(Logger.INFO,"VueSalons.afficherSalons");

		Pane listPane = (Pane)lookup("#liste"); 
		if(!listPane.getChildren().isEmpty()) 
        {
			listPane.getChildren().clear();
        }
        
        int position = 1;
        for(Salon salon:salons) 
        {	
        	Button btnSalon = (Button)lookup("#salon-" + position);
			btnSalon.setText(salon.getNom());
			btnSalon.setId(salon.getId()+""); // l'id est changé mais on n'a plus besoin de recuperer l'objet
			
			btnSalon.setOnAction(new EventHandler<ActionEvent>() 
			{
	            @Override public void handle(ActionEvent e) 
	            {
	            	Logger.logMsg(Logger.INFO, "Bouton Collection active");
	            	Button bouton = (Button)e.getSource();
	            	//controleur.actionOuvrirSalon(Integer.parseInt(bouton.getId()));
	            }
	        });

			position++;
        }

	}*/
}
