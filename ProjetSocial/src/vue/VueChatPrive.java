package vue;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modele.Message;

public class VueChatPrive extends Vue{

	public static VueChatPrive instance;
	public static VueChatPrive getInstance() {if(null == instance)instance = new VueChatPrive(); return instance;}
	protected Controleur controleur;
	public Controleur getControleur() {return this.controleur;}

	private VueChatPrive ()
	{
		super("vue_chat_prive.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueChatPrive()");
	}
	
	
	public void activerControles()
	{
		super.activerControles();
	
		//Boutons généraux
		
		Button actionListeSalon = (Button) lookup("#btn-salons");
		actionListeSalon.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	VueSalons.getInstance().getControleur().actionOuvrirListeSalons(VueSalons.getInstance());
            }
        });
		
		Button actionChatPublic = (Button) lookup("#btn-ChatPublic");
		actionChatPublic.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Chat public Activer");
            	VueChatPublic.getInstance().getControleur().actionOuvrirChatPublic(VueChatPublic.getInstance());
            }
        });
		
		Button actionParametre = (Button) lookup("#btn-parametres");
		actionParametre.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	VueParametre.getInstance().getControleur().actionOuvrirParametre(VueParametre.getInstance());
            }
        });
		
		Button actionStatistiques = (Button) lookup("#btn-statistiques");
		actionStatistiques.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	VueStatistiques.getInstance().getControleur().actionOuvrirStatistiques(VueStatistiques.getInstance());
            }
        });
		
		//Bouton de vue
		Button actionEnvoyerMessage = (Button) lookup("#btn-envoyer");
		actionEnvoyerMessage.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				VueChatPrive.getInstance().construireMessage();
				
				//ËCHAFAUD POUR TEST D'AFFICHAGE
				List<Message> messages = new ArrayList<Message>();
				Timestamp momentCourant = new Timestamp(System.currentTimeMillis());
				messages.add(new Message(1, momentCourant, "Yo !", 2 ,1));
				messages.add(new Message(1, momentCourant, "Salut ! Comment ça va ?", 2 ,2));
				messages.add(new Message(1, momentCourant, "Moi ça va bien. Tu viens toujours au party de samedi soir ?", 2 ,1));
				messages.add(new Message(1, momentCourant, "Et comment !", 2 ,2));
				messages.add(new Message(1, momentCourant, "Je viendrais même si il fallait y aller sur les mains", 2 ,2));

				try
				{
					afficherMessages(messages);
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	private void construireMessage() 
	{
		Message message = new Message();
		TextArea champMessage = (TextArea) lookup("#champ-message");

		message.setId(6);
		message.setMoment(new Timestamp(System.currentTimeMillis())); //TODO Laisser le service de données gérer le moment
		message.setSalon_id(2);	//TODO Changer le salon dynamiquement selon la liste de salon dans le constructeur
		message.setText(champMessage.getText());
		message.setUtilisateur_id(Controleur.USER_ID);
		
		//this.getControleur().notifierEnvoiMessage(message);
	}
	
	private void afficherMessages(List<Message> messages) throws IOException
	{			
		VBox vbMessages = (VBox) lookup("#vb-messages");
		for (Message message : messages)
		{			
			Pane conteneurMessage = new Pane();
			Pane panneauMessage = FXMLLoader.load(getClass().getResource("/vue/message.fxml"));
			conteneurMessage.setPrefHeight(panneauMessage.getPrefHeight());

			//Affichage différent selon l'utilisateur qui a écrit le message
			if(message.getUtilisateur_id() == Controleur.USER_ID)
			{
				conteneurMessage.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
				panneauMessage.setStyle("-fx-background-color: " + Controleur.BLEU_MESSAGE);
			}
			else
			{
				conteneurMessage.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
				panneauMessage.setStyle("-fx-background-color: " + Controleur.GRIS_MESSAGE);
			}
			Label texteMessage = (Label) panneauMessage.getChildren().get(0);
			texteMessage.setText(message.getText());

			conteneurMessage.getChildren().add(panneauMessage);
			vbMessages.getChildren().add(conteneurMessage);
			vbMessages.setPrefHeight(vbMessages.getPrefHeight() + conteneurMessage.getPrefHeight() + vbMessages.getSpacing());
		}
		 
		
	}
}
