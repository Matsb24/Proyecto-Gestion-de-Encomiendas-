package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.EstadoDTO;
import MisInterfaces.EstadoInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadoDAO implements EstadoInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<EstadoDTO> estados = new ArrayList<>();  // Lista para almacenar los estados

    // Método para insertar un estado en la BD
    public void crearEstado(EstadoDTO estado) {
        String query = "INSERT INTO estado (Estado_ID, Nombre_Estado) VALUES (?, ?)";
        Connection conn = null;  // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(query);  // Prepara la sentencia SQL
            ps.setInt(1, estado.getEstadoID());
            ps.setString(2, estado.getDescripcion());  // Utiliza el método getDescripcion()
            ps.executeUpdate();  // Ejecuta la sentencia SQL
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
    }

    // Método para listar todos los estados
    public ArrayList<EstadoDTO> listarTodo() {
        String sql = "SELECT * FROM estado";
        Connection conn = null;  // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            rs = ps.executeQuery();  // Ejecuta la consulta
            while (rs.next()) {
                // Crea un nuevo estado con los datos obtenidos de la BD
                EstadoDTO estado = new EstadoDTO();
                
                    estado.getEstadoID();
                    estado.getDescripcion();  // Utiliza el nombre de la columna correcto
                    estados.add(estado);  // Añade el estado a la lista
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return estados;
    }

    // Método para eliminar un estado por ID
    public void eliminar(int estadoID) {
        String sql = "DELETE FROM estado WHERE Estado_ID = ?"; // Ajusta el nombre de la tabla y columna según sea necesario
        Connection conn = null;  // Conexión local
        try {
            conn = con.getConexion(); // Abre la conexión
            ps = conn.prepareStatement(sql); // Prepara la sentencia SQL
            ps.setInt(1, estadoID); // Establece el ID en la consulta
            ps.executeUpdate(); // Ejecuta la actualización
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn); // Cierra los recursos
        }
    }

    
    public EstadoDTO obtenerEstadoPorID(int estadoID) {
    EstadoDTO estado = null;
    String sql = "SELECT * FROM estados WHERE Estado_ID = ?";
    try (Connection conn = con.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, estadoID);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            estado = new EstadoDTO();
            estado.setEstadoID(rs.getInt("Estado_ID"));
            estado.setNombre_estado(rs.getString("Nombre_Estado"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return estado;
}
    
    public String obtenerEstadoDescrPorEstadoID(int EstadoID) {
        String EstaID = ""; // Valor por defecto si no se encuentra el distrito
        String sql = "SELECT Descripcion_Encomienda FROM estados WHERE Estado_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, EstadoID);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                EstaID = rs.getString("Descripcion_Encomienda");  // Obtiene el UbiGEO_ID del distrito
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return EstaID;
    }
    
    public String obtenerEstadoNombrePorEstadoID(int EstadoID) {
        String EstaID = "";
        String sql = "SELECT Nombre_Estado FROM estados WHERE Estado_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, EstadoID);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                EstaID = rs.getString("Nombre_Estado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return EstaID;
    }
    
    // Método para cerrar recursos (Connection, PreparedStatement, ResultSet)
    private void cerrarRecursos(Connection conn) {
        try {
            if (rs != null) {
                rs.close();  // Cierra el ResultSet
            }
            if (ps != null) {
                ps.close();  // Cierra el PreparedStatement
            }
            if (conn != null) {
                conn.close();  // Cierra la conexión
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



