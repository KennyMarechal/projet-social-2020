package controleur;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.MessageDAO;
import donnee.SalonDAO;
import donnee.StatistiqueDAO;
import modele.Message;
import modele.Salon;
import vue.Fenetre;
import vue.Navigateur;
import vue.Vue;
import vue.VueAccueil;
import vue.VueChatPrive;
import vue.VueParametre;
import vue.VueSalons;
import vue.VueStatistiques;

public class Controleur {
	
	public Controleur() {
		Logger.logMsg(Logger.INFO, "new Controleur()");
	}
	
	public static Vue selectionnerVuePrincipale() { 
		/*La vue principale c'est la vue qui comporte les listes*/
		//return VueAccueil.getInstance();
		//return VueParametre.getInstance();
		//return VueChatPrive.getInstance();
		
		tests();
		
		return VueSalons.getInstance();
	}

	public void actionOuvrirAccueil(VueAccueil instance) 
	{
		//TODO Appeler les fonction d'affichage de ta vue avant quel s'affiche
		
		Navigateur.getInstance().afficherVue(instance);
	}

	public void actionOuvrirChatPrive(VueChatPrive instance) 
	{
		//instance.afficherMessages(Salon)
		Navigateur.getInstance().afficherVue(instance);
	}

	public void actionOuvrirParametre(VueParametre instance) 
	{
		//TODO Appeler les fonction d'affichage de ta vue avant quel s'affiche
		
		Navigateur.getInstance().afficherVue(instance);
	}

	public void actionOuvrirStatistiques(VueStatistiques instance) 
	{
		instance.afficherPageStatistique(StatistiqueDAO.getInstance().getPage());
		Navigateur.getInstance().afficherVue(instance);
	}

	public void actionOuvrirListeSalons(VueSalons instance) 
	{
		//TODO Appeler les fonction d'affichage de ta vue avant quel s'affiche
		
		Navigateur.getInstance().afficherVue(instance);
	}

	/*public void actionOuvrirSalon(int id) {
		switch(id) {
		case 1:
			Fenetre.getInstance().afficherVue(VueChatPrive.getInstance());
			break;
			
		}
	}*/
	
	private static void tests() {
		
		List<Salon> listeMois = SalonDAO.getInstance().getListe();
		System.out.println((listeMois.get(0)).getTitre());	
	}

	public void notifierEnvoiMessage(Message message)
	{
		message.setUtilisateur_id(2); //TODO Utiliser le USER_ID global
		MessageDAO.getInstance().envoyerMessage(message);		
	}
}
