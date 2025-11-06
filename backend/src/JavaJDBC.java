
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//la clave principal se llama igual que el archivo//
public class JavaJDBC {
    public static void main(String[] args) {
        try {
    // Carga explícitamente el driver JDBC de MySQL en memoria
    // Esto registra el driver en DriverManager
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Establece la conexión con la base de datos
    // Parámetros: URL JDBC (//host:puerto/base de datos), usuario, contraseña
    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/eventos", "root", ""
    );

    // Crea un objeto de clase Statement para poder ejecutar sentencias SQL
    Statement statement = connection.createStatement();

    // Ejecuta una consulta SQL SELECT
    // Devuelve un ResultSet con las filas de la tabla "inscripciones"
    ResultSet resultSet = statement.executeQuery("select * from inscripciones");

    // Recorre el ResultSet fila por fila
    while (resultSet.next()) {
        // Obtiene los valores de la fila actual
        // getInt(2) → valor de la segunda columna, como entero
        // getString(1) → valor de la primera columna, como cadena
        // Imprime ambos valores por consola
        System.out.println(resultSet.getString(2) + " " + resultSet.getInt(1));
    }

    // ======================================================
// CANTIDAD TOTAL DE PERSONAS INSCRITAS
// ======================================================
ResultSet total = statement.executeQuery("SELECT COUNT(*) AS total FROM inscripciones");
if (total.next()) {
    System.out.println("\nTotal de personas inscritas: " + total.getInt("total"));
}
total.close();

// ======================================================
// NÚMERO DE PERSONAS INSCRITAS A CADA EVENTO
// ======================================================
System.out.println("\nPersonas inscritas por evento:");
ResultSet porEvento = statement.executeQuery(
    "SELECT evento, COUNT(*) AS cantidad FROM inscripciones GROUP BY evento ORDER BY evento"
);
while (porEvento.next()) {
    System.out.println(" - " + porEvento.getString("evento") + ": " + porEvento.getInt("cantidad"));
}
porEvento.close();

// ======================================================
// LISTADO DE PERSONAS POR EVENTO
// ======================================================
System.out.println("\nListado de personas por evento:");
ResultSet listado = statement.executeQuery(
    "SELECT evento, nombre, correo, telefono, localidad, fecha_inicio FROM inscripciones ORDER BY evento, nombre"
);

String eventoActual = null;
while (listado.next()) {
    String evento = listado.getString("evento");
    String nombre = listado.getString("nombre");
    String correo = listado.getString("correo");
    String telefono = listado.getString("telefono");
    String localidad = listado.getString("localidad");
    String fecha = listado.getString("fecha_inicio");

    if (eventoActual == null || !eventoActual.equals(evento)) {
        eventoActual = evento;
        System.out.println("\n>> " + eventoActual + " <<");
    }
    System.out.println("   " + nombre + " | " + correo + " | " + telefono + " | " + localidad + " | " + fecha);
}
listado.close();


    // Cierra la conexión con la base de datos (libera recursos)
    connection.close();

} catch (Exception e) {
    // Si ocurre cualquier error en todo el bloque try, se captura aquí
    // y se imprime la información del error
    System.out.println(e);
}

    }
}
