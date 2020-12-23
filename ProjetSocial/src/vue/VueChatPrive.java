package vue;

import java.io.IOException;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modele.Message;

public class VueChatPrive extends Vue{

	public static VueChatPrive instance;
	public static VueChatPrive getInstance() {if(null == instance)instance = new VueChatPrive(); return instance;}
	protected Controleur controleur;
	public Controleur getControleur() {return this.controleur;}
	
	private int id = 2;

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
				getControleur().notifierEnvoiMessagePrive(construireMessage());
				
			}
		});
		
		//Bouton rafriachissement
		Button actionRafraichirMessages = (Button) lookup("#btn-rafraichir");
		actionRafraichirMessages.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				getControleur().notifierRafraichissementChatPrive();
				
			}
		});
	}
	
	private Message construireMessage() 
	{
		Message message = new Message();
		TextArea champMessage = (TextArea) lookup("#champ-message");

		message.setText(champMessage.getText());
		message.setSalon_id(this.getId());
		message.setUtilisateur_id(Controleur.ID_UTILISATEUR);
		
		champMessage.clear();
		
		return message;
	}
	
	public void afficherMessages(List<Message> messages) throws IOException
	{			
		ScrollPane scrollPane = (ScrollPane) lookup("#panneau-messages");
		
		VBox vbMessages = (VBox) scrollPane.getContent();
		if(vbMessages.getChildren() != null)
		{
			vbMessages.getChildren().clear();
			vbMessages.setPrefHeight(0);
		}
		
		for (Message message : messages)
		{			
			Pane conteneurMessage = new Pane();
			Pane panneauMessage = FXMLLoader.load(getClass().getResource("/vue/message.fxml"));
			conteneurMessage.setPrefHeight(panneauMessage.getPrefHeight());

			//Affichage différent selon l'utilisateur qui a écrit le message
			if(message.getUtilisateur_id() == Controleur.ID_UTILISATEUR)
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
		scrollPane.setVvalue(1);

	}

	public int getId()
	{
		return this.id;
	}
}
