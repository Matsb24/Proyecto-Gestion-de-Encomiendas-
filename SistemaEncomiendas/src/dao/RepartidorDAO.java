/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.RepartidorDTO;
import interfaces.RepartidorInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conexion;

public class RepartidorDAO implements RepartidorInterface {
    private final Conexion con = new Conexion();

    // Crear nuevo repartidor
    public void crearRepartidor(RepartidorDTO repartidor) {
        String sql = "INSERT INTO repartidores (Dni_ID, Telefono, Vehiculo_Placa, Codigo_Ubigeo) VALUES (?, ?, ?, ?)";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, repartidor.getDniID());
            ps.setString(2, repartidor.getTelefono());
            ps.setString(3, repartidor.getVehiculoPlaca());
            ps.setInt(4, repartidor.getCodigoUbigeo());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Listar todos los repartidores
    public ArrayList<RepartidorDTO> listarTodo() {
        ArrayList<RepartidorDTO> rep = new ArrayList<>();
        String sql = "SELECT Repartidor_ID, Dni_ID, Telefono, Vehiculo_Placa, Codigo_Ubigeo FROM repartidores";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RepartidorDTO r = new RepartidorDTO();
                r.setRepartidorID(rs.getInt("Repartidor_ID"));
                r.setDniID(rs.getString("Dni_ID"));
                r.setTelefono(rs.getString("Telefono"));
                r.setVehiculoPlaca(rs.getString("Vehiculo_Placa"));
                r.setCodigoUbigeo(rs.getInt("Codigo_Ubigeo"));
                rep.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rep;
    }

    // Obtener repartidor por ID
    public RepartidorDTO obtenerRepartidorPorID(int repartidorID) {
        RepartidorDTO repartidor = null;
        String sql = "SELECT Repartidor_ID, Dni_ID, Telefono, Vehiculo_Placa, Codigo_Ubigeo FROM repartidores WHERE Repartidor_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, repartidorID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    repartidor = new RepartidorDTO();
                    repartidor.setRepartidorID(rs.getInt("Repartidor_ID"));
                    repartidor.setDniID(rs.getString("Dni_ID"));
                    repartidor.setTelefono(rs.getString("Telefono"));
                    repartidor.setVehiculoPlaca(rs.getString("Vehiculo_Placa"));
                    repartidor.setCodigoUbigeo(rs.getInt("Codigo_Ubigeo"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return repartidor;
    }

    // Actualizar repartidor
    public void actualizarRepartidor(RepartidorDTO repartidor) {
        String sql = "UPDATE repartidores SET Dni_ID = ?, Telefono = ?, Vehiculo_Placa = ?, Codigo_Ubigeo = ? WHERE Repartidor_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, repartidor.getDniID());
            ps.setString(2, repartidor.getTelefono());
            ps.setString(3, repartidor.getVehiculoPlaca());
            ps.setInt(4, repartidor.getCodigoUbigeo());
            ps.setInt(5, repartidor.getRepartidorID());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Eliminar repartidor por ID
    public void eliminarRepartidor(int repartidorID) throws SQLException {
        String sql = "DELETE FROM repartidores WHERE Repartidor_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, repartidorID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    // Obtener placa del vehículo por repartidor ID
    public String obtenerRepartidorPlacaPorRepartidorID(int repartidorID) {
        String placa = "";
        String sql = "SELECT Vehiculo_Placa FROM repartidores WHERE Repartidor_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, repartidorID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    placa = rs.getString("Vehiculo_Placa");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return placa;
    }
}


