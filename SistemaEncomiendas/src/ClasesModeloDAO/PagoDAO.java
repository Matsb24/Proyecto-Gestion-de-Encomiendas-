package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.PagoDTO;
import MisInterfaces.PagoInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PagoDAO implements PagoInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    // Método para crear un nuevo pago
    public void crearPago(PagoDTO pago) {
        String sql = "INSERT INTO pagos (Cliente_ID, Precio, Tipo_Pago) VALUES (?, ?, ?)";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, pago.getClienteID());
            ps.setBigDecimal(2, pago.getPrecio());
            ps.setString(3, pago.getTipoPago());
            ps.executeUpdate();  // Ejecuta la sentencia SQL
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
    }

    // Método para obtener un pago por ID
    public PagoDTO obtenerPagoPorID(int pagoID) {
        String sql = "SELECT * FROM pagos WHERE Pago_ID = ?";
        PagoDTO pago = null;
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, pagoID);
            rs = ps.executeQuery();  // Ejecuta la consulta
            if (rs.next()) {
                pago = new PagoDTO();
                    pago.setPagoID(rs.getInt("Pago_ID")); // Asignar el ID de pago
                    pago.setClienteID(rs.getInt("Cliente_ID")); // Asignar el ID del cliente
                    pago.setPrecio(rs.getBigDecimal("Precio")); // Asignar el precio
                    pago.setTipoPago(rs.getString("Tipo_Pago")); // Asignar el tipo de pago
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return pago;
    }

    // Método para actualizar un pago
    public void actualizarPago(PagoDTO pago) {
        String sql = "UPDATE pagos SET Cliente_ID = ?, Precio = ?, Tipo_Pago = ? WHERE Pago_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, pago.getClienteID());
            ps.setBigDecimal(2, pago.getPrecio());
            ps.setString(3, pago.getTipoPago());
            ps.setInt(4, pago.getPagoID());
            ps.executeUpdate();  // Ejecuta la actualización
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
    }

    // Método para eliminar un pago por ID
    public void eliminarPago(int pagoID) {
        String sql = "DELETE FROM pagos WHERE Pago_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, pagoID);  // Establece el ID en la consulta
            ps.executeUpdate();  // Ejecuta la eliminación
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // Método para obtener todos los Pagos
    public ArrayList<PagoDTO> listarTodo() {
        String sql = "SELECT * FROM pagos";
        ArrayList<PagoDTO> listaPagos = new ArrayList<>();
        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PagoDTO pago = new PagoDTO();
                pago.setPagoID(rs.getInt("Pago_ID"));
                pago.setClienteID(rs.getInt("Cliente_ID"));
                pago.setPrecio(rs.getBigDecimal("Precio"));
                pago.setTipoPago(rs.getString("Tipo_Pago"));
                listaPagos.add(pago); // Agrega el pago a la lista
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPagos;
    }
    
}