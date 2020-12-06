package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;

public class VueParametre extends Vue{

	public static VueParametre instance;
	public static VueParametre getInstance() {if(null == instance)instance = new VueParametre(); return instance;}
		
	protected Controleur controleur;
	
	private VueParametre() {
		super("vue_parametre.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueParametre()");
	}
	
	public void activerControles(){
		super.activerControles();
	}

}
