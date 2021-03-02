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
		actualiserAutomatique();
	}
	
	
	public void activerControles()
	{
		super.activerControles();
	
		//Boutons g�n�raux
		
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
	
	public void afficherMessages(List<Message> messages, List<String> utilisateurs) throws IOException
	{			
		ScrollPane scrollPane = (ScrollPane) lookup("#panneau-messages");
		
		VBox vbMessages = (VBox) scrollPane.getContent();
		if(vbMessages.getChildren() != null)
		{
			vbMessages.getChildren().clear();
			vbMessages.setPrefHeight(0);
		}
		
		Label titre = (Label) lookup("#titre");
		titre.setText("Chat avec : Tout le monde (mais �a devrait pas)");
		
		for (Message message : messages)
		{			
			Pane conteneurMessage = new Pane();
			Pane panneauMessage = FXMLLoader.load(getClass().getResource("/vue/message.fxml"));
			conteneurMessage.setPrefHeight(panneauMessage.getPrefHeight());

			//Affichage diff�rent selon l'utilisateur qui a �crit le message
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
			Label texteUtilisateur = (Label) panneauMessage.lookup("#champ-utilisateur");
			Label texteDate = (Label) panneauMessage.lookup("#champ-date");

			texteMessage.setText(message.getText());
			texteUtilisateur.setText("De : " + utilisateurs.get(message.getUtilisateur_id()-1));
			texteDate.setText(String.valueOf(message.getMoment()));

			conteneurMessage.getChildren().add(panneauMessage);
			vbMessages.getChildren().add(conteneurMessage);
			vbMessages.setPrefHeight(vbMessages.getPrefHeight() + conteneurMessage.getPrefHeight() + vbMessages.getSpacing());
			
		}
		scrollPane.setVvalue(1);
	}

	private void actualiserAutomatique(){
		Timer timer = new Timer();
		
		//timer.scheduleAtFixedRate(task, firstTime, period);
		
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  Logger.logMsg(Logger.INFO, "Timer");
				  getControleur().notifierRafraichissementChatPrive();
			  }
			}, 1000, 1000);
	}
	public int getId()
	{
		return this.id;
	}
}
