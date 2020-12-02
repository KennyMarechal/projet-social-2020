package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;

public class VueAcceuil extends Vue{

	public static VueAcceuil instance;
	public static VueAcceuil getInstance() {if(null == instance)instance = new VueAcceuil(); return instance;}
		
	protected Controleur controleur;
	
	private VueAcceuil () {
		super("vue_accueil.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueAcceuil()");
	}
	
	public void activerControles(){
		super.activerControles();
	}
}
