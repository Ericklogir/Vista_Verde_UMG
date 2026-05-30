
package proyectofinalvistaverde;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String URL ="jdbc:mysql://localhost:3306/Vistaverde";

    private static final String USER = "root";

    private static final String PASSWORD = "erick123";

    public static Connection getConexion() {

        try {

            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);

            return con;

        } catch (SQLException e) {

            System.out.println("Error de conexión: " + e.getMessage());

            return null;
        }
    }
    
}
