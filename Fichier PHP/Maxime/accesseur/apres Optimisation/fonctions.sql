--
-- PostgreSQL
--

---
---Fonction de recuperation de tout les usagers contenu dans les salons de l'usager spécifié
---

CREATE OR REPLACE FUNCTION listerContactsDeUtilisateurID(utilisateurID int)
  RETURNS TABLE (utilisateur_id   int 
               , salon_id   int) AS
$func$
BEGIN
	RETURN QUERY
   
	SELECT autorisation.utilisateur_id, autorisation.salon_id
	From autorisation
	INNER JOIN (SELECT autorisation.salon_id from autorisation where autorisation.utilisateur_id = utilisateurID) listeAutorisationsUtilisateur
	ON autorisation.salon_id = listeAutorisationsUtilisateur.salon_id;
   
END
$func$  LANGUAGE plpgsql;

---
---Fonction de génération d'une grille de statistique des usagers contenu dans les salons de l'usager spécifié
---

CREATE OR REPLACE FUNCTION genererStatistiqueDeUtilisateurID(utilisateurID int)
  RETURNS TABLE (pseudo character varying (30)
	  			, utilisateur_id   int
				, heure double precision
				, frequence bigint
				, heureminimale double precision) AS
$func$
DECLARE
	maintenant timestamp with time zone := NOW();
BEGIN
	RETURN QUERY
   
	SELECT  source.pseudo, 
			source.utilisateur_id, 
			source.heure, 
			count(source.heure) as frequence, 
			(SELECT date_part('hour', maintenant - time '12:00') as heureminimale)
	FROM
	(SELECT	
		(SELECT utilisateurs.pseudo from utilisateurs where utilisateurs.id = message.utilisateur_id),
		message.utilisateur_id,
		date_part('hour', message.moment) as heure
		From message
		INNER JOIN (Select listerContactsDeUtilisateurID.utilisateur_id, listerContactsDeUtilisateurID.salon_id from listerContactsDeUtilisateurID(1)) listeContactsUtilisateur
		ON message.utilisateur_id = listeContactsUtilisateur.utilisateur_id AND 
		message.salon_id = listeContactsUtilisateur.salon_id AND 
		age(date_trunc('hour', maintenant), message.moment) <= time '12:00'

	) source
	GROUP BY source.pseudo, source.utilisateur_id, source.heure
	ORDER BY source.utilisateur_id;
END
$func$  LANGUAGE plpgsql;