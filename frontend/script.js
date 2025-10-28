// Texto que se va a escribir
const texto = "Conferencias a las que puedes asistir";
let i = 0;

// Selecciona el span del HTML
const textoElemento = document.getElementById("texto");

// Crea el cursor parpadeante
const cursor = document.createElement("span");
cursor.classList.add("cursor");
textoElemento.appendChild(cursor);

function escribir() {
  if (i < texto.length) {
    // Inserta una letra antes del cursor
    cursor.insertAdjacentText("beforebegin", texto.charAt(i));
    i++;
    setTimeout(escribir, 70); // Velocidad de escritura
  }
}

escribir();
