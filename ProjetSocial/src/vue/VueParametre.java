package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class VueParametre extends Vue{

	public static VueParametre instance;
	public static VueParametre getInstance() {if(null == instance)instance = new VueParametre(); return instance;}
		
	protected Controleur controleur;
	
	protected boolean isSombre = false;
	protected boolean isTextPetit = false;
	
	private VueParametre() {
		super("vue_parametre.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueParametre()");
	}
	
	public Controleur getControleur() {return this.controleur;}
	
	public void activerControles(){
		super.activerControles();
		
		Button actionAccueil = (Button) lookup("#retour");
		actionAccueil.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton retour Activer");
            	VueSalons.getInstance().getControleur().actionOuvrirListeSalons(VueSalons.getInstance());
            }
        });
        
        RadioButton RadioBoutonClair = (RadioButton) lookup("#radio-clair");
        RadioBoutonClair.setSelected(!isSombre);
        
        RadioButton RadioBoutonPetitText = (RadioButton) lookup("#radio-petit");
        RadioBoutonPetitText.setSelected(!isTextPetit);
		
	}

}
