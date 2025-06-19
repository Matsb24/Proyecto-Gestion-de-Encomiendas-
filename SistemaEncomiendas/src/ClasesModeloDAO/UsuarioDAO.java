package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.DatosPersonalesDTO;
import ClasesModeloDTO.UsuarioDTO;
import MisInterfaces.UsuarioInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements UsuarioInterface {
    private Conexion con = new Conexion();
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    // Método para crear un nuevo usuario
    public void crearUsuario(UsuarioDTO usuario) {
        String query = "INSERT INTO usuario (Dni_ID, Rol_ID, Telefono, Contraseña) VALUES (?, ?, ?, ?)";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion(); // Abre la conexión
            ps = conn.prepareStatement(query);
            ps.setString(1, usuario.getDatosPersonales().getDniID());
            ps.setInt(2, usuario.getUsuarioRol());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getContrasena());

            
            ps.executeUpdate();

            // Agregar los datos personales
            DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, ps, conn); // Cierra los recursos
        }
    }

    // Método para obtener un usuario por ID
    public UsuarioDTO obtenerUsuarioById(int usuarioID) {
        String query = "SELECT * FROM usuario WHERE Usuario_ID = ?";
        UsuarioDTO usuario = null;
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion(); // Abre la conexión
            ps = conn.prepareStatement(query); // Prepara la sentencia SQL
            ps.setInt(1, usuarioID);
            rs = ps.executeQuery(); // Ejecuta la consulta

            if (rs.next()) {
                DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();


                usuario = new UsuarioDTO();
                usuario.setUsuarioID(rs.getInt("Usuario_ID"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setContrasena(rs.getString("Contraseña"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, ps, conn); // Cierra los recursos
        }
        return usuario;
    }

    //Login
public UsuarioDTO obtenerUsuarioPorTelefonoYContraseña(String telefono, String contraseña) {
    String query = "SELECT * FROM usuario WHERE Telefono = ? AND Contraseña = ?";
    Connection conn = null;
    UsuarioDTO usuario = null;

    try {
        conn = con.getConexion();  // Obtiene la conexión de la clase Conexion
        ps = conn.prepareStatement(query);  // Prepara la consulta SQL
        ps.setString(1, telefono);  // Asigna el primer parámetro (telefono)
        ps.setString(2, contraseña);  // Asigna el segundo parámetro (contraseña)

        rs = ps.executeQuery();  // Ejecuta la consulta
        if (rs.next()) {
            usuario = new UsuarioDTO();  // Si existe un resultado, crea un nuevo UsuarioDTO
            usuario.setUsuarioID(rs.getInt("Usuario_ID"));
            usuario.setTelefono(rs.getString("Telefono"));
            usuario.setContrasena(rs.getString("Contraseña"));
            usuario.setUsuarioRol(rs.getInt("Rol_ID"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return usuario;  // Retorna el usuario si se encontró, o null si no se encontró o hubo un error
}
    
    // Método para obtener todos los usuarios
    public ArrayList<UsuarioDTO> listarTodo() {
    String sql = "SELECT * FROM usuario";
    ArrayList<UsuarioDTO> rep = new ArrayList<>(); 
    try (Connection conn = con.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) { 

        while (rs.next()) {
            // Crea una nueva encomienda con los datos obtenidos de la BD
            UsuarioDTO r = new UsuarioDTO();
                    r.setUsuarioID(rs.getInt("Usuario_ID"));
                    r.setDniID(rs.getString("Dni_ID"));
                    r.setUsuarioRol(rs.getInt("Rol_ID"));
                    r.setTelefono(rs.getString("Telefono"));
            rep.add(r); 
        }
    } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rep; 
}

    // Método para actualizar un usuario
    public void actualizarUsuario(UsuarioDTO usuario) {
        String query = "UPDATE usuario SET Telefono = ?, Contraseña = ?, Dni_ID = ? WHERE Usuario_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion(); // Abre la conexión
            ps = conn.prepareStatement(query);
            ps.setString(1, usuario.getTelefono());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getDatosPersonales().getDniID());
            ps.setInt(4, usuario.getUsuarioID());
            ps.executeUpdate();

            // Actualizar los datos personales
            DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, ps, conn); // Cierra los recursos
        }
    }

    // Método para eliminar un usuario
    public void deleteUsuario(int usuarioID) {
        String query = "DELETE FROM usuario WHERE Usuario_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion(); // Abre la conexión
            ps = conn.prepareStatement(query);
            ps.setInt(1, usuarioID);
            ps.executeUpdate();

            DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();
            UsuarioDTO usuario = obtenerUsuarioById(usuarioID);

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, ps, conn); // Cierra los recursos
        }
    }

    // Método para cerrar recursos
    private void cerrarRecursos(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close(); // Cierra el ResultSet
            }
            if (ps != null) {
                ps.close(); // Cierra el PreparedStatement
            }
            if (conn != null) {
                conn.close(); // Cierra la conexión
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error al cerrar los recursos", ex);
        }
    }
}