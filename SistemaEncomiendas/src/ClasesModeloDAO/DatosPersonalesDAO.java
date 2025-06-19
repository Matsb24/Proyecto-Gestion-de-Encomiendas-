package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.DatosPersonalesDTO;
import MisInterfaces.DatosPersonalesInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatosPersonalesDAO implements DatosPersonalesInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    // Método para crear nuevos datos personales
    public void crearDatosPersonales(DatosPersonalesDTO datosPersonales) {
        String query = "INSERT INTO datos_personales (Dni_ID, Nombre, Apellido, Direccion) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        
        try {
            conn = con.getConexion();  
            ps = conn.prepareStatement(query);
            ps.setString(1, datosPersonales.getDniID());
            ps.setString(2, datosPersonales.getNombre());
            ps.setString(3, datosPersonales.getApellido());
            ps.setString(4, datosPersonales.getDireccion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
    }

    // Método para obtener datos personales por ID
    public DatosPersonalesDTO obtenerDatosPersonalesById(String dniID) {
        DatosPersonalesDTO datosPersonales = null;
        String query = "SELECT * FROM datos_personales WHERE Dni_ID = ?";
        Connection conn = null;
        
        try {
            conn = con.getConexion();  
            ps = conn.prepareStatement(query);
            ps.setString(1, dniID);
            rs = ps.executeQuery();
            if (rs.next()) {
                datosPersonales = new DatosPersonalesDTO();
                datosPersonales.setDniID(rs.getString("Dni_ID"));
                datosPersonales.setNombre(rs.getString("Nombre"));
                datosPersonales.setApellido(rs.getString("Apellido"));
                datosPersonales.setDireccion(rs.getString("Direccion"));
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return datosPersonales;
    }

    // Método para listar todos los datos personales
    public ArrayList<DatosPersonalesDTO> getAllDatosPersonales() {
        String sql = "SELECT * FROM datos_personales";
        ArrayList<DatosPersonalesDTO> dat = new ArrayList<>();
        try (Connection conn = con.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                DatosPersonalesDTO datosPersonales = new DatosPersonalesDTO();
                datosPersonales.setDniID(rs.getString("Dni_ID"));
                datosPersonales.setNombre(rs.getString("Nombre"));
                datosPersonales.setApellido(rs.getString("Apellido"));
                datosPersonales.setDireccion(rs.getString("Direccion"));
                dat.add(datosPersonales);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dat;
    }

    // Método para actualizar los datos personales
    public void actualizarDatosPersonales(DatosPersonalesDTO datosPersonales) {
        String query = "UPDATE datos_personales SET Nombre = ?, Apellido = ?, Direccion = ? WHERE Dni_ID = ?";
        Connection conn = null;
        
        try {
            conn = con.getConexion();  
            ps = conn.prepareStatement(query);
            ps.setString(1, datosPersonales.getNombre());
            ps.setString(2, datosPersonales.getApellido());
            ps.setString(3, datosPersonales.getDireccion());
            ps.setString(4, datosPersonales.getDniID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
    }

    // Método para eliminar datos personales por ID
    public void deleteDatosPersonales(String dniID) {
        String query = "DELETE FROM datos_personales WHERE Dni_ID = ?";
        Connection conn = null;
        
        try {
            conn = con.getConexion();  
            ps = conn.prepareStatement(query);
            ps.setString(1, dniID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
    }

    public String obtenerDNINombreMoto(String DNI) {
        String DN = ""; // Valor por defecto si no se encuentra el distrito
        String sql = "SELECT Nombre FROM datos_personales WHERE Dni_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setString(1, DNI);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                DN = rs.getString("Nombre");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return DN;
    }
    
    // Método para cerrar recursos (PreparedStatement, ResultSet, Connection)
    private void cerrarRecursos(Connection conn) {
        try {
            if (rs != null) {
                rs.close();  // Cierra el ResultSet
            }
            if (ps != null) {
                ps.close();  // Cierra el PreparedStatement
            }
            if (conn != null) {
                conn.close();  // Cierra la conexión si se provee
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
