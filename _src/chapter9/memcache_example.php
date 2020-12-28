<?php

$memcache = new Memcache();
$memcache->connect("127.0.0.1", 5701)
  or die("Could not connect to Hazelcast");

$city = Array();
$city["name"] = "London";
$city["country"] = "GB";
$city["population"] = 8174100;

$memcache->set("London-GB", $city)
  or die("Failed to save city");

$london = $memcache->get("London-GB");

echo var_export($london, true) ."\n";

?>
