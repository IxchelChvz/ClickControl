<?php
//conexion con la base de datos //
$servername = "localhost"; 
$username = "root";  
$password = "";
$dbname = "evento";
//intenta abrir la conexion con los datos//
$conn = new mysqli($servername, $username, $password, $database);
//comprueba si la conexion fallo//
if ($conn->connect_error) {
    die("error de conexion:" . $conn->connecte_error);
}
//recibir los datos de el formulario//

$id_usuario = $_POST['id_usuario'];
$nombre = $_POST['nombre_completo'];
$evento = $_POST['tipo_de_evento'];
$correo = $_POST['correo'];
$telefono = $POST['telefono'];
$localidad = $POST['localidad'];
$fecha_inicio = $POST['fecha_inicio'];

//preparar y ejecutar la consulta SQL//
//para insertar la informcion en la base de datos//
$sql = "INSERT INT usuarios (id_usuario,nombre,evento,telefono,correo,localidad,fecha_inicio)VALUES ('$id_usuario', '$nombre', '$evento', '$telefono', '$correo', '$localidad', '$fecha_inicio')";

//ejecuta la consulta si devuelve true significa que le inserte fue exitoso//

if ($conn->query($sql) === TRUE){
    echo "<h3>Datos guardados correctamente:</h3>";
    echo "<p> id: $id_usuario</p>";
    echo "<p> nombre completo: $nombre</p>";
    echo "<p> evento: $evento</p>";
    echo "<p> telefono: $telefono</p>";
    echo "<p> correo: $correo</p>";
    echo "<p> localidad: $localidad</p>";
    echo "<p> fecha_inicio: $fecha_inicio</p>";
}else{
    echo "error al guardar: " . $conn->error;
}

//cerrar conexion//
$conn->close()
?>