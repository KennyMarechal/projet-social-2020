<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);
	
	class BaseDeDonnees{
		public static function getConnection(){
			$usager = '{USAGERADMIN}';
			$motdepasse = '{MOTDEPASSE}';
			$hote = '{IPADDRESSE}';
			$base = '{BASEDEDONNEES}';
			$dsn = "pgsql:host=$hote;dbname=$base";
			$basededonnees = new PDO($dsn, $usager, $motdepasse,
			array(
				PDO::ATTR_ERRMODE => PDO::ERRMODE_WARNING,
				PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8mb4"
			  ));
			
			return $basededonnees;
		}
	}
?>