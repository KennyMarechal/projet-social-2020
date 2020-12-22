<?php
	include "accesseur/SocialDAO.php";
	$socialDAO = new SocialDAO();
	$listeUtilisateurs = $socialDAO->listerUtilisateur();

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	//print_r($listeUtilisateurs);
?>
<utilisateurs>
<?php
	foreach($listeUtilisateurs as $utilisateur)
	{
		?>
	<utilisateur>
	    <id><?=$utilisateur->id?></id>
	    <titre><?=$utilisateur->pseudo?></titre>
	</utilisateur>
	<?php
	}
?>
</utilisateurs>