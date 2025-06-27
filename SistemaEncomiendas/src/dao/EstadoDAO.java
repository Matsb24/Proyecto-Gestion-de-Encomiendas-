package dao;

import dto.EstadoDTO;
import interfaces.EstadoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conexion;

public class EstadoDAO implements EstadoInterface {
    private final Conexion con = new Conexion();

    // Crear un nuevo estado
    public void crearEstado(EstadoDTO estado) {
        String sql = "INSERT INTO estados (Estado_ID, Nombre_Estado) VALUES (?, ?)";
        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estado.getEstadoID());
            ps.setString(2, estado.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Listar todos los estados
    public ArrayList<EstadoDTO> listarTodo() {
        ArrayList<EstadoDTO> estados = new ArrayList<>();
        String sql = "SELECT Estado_ID, Nombre_Estado FROM estados";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                EstadoDTO estado = new EstadoDTO();
                estado.setEstadoID(rs.getInt("Estado_ID"));
                estado.setDescripcion(rs.getString("Nombre_Estado"));
                estados.add(estado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estados;
    }

    // Eliminar estado por ID
    public void eliminar(int estadoID) {
        String sql = "DELETE FROM estados WHERE Estado_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estadoID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Obtener un estado por su ID
    public EstadoDTO obtenerEstadoPorID(int estadoID) {
        EstadoDTO estado = null;
        String sql = "SELECT Estado_ID, Nombre_Estado FROM estados WHERE Estado_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estadoID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    estado = new EstadoDTO();
                    estado.setEstadoID(rs.getInt("Estado_ID"));
                    estado.setDescripcion(rs.getString("Nombre_Estado"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estado;
    }

    // Método genérico para obtener un valor String de una columna por Estado_ID
    private String obtenerCampoEstadoPorID(int estadoID, String nombreColumna) {
        String valor = "";
        String sql = "SELECT " + nombreColumna + " FROM estados WHERE Estado_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estadoID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    valor = rs.getString(nombreColumna);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public String obtenerEstadoNombrePorEstadoID(int estadoID) {
        return obtenerCampoEstadoPorID(estadoID, "Nombre_Estado");
    }

    public String obtenerEstadoDescrPorEstadoID(int estadoID) {
        return obtenerCampoEstadoPorID(estadoID, "Descripcion_Encomienda");
    }
}

