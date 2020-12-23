<?php
    include_once "BasedeDonnees.php";
	
	class StatistiqueDAO
	{
		function getGraphiqueStatistiqueParClient($idClient)
		{
			$LISTERSTATISTIQUES = "SELECT pseudo, utilisateur_id, heure, frequence, heureminimale
			From genererStatistiqueDeUtilisateurID(:idClient);";
			$requete = BaseDeDonnees::getConnection()->prepare($LISTERSTATISTIQUES);
			$requete->bindParam(':idClient', $idClient, PDO::PARAM_INT);
			$requete->execute();
			$listeStatistiqueGraphique = $requete->fetchAll();
			return $listeStatistiqueGraphique;
		}
		
		function getStatMessageParClient($idClient)
		{
			$STATISTIQUEMESSAGE = "SELECT Count(id) 
			From message 
			Where utilisateur_id = :idClient AND 
			age(date_trunc('hour', NOW()), message.moment) <= time '13:00';";
			$requete = BaseDeDonnees::getConnection()->prepare($STATISTIQUEMESSAGE);
			$requete->bindParam(':idClient', $idClient, PDO::PARAM_INT);
			$requete->execute();
			$statistiqueMessage = $requete->fetchAll();
			return $statistiqueMessage;
		}
	}
?>
