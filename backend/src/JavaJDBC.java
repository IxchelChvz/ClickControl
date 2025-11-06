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
            // LISTADO DE PERSONAS POR EVENTO
            // ======================================================
            System.out.println("\nListado de personas por evento:");
            ResultSet listado = statement.executeQuery(
                    "SELECT evento, nombre, correo, telefono, localidad FROM inscripciones ORDER BY evento, nombre"
            );

            String eventoActual = null;
            while (listado.next()) {
                String evento = listado.getString("evento");
                String nombre = listado.getString("nombre");
                String correo = listado.getString("correo");
                String telefono = listado.getString("telefono");
                String localidad = listado.getString("localidad");

                if (eventoActual == null || !eventoActual.equals(evento)) {
                    eventoActual = evento;
                    System.out.println("\n>> " + eventoActual + " <<");
                }
                System.out.println("   " + nombre + " | " + correo + " | " + telefono + " | " + localidad);
            }
            listado.close();

            // Cierra la conexión
            connection.close();

        } catch (Exception e) {
            // Captura cualquier error
            System.out.println(e);
        }
    }
}
