package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;

public class VueChatPrive extends Vue{

	public static VueChatPrive instance;
	public static VueChatPrive getInstance() {if(null == instance)instance = new VueChatPrive(); return instance;}
		
	protected Controleur controleur;
	
	private VueChatPrive () {
		super("vue_chat_prive.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueChatPrive()");
	}
	
	public void activerControles(){
		super.activerControles();
	}
}
