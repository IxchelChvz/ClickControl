const texto = "Conferencias a las que puedes asistir";
let i = 0;

function escribir() {
  if (i < texto.length) {
    document.getElementById("texto").textContent += texto.charAt(i);
    i++;
    setTimeout(escribir, 100);
  }
}

escribir();