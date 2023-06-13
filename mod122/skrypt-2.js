console.log("Koordynaty");

//Zmieniono "#moveMe" i "#move" na "moveMe" i "move"
let box = document.getElementById("moveMe");
document.getElementById("move").addEventListener('click', ()=>{
	console.log("Moving");
	box.style.position="absolute";
	
	//Zmieniono '=150' na '="150px"'
	box.style.top="150px";
	box.style.left="150px";
});
