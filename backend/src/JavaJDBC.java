//
// Importamos las clases necesarias
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaJDBC {
    public static void main(String[] args) {
        try {
            // Carga el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conexión con la base de datos (ajusta si tu usuario/clave cambia)
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/eventos", "root", ""
            );

            // Crea el objeto Statement para ejecutar consultas
            Statement statement = connection.createStatement();

            // ======================================================
            // MOSTRAR TODAS LAS INSCRIPCIONES (nombre e id)
            // ======================================================
            ResultSet resultSet = statement.executeQuery("SELECT * FROM inscripciones");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("nombre") + " " + resultSet.getInt("id_usuario"));
            }
            resultSet.close();

            // ======================================================
            // TOTAL DE PERSONAS INSCRITAS
            // ======================================================
            ResultSet total = statement.executeQuery("SELECT COUNT(*) AS total FROM inscripciones");
            if (total.next()) {
                System.out.println("\nTotal de personas inscritas: " + total.getInt("total"));
            }
            total.close();

            // ======================================================
            // PERSONAS INSCRITAS POR EVENTO (versión simplificada)
            // ======================================================
            System.out.println("\nPersonas inscritas por evento:");

            // Mapa para contar cada evento individualmente
            java.util.Map<String, Integer> contadorEventos = new java.util.HashMap<>();

            ResultSet rsEventos = statement.executeQuery("SELECT evento FROM inscripciones");
            while (rsEventos.next()) {
                String eventosTexto = rsEventos.getString("evento");
                // Divide por coma si hay varios eventos
                String[] eventosSeparados = eventosTexto.split(",\\s*");
                for (String ev : eventosSeparados) {
                    contadorEventos.put(ev, contadorEventos.getOrDefault(ev, 0) + 1);
                }
            }
            rsEventos.close();

            // Mostrar resultado limpio
            for (String ev : contadorEventos.keySet()) {
                System.out.println(" - " + ev + ": " + contadorEventos.get(ev));
            }

         // ======================================================
// LISTADO DE PERSONAS POR EVENTO (FORMATO LIMPIO)
// ======================================================
System.out.println("\nListado de personas por evento:");

// 1️⃣ Obtener todas las filas de la tabla
ResultSet listado = statement.executeQuery(
    "SELECT nombre, correo, telefono, localidad, evento FROM inscripciones ORDER BY evento, nombre"
);

// 2️⃣ Crear una lista de eventos sin repetir
java.util.Map<String, java.util.List<String>> eventos = new java.util.HashMap<>();

while (listado.next()) {
    String nombre = listado.getString("nombre");
    String correo = listado.getString("correo");
    String telefono = listado.getString("telefono");
    String localidad = listado.getString("localidad");
    String eventosTexto = listado.getString("evento");

    // Separar si hay varios eventos con comas
    String[] eventosSeparados = eventosTexto.split(",");

    for (String ev : eventosSeparados) {
        ev = ev.trim(); // quitar espacios
        eventos.putIfAbsent(ev, new java.util.ArrayList<>());
        eventos.get(ev).add(nombre + " | " + correo + " | " + telefono + " | " + localidad);
    }
}
listado.close();

// 3️⃣ Imprimir cada evento y sus personas
for (String evento : eventos.keySet()) {
    System.out.println("\n>> " + evento + " <<");
    for (String persona : eventos.get(evento)) {
        System.out.println("   " + persona);
    }
}


            // Cierra la conexión
            connection.close();

        } catch (Exception e) {
            // Captura cualquier error
            System.out.println(e);
        }
    }
}
