package controleur;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.JedisMain;
import donnee.MessageDAO;
import donnee.SalonDAO;
import donnee.StatistiqueDAO;
import modele.Message;
import modele.Salon;
import vue.Fenetre;
import vue.Navigateur;
import vue.Vue;
import vue.VueChatPrive;
import vue.VueChatPublic;
import vue.VueParametre;
import vue.VueSalons;
import vue.VueStatistiques;

public class Controleur 
{
	public final static int USER_ID = 1;
	public final static String BLEU_MESSAGE = "#68aded";
	public final static String GRIS_MESSAGE = "#c9c9c9";
	
	public Controleur() {
		Logger.logMsg(Logger.INFO, "new Controleur()");
	}
		
	public static Vue selectionnerVuePrincipale() 
	{ 	
		JedisMain jedisMain = new JedisMain();
		VueSalons.getInstance().afficherSalons(jedisMain.getCache());
		return VueSalons.getInstance();
	}


	public void actionOuvrirChatPrive(VueChatPrive instance) 
	{
		//instance.afficherMessages(Salon)
		Navigateur.getInstance().afficherVue(instance);
	}
	
	public void actionOuvrirChatPublic(VueChatPublic instance) {
		Navigateur.getInstance().afficherVue(instance);
	}

	public void actionOuvrirParametre(VueParametre instance) 
	{		
		Navigateur.getInstance().afficherVue(instance);
	}

	public void actionOuvrirStatistiques(VueStatistiques instance) 
	{
		instance.afficherPageStatistique(StatistiqueDAO.getInstance().getPage());
		Navigateur.getInstance().afficherVue(instance);
	}
	
	public void actionRafraichirPageStatistiques(VueStatistiques instance) 
	{
		StatistiqueDAO.getInstance().rafraichir();
		actionOuvrirStatistiques(instance);
	}

	public void actionOuvrirListeSalons(VueSalons instance) 
	{		
		Navigateur.getInstance().afficherVue(instance);
	}

	public void actionOuvrirSalon(int id) {
		switch(id) {
		case 1:
			Fenetre.getInstance().afficherVue(VueStatistiques.getInstance());
			break;
			
		case 2:
			Fenetre.getInstance().afficherVue(VueChatPublic.getInstance());
			break;
			
		case 3:
			Fenetre.getInstance().afficherVue(VueChatPrive.getInstance());
			break;
			
		}
	}
	
	private static void tests() {
		
		List<Salon> listeMois = SalonDAO.getInstance().getListe();
		System.out.println((listeMois.get(0)).getTitre());	
	}

	public void notifierEnvoiMessage(Message message)
	{
		message.setUtilisateur_id(USER_ID);
		MessageDAO.getInstance().envoyerMessage(message);		
	}
}
