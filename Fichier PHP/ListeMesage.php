<?php
	include "accesseur/SocialDAO.php";
	$socialDAO = new SocialDAO();
	$listeMessages = $socialDAO->listerMessages();

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	//print_r($listeMessages);
?>
<messages>
<?php
	foreach($listeMessages as $message)
	{
		?>
	<message>
	    <id><?=$message->id?></id>
        <moment><?=$message->moment?></moment>
	    <text><?=$message->text?></text>
        <salon_id><?=$message->salon_id?></salon_id>
        <utilisateur_id><?=$message->utilisateur_id?></utilisateur_id>
	</message>
	<?php
	}
?>
</messages>