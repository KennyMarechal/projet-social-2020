<?php
    include_once "BasedeDonnees.php";
	
	class StatistiqueDAO
	{
		function getGraphiqueStatistiqueParClient($idClient)
		{
			$LISTERSTATISTIQUES = "SELECT (SELECT pseudo from utilisateurs where utilisateurs.id = message.utilisateur_id),
				message.utilisateur_id,
				date_part('hour', message.moment) as heure,
				count(date_part('hour', message.moment)) as frequence,
				(SELECT date_part('hour', NOW() - time '12:00') as heureminimale)
			From message
			INNER JOIN (SELECT autorisation.*
				From autorisation
				INNER JOIN (SELECT salon_id from autorisation where utilisateur_id = :idClient) listeAutorisations
				ON autorisation.salon_id = listeAutorisations.salon_id) listeAutoriser
			ON message.utilisateur_id = listeAutoriser.utilisateur_id AND 
			message.salon_id = listeAutoriser.salon_id AND 
			age(date_trunc('hour', NOW()), message.moment) <= time '12:00'
			GROUP BY message.utilisateur_id, date_part('hour', message.moment)
			ORDER BY message.utilisateur_id;";
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
			age(date_trunc('hour', NOW()), message.moment) <= time '12:00';";
			$requete = BaseDeDonnees::getConnection()->prepare($STATISTIQUEMESSAGE);
			$requete->bindParam(':idClient', $idClient, PDO::PARAM_INT);
			$requete->execute();
			$statistiqueMessage = $requete->fetchAll();
			return $statistiqueMessage;
		}
	}
?>
