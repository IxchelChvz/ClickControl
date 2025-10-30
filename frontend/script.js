
const texto = "Conferencias a las que puedes asistir";
let i = 0;


const textoElemento = document.getElementById("texto");


const cursor = document.createElement("span");
cursor.classList.add("cursor");
textoElemento.appendChild(cursor);

function escribir() {
  if (i < texto.length) {
    cursor.insertAdjacentText("beforebegin", texto.charAt(i));
    i++;
    setTimeout(escribir, 70); 
  }
}

escribir();
