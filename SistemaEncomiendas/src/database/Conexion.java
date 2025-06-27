

package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    protected Connection conn;

    public Connection getConexion(){
        try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba1", "root", "");
    } catch (ClassNotFoundException ex) {
       
    } catch (SQLException ex) {
       
    }
    return conn; 
}

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Método para probar la conexión
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();

        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT 1");
                if (rs.next()) {
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Cerrar la conexión al final
                conexion.cerrarConexion();
            }
        }
    }
}



