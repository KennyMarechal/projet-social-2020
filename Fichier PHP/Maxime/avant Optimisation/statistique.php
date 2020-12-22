<?php
	include "accesseur/StatistiqueDAO.php";
	$statistiqueDAO = new StatistiqueDAO();

        $idUtilisateur = $_GET["utilisateur"];
	$listeStatistiqueGraphique = $statistiqueDAO->getGraphiqueStatistiqueParClient($idUtilisateur);
	$statistiqueMessage = $statistiqueDAO->getStatMessageParClient($idUtilisateur);
?>
<?php
echo '<?xml version="1.0" encoding="UTF-8"?>';
?>

<statistiques>
	<listeStatistiqueUtilisateur>
<?php 	$i = -1;
	$max = 0;
	foreach($listeStatistiqueGraphique as $statistiqueGraphique){ 
		if ($i == -1){
			$i = $statistiqueGraphique["utilisateur_id"];
?>
		<utilisateur>
			<nom><?=$statistiqueGraphique["pseudo"]?></nom>
			<listeStatistiqueGraphique>
<?php
		} elseif ($i != $statistiqueGraphique["utilisateur_id"]) {
			$i = $statistiqueGraphique["utilisateur_id"];
?>
			</listeStatistiqueGraphique>
		</utilisateur>
		<utilisateur>
			<nom><?=$statistiqueGraphique["pseudo"]?></nom>
			<listeStatistiqueGraphique>
<?php   } ?>
				<statistiqueGraphique>
					<heure><?=$statistiqueGraphique["heure"]?></heure>
					<frequence><?=$statistiqueGraphique["frequence"]?></frequence>
				</statistiqueGraphique>
<?php		if ($max < $statistiqueGraphique["frequence"]){ $max = $statistiqueGraphique["frequence"]; }
	} if ($i != -1){
?>
			</listeStatistiqueGraphique>
		</utilisateur>
<?php   } ?>
	</listeStatistiqueUtilisateur>
<?php if ($i != -1){ ?>
	<heureMinimale><?=$listeStatistiqueGraphique[0]["heureminimale"]?></heureMinimale>
<?php   } else { ?>
	<heureMinimale>0</heureMinimale>
<?php	} ?>
	<valeurGraphiqueMaximale><?=$max?></valeurGraphiqueMaximale>
	<statistiqueMessage><?=$statistiqueMessage[0]["count"]?></statistiqueMessage>
</statistiques>
