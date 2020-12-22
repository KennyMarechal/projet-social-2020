<?php
	include "accesseur/SocialDAO.php";
	$socialDAO = new SocialDAO();
	$listeSalons = $socialDAO->listerSalon();

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	//print_r($listeSalons);
?>
<salons>
<?php
	foreach($listeSalons as $salon)
	{
		?>
	<salon>
	    <id><?=$salon->id?></id>
	    <titre><?=$salon->titre?></titre>
	</salon>
	<?php
	}
?>
</salons>