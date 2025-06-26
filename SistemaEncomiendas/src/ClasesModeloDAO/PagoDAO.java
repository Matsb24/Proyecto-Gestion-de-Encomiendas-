package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.PagoDTO;
import MisInterfaces.PagoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PagoDAO implements PagoInterface {
    private final Conexion con = new Conexion();

    // Crear nuevo pago
    public void crearPago(PagoDTO pago) {
        String sql = "INSERT INTO pagos (Cliente_ID, Precio, Tipo_Pago) VALUES (?, ?, ?)";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pago.getClienteID());
            ps.setBigDecimal(2, pago.getPrecio());
            ps.setString(3, pago.getTipoPago());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Obtener pago por ID
    public PagoDTO obtenerPagoPorID(int pagoID) {
        PagoDTO pago = null;
        String sql = "SELECT Pago_ID, Cliente_ID, Precio, Tipo_Pago FROM pagos WHERE Pago_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pagoID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pago = new PagoDTO();
                    pago.setPagoID(rs.getInt("Pago_ID"));
                    pago.setClienteID(rs.getInt("Cliente_ID"));
                    pago.setPrecio(rs.getBigDecimal("Precio"));
                    pago.setTipoPago(rs.getString("Tipo_Pago"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pago;
    }

    // Actualizar pago
    public void actualizarPago(PagoDTO pago) {
        String sql = "UPDATE pagos SET Cliente_ID = ?, Precio = ?, Tipo_Pago = ? WHERE Pago_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pago.getClienteID());
            ps.setBigDecimal(2, pago.getPrecio());
            ps.setString(3, pago.getTipoPago());
            ps.setInt(4, pago.getPagoID());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Eliminar pago por ID
    public void eliminarPago(int pagoID) {
        String sql = "DELETE FROM pagos WHERE Pago_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pagoID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Listar todos los pagos
    public ArrayList<PagoDTO> listarTodo() {
        ArrayList<PagoDTO> listaPagos = new ArrayList<>();
        String sql = "SELECT Pago_ID, Cliente_ID, Precio, Tipo_Pago FROM pagos";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PagoDTO pago = new PagoDTO();
                pago.setPagoID(rs.getInt("Pago_ID"));
                pago.setClienteID(rs.getInt("Cliente_ID"));
                pago.setPrecio(rs.getBigDecimal("Precio"));
                pago.setTipoPago(rs.getString("Tipo_Pago"));
                listaPagos.add(pago);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaPagos;
    }
}
