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
import vue.VueChatPrive;
import vue.VueParametre;
import vue.VueSalons;
import vue.VueStatistiques;

public class Controleur {
	
	public Controleur() {
		Logger.logMsg(Logger.INFO, "new Controleur()");
	}
	
	public static Vue selectionnerVuePrincipale() { 
		
		tests();
		
		return VueSalons.getInstance();
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
	
	public void actionRafraichirPageStatistiques(VueStatistiques instance) 
	{
		StatistiqueDAO.getInstance().rafraichir();
		actionOuvrirStatistiques(instance);
	}

	public void actionOuvrirListeSalons(VueSalons instance) 
	{
		//TODO Appeler les fonction d'affichage de ta vue avant quel s'affiche
		
		Navigateur.getInstance().afficherVue(instance);
	}

	/*public void actionOuvrirSalon(int id) {
		switch(id) {
		case 1:
<<<<<<< Updated upstream
<<<<<<< Updated upstream
			Fenetre.getInstance().afficherVue(VueChatPrive.getInstance());
			break;
			
=======
=======
>>>>>>> Stashed changes
<<<<<<< HEAD
			Fenetre.getInstance().afficherVue(VueChatPublic.getInstance());
			break;
			
		case 2:
			Fenetre.getInstance().afficherVue(VueChatPrive.getInstance());
			break;
			
		case 3:
			Fenetre.getInstance().afficherVue(VueStatistiques.getInstance());
			break;
=======
			Fenetre.getInstance().afficherVue(VueChatPrive.getInstance());
			break;
			
>>>>>>> 81485bbc44613f1352832f3932b91d41b8224780
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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
