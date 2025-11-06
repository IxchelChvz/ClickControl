<?php
//conexion con la base de datos //
$servername = "localhost"; 
$username = "root";  
$password = "";
$dbname = "eventos";



if (isset($_POST["nombre"])&& isset($_POST["eventos"]) &&
    isset($_POST["correo"]) && isset($_POST["telefono"]) &&
    isset($_POST["localidad"])
    ) {


    $nombre = $_POST["nombre"];
    $eventos = implode(", ", $_POST["eventos"]);
    $correo = $_POST["correo"];
    $localidad = $_POST["localidad"];
    $telefono = $_POST["telefono"];
} else {
    $nombre = "";
    $eventos = "";
    $correo = "";
    $localidad = "";
    $telefono = "";
}

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {

die("Error de conexión: " . $conn->connect_error);
}


if ($nombre === "" || $correo === "" || $eventos === "" || $telefono === "" || $localidad === "") {
	die("Todos los campos son obligatorios");
}


echo "Valor del evento recibido: " . $eventos;

$sql = "INSERT INTO inscripciones (nombre, evento,correo,telefono,localidad) VALUES ('$nombre','$eventos','$correo','$telefono','$localidad')";

if ($conn->query($sql) === TRUE) {
    echo "<h2>¡Registro correcto!</h2>";
    echo "<p>Nombre insertado: " . $nombre . "</p>";
    echo "<p>correo insertado: " . $correo . "</p>";
    echo "<p>localidad insertado: " . $localidad . "</p>";
    echo "<p>telefono insertado: " . $telefono . "</p>";
    echo "Valor del evento recibido: " . $eventos;
    echo "<p>Evento: $eventos</p>";
} else {
    echo "Error: " . $conn->error;
}




$conn->close();
?>




