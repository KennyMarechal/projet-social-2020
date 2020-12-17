package modele;

import java.sql.Timestamp;

public class Message {
	
	protected int id;
	protected Timestamp moment;
	protected String text;
	protected int salon_id;
	protected int utilisateur_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getMoment() {
		return moment;
	}
	public void setMoment(Timestamp moment) {
		this.moment = moment;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getSalon_id() {
		return salon_id;
	}
	public void setSalon_id(int salon_id) {
		this.salon_id = salon_id;
	}
	public int getUtilisateur_id() {
		return utilisateur_id;
	}
	public void setUtilisateur_id(int utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}
}
