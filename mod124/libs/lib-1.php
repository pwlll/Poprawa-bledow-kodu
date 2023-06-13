<?php
class Form{

	private $legend;
	private $ctrls;

	public function __construct($text){
		$this->legend = $text;
	}
	public function pack(...$controls){
		$this->ctrls = $controls;
	}

	private function getHeader(){
		return '<h2>'.$this->legend.'</h2>';
	}

	public function display(){
		//Zamiana zmiennej $out odbywa się w innym miejscu
		$out='<form>'.$this->getHeader();
		foreach($this->ctrls as $ctrl){
			$out =$out.$ctrl->render().'</br>';
		}
		$out=$out.'</form>';
		return $out;
	}

}
?>
