<?php
class Input{
	//Zmieniono $name na $content
	private $content;
	public function __construct($name){
		$this->content = $name;
	}
	public function render(){
		//Dodano "name='.$this->content.'"
		$out = '<input type="text" / name='.$this->content.'>';
		return $out;
	}
}
?>
