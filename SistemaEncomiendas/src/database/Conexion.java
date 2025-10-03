

package database;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Conexion {
    protected Connection conn;

    private static final Logger LOGGER = Logger.getLogger(Conexion.class.getName());

    private static String DB_URL = "jdbc:mysql://localhost:3306/prueba1";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "";
    private static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        // Intentar cargar config desde classpath first, luego desde archivo en working dir
        Properties props = new Properties();
        try (InputStream in = Conexion.class.getResourceAsStream("/config.properties")) {
            if (in != null) {
                props.load(in);
                LOGGER.info("Cargando configuración de DB desde classpath /config.properties");
            } else {
                // intentar desde fichero relativo al working dir
                try (InputStream fin = new FileInputStream("config.properties")) {
                    props.load(fin);
                    LOGGER.info("Cargando configuración de DB desde ./config.properties");
                } catch (IOException e) {
                    // No hay fichero de configuración: seguiremos con valores por defecto
                    LOGGER.fine("No se encontró config.properties; usando valores por defecto");
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error leyendo config.properties", e);
        }

        if (props.getProperty("db.url") != null) DB_URL = props.getProperty("db.url");
        if (props.getProperty("db.user") != null) DB_USER = props.getProperty("db.user");
        if (props.getProperty("db.password") != null) DB_PASSWORD = props.getProperty("db.password");
        if (props.getProperty("db.driver") != null) DB_DRIVER = props.getProperty("db.driver");
    }

    public Connection getConexion() {
        try {
            Class.forName(DB_DRIVER); // Cargar el driver
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return conn;
        } catch (ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Driver JDBC no encontrado: " + DB_DRIVER, ex);
            throw new RuntimeException("Driver JDBC no encontrado: " + DB_DRIVER, ex);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error conectando a la base de datos: " + DB_URL, ex);
            throw new RuntimeException("Error conectando a la base de datos: " + DB_URL, ex);
        }
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            } finally {
                conn = null;
            }
        }
    }

    // Método para probar la conexión
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        try (Connection c = conexion.getConexion(); Statement stmt = c.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT 1")) {
                if (rs.next()) {
                    System.out.println("Conexión OK");
                }
            }
        } catch (RuntimeException rex) {
            System.err.println("Fallo al intentar conectar: " + rex.getMessage());
            rex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Error SQL durante prueba: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}



