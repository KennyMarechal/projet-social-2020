<?php
class SocialDAO
{
    public static function listerMessages()
    {
        include "connexion.php";
		$SQL_LISTE_MESSAGE = "SELECT * FROM message;";
		$requeteListeMessages = $basededonnees->prepare($SQL_LISTE_MESSAGE);
		$resultat = $requeteListeMessages->execute();
		$listeMessages = $requeteListeMessages->fetchAll(PDO::FETCH_OBJ);
		
		return $listeMessages;
    }

    public static function listerSalonParUtilisateurId($utilisateur_id)
    {
        include "connexion.php";
		$SQL_LISTE_SALON_PAR_UTILISATEUR_ID = "SELECT titre, salon.id FROM salon INNER JOIN autorisation 
        ON salon.id  = autorisation.salon_id AND autorisation.utilisateur_id = $utilisateur_id;";

		$requeteListeSalonParUtilisateurId = $basededonnees->prepare($SQL_LISTE_SALON_PAR_UTILISATEUR_ID);
		$resultat = $requeteListeSalonParUtilisateurId->execute();
		$listeSalonParUtilisateurId = $requeteListeSalonParUtilisateurId->fetchAll(PDO::FETCH_OBJ);
		
		return $listeSalonParUtilisateurId;
    }

    public static function listerDernierMessageParId($utilisateur_id, $salon_id)
    {
        include "connexion.php";
		$SQL_LISTE_SALON = "SELECT * FROM salon;";
		$requeteListeSalon = $basededonnees->prepare($SQL_LISTE_SALON);
		$resultat = $requeteListeSalon->execute();
		$listeSalons = $requeteListeSalon->fetchAll(PDO::FETCH_OBJ);
		
		return $listeSalons;
    }

    public static function listerSalon()
    {
        include "connexion.php";
		$SQL_LISTE_SALON = "SELECT * FROM salon;";
		$requeteListeSalon = $basededonnees->prepare($SQL_LISTE_SALON);
		$resultat = $requeteListeSalon->execute();
		$listeSalons = $requeteListeSalon->fetchAll(PDO::FETCH_OBJ);
		
		return $listeSalons;
    }

    public static function listerUtilisateur()
    {
        include "connexion.php";
		$SQL_LISTE_UTILISATEUR = "SELECT * FROM utilisateurs;";
		$requeteListeUtilisateurs = $basededonnees->prepare($SQL_LISTE_UTILISATEUR);
		$resultat = $requeteListeUtilisateurs->execute();
		$listeUtilisateurs = $requeteListeUtilisateurs->fetchAll(PDO::FETCH_OBJ);
		
		return $listeUtilisateurs;
	}
	
	public static function ajouterMessage($message)
	{
		include "connexion.php";

		$MESSAGE_SQL_AJOUTER_MESSAGE =
		"INSERT INTO message (id, moment, text, salon_id, utilisateur_id) VALUES(" .
		":id," .
		":moment," .
		":text," .
		":salon_id," .
		":utilisateur_id" .
		");";

		$requeteAjouterMessage = $basededonnees->prepare($MESSAGE_SQL_AJOUTER_MESSAGE);

		$requeteAjouterMessage->bindParam(':id', $message->id, PDO::PARAM_STR);
		$requeteAjouterMessage->bindParam(':moment', $message->moment, PDO::PARAM_STR);
		$requeteAjouterMessage->bindParam(':text', $message->text, PDO::PARAM_STR);
		$requeteAjouterMessage->bindParam(':salon_id', $message->salon_id, PDO::PARAM_STR);
		$requeteAjouterMessage->bindParam(':utilisateur_id', $message->utilisateur_id, PDO::PARAM_INT);

		$reussiteAjout = $requeteAjouterMessage->execute();
		print_r($requeteAjouterMessage->errorInfo());
	}
}
?>