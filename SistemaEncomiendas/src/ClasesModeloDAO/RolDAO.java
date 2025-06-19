/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesModeloDAO;
import BasedeDatos.Conexion;
import ClasesModeloDTO.RolDTO;
import MisInterfaces.RolInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RolDAO implements RolInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<RolDTO> listaRoles = new ArrayList<>();

    // Método para crear un nuevo rol
    public void crearRol(RolDTO rol) {
        String sql = "INSERT INTO rol (Rol_ID, Nom_rol) VALUES (?, ?)";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, rol.getRolID());
            ps.setString(2, rol.getNombre());
            ps.executeUpdate();  // Ejecuta la sentencia SQL
        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
    }

    // Método para obtener un rol por ID
    public RolDTO obtenerRolPorID(int rolID) {
        String sql = "SELECT * FROM rol WHERE Rol_ID = ?";
        RolDTO rol = null;
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, rolID);
            rs = ps.executeQuery();  // Ejecuta la consulta
            if (rs.next()) {
                rol = new RolDTO(
                    rs.getInt("Rol_ID"),
                    rs.getString("Nom_rol")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return rol;
    }

    // Método para actualizar un rol
    public void actualizarRol(RolDTO rol) {
        String sql = "UPDATE rol SET Nom_rol = ? WHERE Rol_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setString(1, rol.getNombre());
            ps.setInt(2, rol.getRolID());
            ps.executeUpdate();  // Ejecuta la actualización
        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
    }

    // Método para eliminar un rol por ID
    public void eliminarRol(int rolID) {
        String sql = "DELETE FROM rol WHERE Rol_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, rolID);  // Establece el ID en la consulta
            ps.executeUpdate();  // Ejecuta la eliminación
        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
    }

    
        public int obtenerRolIDPorNombre(String nombreRol) {
        int RolID = -1; // Valor por defecto si no se encuentra el distrito
        String sql = "SELECT Rol_ID FROM rol WHERE Nom_rol = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setString(1, nombreRol);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                RolID = rs.getInt("Rol_ID");  // Obtiene el UbiGEO_ID del distrito
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return RolID;
    }
        
    public String obtenerNombrePorRolID(int rol) {
        String nomrol = "";
        String sql = "SELECT Nom_rol FROM rol WHERE Rol_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, rol);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                nomrol = rs.getString("Nom_rol");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return nomrol;
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
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Método para listar todas las categorías
    public ArrayList<RolDTO> listarRoles() {
        String sql = "SELECT * FROM rol";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            rs = ps.executeQuery();  // Ejecuta la consulta
            while (rs.next()) {
                // Crea una nueva categoría con los datos obtenidos de la BD
                RolDTO rol = new RolDTO(
                        rs.getInt("Rol_ID"),
                        rs.getString("Nom_rol")
                );
                listaRoles.add(rol);  // Añade la categoría a la lista
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return listaRoles;
    }
}

