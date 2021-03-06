package controleur;

import java.io.IOException;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.JedisMain;
import donnee.MessageDAO;
import donnee.SalonDAO;
import donnee.StatistiqueDAO;
import donnee.UtilisateurDAO;
import modele.Message;
import modele.Salon;
import vue.Navigateur;
import vue.Vue;
import vue.VueChatPrive;
import vue.VueChatPublic;
import vue.VueParametre;
import vue.VueSalons;
import vue.VueStatistiques;

public class Controleur 
{
	public final static int ID_UTILISATEUR = 1;
	public final static String BLEU_MESSAGE = "#DBDDDF";
	public final static String GRIS_MESSAGE = "#6C9DC1";
	
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
		Navigateur.getInstance().afficherVue(instance);
		lireMessagesPrives();
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
			Navigateur.getInstance().afficherVue(VueStatistiques.getInstance());
			break;
			
		case 2:
			Navigateur.getInstance().afficherVue(VueChatPublic.getInstance());
			break;
			
		case 3:
			actionOuvrirChatPrive(VueChatPrive.getInstance());
			break;
			
		}
	}
	
	private static void tests() {
		
		List<Salon> listeMois = SalonDAO.getInstance().getListe();
		System.out.println((listeMois.get(0)).getTitre());	
	}
	
	public void notifierRafraichissementChatPrive()
	{
		lireMessagesPrives();
	}

	public void notifierEnvoiMessagePrive(Message message)
	{
		MessageDAO.getInstance().envoyerMessage(message);
	}
	
	private void lireMessagesPrives()
	{
		List<Message> messages = MessageDAO.getInstance().listerMessagesParSalon(VueChatPrive.getInstance().getId());
		List<String> utilisateurs = UtilisateurDAO.getInstance().listerNomUtilisateurs();

		try
		{
			VueChatPrive.getInstance().afficherMessages(messages, utilisateurs);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
