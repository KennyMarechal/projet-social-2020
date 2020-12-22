<?php 
	include "accesseur/SocialDAO.php";
	include "model/Message.php";
	
	print_r($_POST);

	$socialDAO = new SocialDAO();
	
	$messageTableau = $_POST;
	
	$message = new Message();
	
	$message->setId($messageTableau['id']);
	$message->setMoment($messageTableau['moment']);
	$message->setText($messageTableau['text']);
	$message->setSalon_id($messageTableau['salon_id']);
	$message->setUtilisateur_id($messageTableau['utilisateur_id']);
	
	$socialDAO->ajouterMessage($message);
?>