package vue;

import java.util.HashMap;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.Controleur;
import donnee.StatistiqueDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import modele.PageStatistique;
import modele.StatistiqueUtilisateur;

public class VueStatistiques extends Vue{

	protected Controleur controleur = null;
	public static VueStatistiques instance;
	public static VueStatistiques getInstance() {if(null == instance)instance = new VueStatistiques(); return instance;}
	
	private VueStatistiques () {
		super("vue_statistiques.fxml");
		super.controleur = this.controleur = new Controleur();
		Logger.logMsg(Logger.INFO, "new VueAcceuil()");
	}
	
	public Controleur getControleur() {return this.controleur;}
	
	public void activerControles(){
		super.activerControles();

		Button actionRafraichir = (Button) lookup("#vue-statistique-button-rafraichir");
		actionRafraichir.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Rafraichir-Statistiques Activer");
            	getControleur().actionRafraichirPageStatistiques(getInstance());
            }
        });
		
		Button actionListeSalon = (Button) lookup("#btn-salons");
		actionListeSalon.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Accueil Activer");
            	VueSalons.getInstance().getControleur().actionOuvrirListeSalons(VueSalons.getInstance());
            }
        });
		
		Button actionChatPrive = (Button) lookup("#btn-chat");
		actionChatPrive.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Suivie Activer");
            	VueChatPrive.getInstance().getControleur().actionOuvrirChatPrive(VueChatPrive.getInstance());
            }
        });
		
		Button actionParametre = (Button) lookup("#btn-parametres");
		actionParametre.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Suivie Activer");
            	VueParametre.getInstance().getControleur().actionOuvrirParametre(VueParametre.getInstance());
            }

        });
		
		/*Button actionStatistiques = (Button) lookup("#btn-statistiques");
		actionStatistiques.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Suivie Activer");
            	VueStatistiques.getInstance().getControleur().actionOuvrirStatistiques(VueStatistiques.getInstance());
            }
        });*/
	}
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	public void afficherPageStatistique(PageStatistique page) 
	{
		//Afficher le graphique
		
		Pane pane = (Pane) lookup("#vue-statistique-pane");
		pane.getChildren().clear();
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("heures");
		NumberAxis yAxis = new NumberAxis(0, page.getValeurGraphiqueMaximale() + 1, 1);
		yAxis.setLabel("Nombre de messages");
		
		LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
		chart.setTitle("Message par heure durant les 12 dernières heures");
		
		List<StatistiqueUtilisateur> listeUtilisateur = page.getListeStatistiqueUtilisateur();
		
		int heureminimale = page.getHeureMinimale();
		for (StatistiqueUtilisateur utilisateur : listeUtilisateur) {	
			XYChart.Series serie = transformerListeStatistiqueEnSerie(utilisateur.getListeStatistique(), heureminimale);
			serie.setName(utilisateur.getNom());
			chart.getData().add(serie);
		}
		
		chart.setPrefWidth(pane.getPrefWidth());
		chart.setPrefHeight(pane.getPrefHeight());
		pane.getChildren().add(chart);
		
		//Afficher la statistique personelle (Nombre total de messages depuis 12h)
		Label labelNombreMessages = (Label) lookup("#vue-statistique-nombre-messages");
		labelNombreMessages.setText("Nombre de messages que vous avez envoyés dans les 12 dernières heures :  " + page.getStatistiquePersonelle());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private XYChart.Series transformerListeStatistiqueEnSerie(List<HashMap<String, Integer>> listeStatistiques, int heureminimale) {
		XYChart.Series serie = new XYChart.Series();
		for (int i = 0; i < 12; i++) {
			int j = i + heureminimale;
			if (j >= 24) j -= 24;
			
			int frequence = 0;
			
			for (HashMap<String, Integer> statistique : listeStatistiques) {
				if (statistique.get("heure") == j) {
					frequence = statistique.get("frequence");
					break;
				}
			}
			
			if (j < 10)
				serie.getData().add(new XYChart.Data<>("0" + j + "h00", frequence));
			else 
				serie.getData().add(new XYChart.Data<>(j + "h00", frequence));
		}
		return serie;
	}
}
