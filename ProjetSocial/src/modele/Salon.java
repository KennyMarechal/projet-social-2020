package modele;

public class Salon {

	private int id;
	private String titre;
	
	public Salon() {}
	
	public Salon(int id, String titre) {
		super();
		this.id = id;
		this.titre = titre;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
}
