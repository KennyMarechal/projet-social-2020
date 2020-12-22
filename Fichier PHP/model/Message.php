<?php
class Message{
    public $id;
	public $moment;
	public $text;
	public $salon_id;
    public $utilisateur_id;
    
    public function getId(){
		return $this->id;
	}
	
	public function setId($id){
		$this->id = $id;
	}
	
	public function getMoment(){
		return $this->moment;
	}
	
	public function setMoment($moment){
		$this->moment = $moment;
	}
	
	public function getText(){
		return $this->text;
	}
	
	public function setText($text){
		$this->text = $text;
	}
	
	public function getSalon_id(){
		return $this->salon_id;
	}
	
	public function setSalon_id($salon_id){
		$this->salon_id = $salon_id;
	}

	public function getUtilisateur_id(){
		return $this->utilisateur_id;
	}
	
	public function setUtilisateur_id($utilisateur_id){
		$this->utilisateur_id = $utilisateur_id;
	}
}
?>