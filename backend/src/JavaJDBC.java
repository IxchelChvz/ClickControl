
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

    // Cierra la conexión con la base de datos (libera recursos)
    connection.close();

} catch (Exception e) {
    // Si ocurre cualquier error en todo el bloque try, se captura aquí
    // y se imprime la información del error
    System.out.println(e);
}

    }
}
