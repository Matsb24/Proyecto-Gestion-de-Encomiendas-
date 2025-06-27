package dao;

import dto.EnvioDTO;
import interfaces.EnvioInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conexion;

public class EnvioDAO implements EnvioInterface {
    private final Conexion con = new Conexion();

    // Crear un nuevo envío
    public void crearEnvio(EnvioDTO envio) {
        String sql = "INSERT INTO envio (Encomienda_ID, Fecha_Envio, Fecha_Entrega, Estado_ID, Repartidor_ID, Cliente_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, envio.getEncomiendaID());
            ps.setTimestamp(2, new Timestamp(envio.getFechaEnvio().getTime()));
            ps.setTimestamp(3, new Timestamp(envio.getFechaEntrega().getTime()));
            ps.setInt(4, envio.getEstadoID());
            ps.setInt(5, envio.getRepartidorID());
            ps.setInt(6, envio.getClienteID());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Listar todos los envíos
    public ArrayList<EnvioDTO> listarTodo() {
        ArrayList<EnvioDTO> envios = new ArrayList<>();
        String sql = "SELECT Envio_ID, Encomienda_ID, Fecha_Envio, Fecha_Entrega, Estado_ID, Repartidor_ID, Cliente_ID FROM envio";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EnvioDTO env = new EnvioDTO();
                env.setEnvioID(rs.getInt("Envio_ID"));
                env.setEncomiendaID(rs.getInt("Encomienda_ID"));
                env.setFechaEnvio(rs.getTimestamp("Fecha_Envio"));
                env.setFechaEntrega(rs.getTimestamp("Fecha_Entrega"));
                env.setEstadoID(rs.getInt("Estado_ID"));
                env.setRepartidorID(rs.getInt("Repartidor_ID"));
                env.setClienteID(rs.getInt("Cliente_ID"));
                envios.add(env);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return envios;
    }

    // Obtener envío por ID
    public EnvioDTO obtenerEnvioPorID(int envioID) {
        EnvioDTO envio = null;
        String sql = "SELECT Envio_ID, Encomienda_ID, Fecha_Envio, Fecha_Entrega, Estado_ID, Repartidor_ID, Cliente_ID FROM envio WHERE Envio_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, envioID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    envio = new EnvioDTO();
                    envio.setEnvioID(rs.getInt("Envio_ID"));
                    envio.setEncomiendaID(rs.getInt("Encomienda_ID"));
                    envio.setFechaEnvio(rs.getTimestamp("Fecha_Envio"));
                    envio.setFechaEntrega(rs.getTimestamp("Fecha_Entrega"));
                    envio.setEstadoID(rs.getInt("Estado_ID"));
                    envio.setRepartidorID(rs.getInt("Repartidor_ID"));
                    envio.setClienteID(rs.getInt("Cliente_ID"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return envio;
    }

    // Actualizar un envío completo
    public void actualizarEnvio(EnvioDTO envio) {
        String sql = "UPDATE envio SET Encomienda_ID = ?, Fecha_Envio = ?, Fecha_Entrega = ?, Estado_ID = ?, Repartidor_ID = ?, Cliente_ID = ? WHERE Envio_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, envio.getEncomiendaID());
            ps.setTimestamp(2, new Timestamp(envio.getFechaEnvio().getTime()));
            ps.setTimestamp(3, new Timestamp(envio.getFechaEntrega().getTime()));
            ps.setInt(4, envio.getEstadoID());
            ps.setInt(5, envio.getRepartidorID());
            ps.setInt(6, envio.getClienteID());
            ps.setInt(7, envio.getEnvioID());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Actualizar solo el estado de un envío
    public void actualizarEstadoEnvio(int envioID, int nuevoEstadoID) {
        String sql = "UPDATE envio SET Estado_ID = ? WHERE Envio_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nuevoEstadoID);
            ps.setInt(2, envioID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Eliminar un envío
    public void eliminarEnvio(int envioID) {
        String sql = "DELETE FROM envio WHERE Envio_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, envioID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
