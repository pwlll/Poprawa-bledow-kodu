<?php
class FileSystem
{
	public $dmsg = "";
	public function get( $dir, $file){
		$out = false;
		if( is_dir($dir) )
		{
			$this->dmsg = 'Katalog istnieje: '.$dir;
			if( file_exists($dir.'/'.$file) ){
				$this->dmsg = $dir.'/'.$file;
				$out = true;
			} else
				$this->dmsg = $dir.'/'.$file;
		} else
			$this->dmsg = $dir;
		return $out;
	}
}
	$fs = new FileSystem();

	# jedno z wielu wywołań wybranego pliku, który może się znajdować w katalogu A lub A/B
	$fleLded = $fs->get( './media', 'dokument.html' );
	echo ($fleLded) ? '#1 Plik istnieje' : '#1 Brak pliku';
	echo ': '.$fs->dmsg.'<br/>';
	if($fleLded===false)
		$fleLded = $fs->get( './media/assets', 'dokument.html' );
	echo ($fleLded) ? '#2 Plik istnieje' : '#2 Brak pliku';
	echo ': '.$fs->dmsg;
?>
