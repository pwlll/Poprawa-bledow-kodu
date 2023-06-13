<?php
require_once("etc/conf-1.php");
require_once("etc/conf-2.php");
require_once("libs/lib-1.php");
require_once("libs/lib-2.php");
require_once("libs/lib-3.php");
require_once("libs/lib-4.php");
$f = new Form('Logowanie');
$l1 = new Label('Login');
$l2 = new Label('Hasło');
$i1 = new Input('user');
$i2 = new Input('pass');
$b2 = new Button('Wyślij');
//Zamieniono argumenty funkcji pack()
$f->pack($l1,$i1,$l2,$i2,$b2);
echo $f->display();
?>
