package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.EnvioDTO;
import MisInterfaces.EnvioInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvioDAO implements EnvioInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<EnvioDTO> envio = new ArrayList<>();

    // Método para crear un envío en la BD
    public void crearEnvio(EnvioDTO envio) {
        String sql = "INSERT INTO envio (Encomienda_ID, Fecha_Envio, Fecha_Entrega, Estado_ID, Repartidor_ID, Cliente_ID) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, envio.getEncomiendaID());
            ps.setTimestamp(2, new java.sql.Timestamp(envio.getFechaEnvio().getTime()));
            ps.setTimestamp(3, new java.sql.Timestamp(envio.getFechaEntrega().getTime()));
            ps.setInt(4, envio.getEstadoID());
            ps.setInt(5, envio.getRepartidorID());
            ps.setInt(6, envio.getClienteID());
            ps.executeUpdate();  // Ejecuta la sentencia SQL
        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
    }

        // Método para listar todas los envios
    public ArrayList<EnvioDTO> listarTodo() {
        String sql = "SELECT * FROM envio";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            rs = ps.executeQuery();  // Ejecuta la consulta
            while (rs.next()) {
                // Crea una nueva categoría con los datos obtenidos de la BD
                EnvioDTO env = new EnvioDTO();
                env.setEnvioID(rs.getInt("Envio_ID"));
                env.setEncomiendaID(rs.getInt("Encomienda_ID"));
                env.setFechaEnvio(rs.getDate("Fecha_Envio"));
                env.setFechaEntrega(rs.getDate("Fecha_Entrega"));
                env.setEstadoID(rs.getInt("Estado_ID"));
                env.setRepartidorID(rs.getInt("Repartidor_ID"));
                env.setClienteID(rs.getInt("Cliente_ID"));
                envio.add(env);  // Añade la categoría a la lista
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return envio;
    }
    
    // Método para obtener un envío por ID
    public EnvioDTO obtenerEnvioPorID(int envioID) {
        String sql = "SELECT * FROM envio WHERE Envio_ID = ?";
        EnvioDTO envio = null;
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, envioID);
            rs = ps.executeQuery();  // Ejecuta la consulta
            if (rs.next()) {
                envio = new EnvioDTO(); 
                    envio.setClienteID(rs.getInt("Envio_ID"));
                    envio.setEncomiendaID(rs.getInt("Encomienda_ID"));
                    envio.setFechaEnvio(rs.getTimestamp("Fecha_Envio"));
                    envio.setFechaEntrega(rs.getTimestamp("Fecha_Entrega"));
                    envio.setEstadoID(rs.getInt("Estado_ID"));
                    envio.setRepartidorID(rs.getInt("Repartidor_ID"));
                    envio.setClienteID(rs.getInt("Cliente_ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return envio;
    }

    // Método para actualizar un envío
    public void actualizarEnvio(EnvioDTO envio) {
        String sql = "UPDATE envio SET Encomienda_ID = ?, Fecha_Envio = ?, Fecha_Entrega = ?, Estado_ID = ?, Repartidor_ID = ?, Cliente_ID = ? WHERE Envio_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, envio.getEncomiendaID());
            ps.setTimestamp(2, new java.sql.Timestamp(envio.getFechaEnvio().getTime()));
            ps.setTimestamp(3, new java.sql.Timestamp(envio.getFechaEntrega().getTime()));
            ps.setInt(4, envio.getEstadoID());
            ps.setInt(5, envio.getRepartidorID());
            ps.setInt(6, envio.getClienteID());
            ps.setInt(7, envio.getEnvioID());
            ps.executeUpdate();  // Ejecuta la actualización
        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
    }

    // Método para actualizar el estado de un envío
public void actualizarEstadoEnvio(int envioID, int nuevoEstadoID) {
    String sql = "UPDATE envio SET Estado_ID = ? WHERE Envio_ID = ?";
    Connection conn = null; // Conexión local
    try {
        conn = con.getConexion();  // Abre la conexión
        ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
        ps.setInt(1, nuevoEstadoID);  // Establece el nuevo estado
        ps.setInt(2, envioID);  // Establece el ID del envío a actualizar
        ps.executeUpdate();  // Ejecuta la actualización
    } catch (SQLException ex) {
        Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn);  // Cierra los recursos
    }
}

    // Método para eliminar un envío por ID
    public void eliminarEnvio(int envioID) {
        String sql = "DELETE FROM envio WHERE Envio_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, envioID);
            ps.executeUpdate();  // Ejecuta la eliminación
        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
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
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

