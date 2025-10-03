/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.RolDTO;
import interfaces.RolInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conexion;

public class RolDAO implements RolInterface {
    private final Conexion con = new Conexion();

    // Crear un nuevo rol
    public void crearRol(RolDTO rol) {
        String sql = "INSERT INTO rol (Rol_ID, Nom_rol) VALUES (?, ?)";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rol.getRolID());
            ps.setString(2, rol.getNombre());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Obtener rol por ID
    public RolDTO obtenerRolPorID(int rolID) {
        String sql = "SELECT Rol_ID, Nom_rol FROM rol WHERE Rol_ID = ?";
        RolDTO rol = null;

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rolID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rol = new RolDTO(rs.getInt("Rol_ID"), rs.getString("Nom_rol"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rol;
    }

    // Actualizar rol
    public void actualizarRol(RolDTO rol) {
        String sql = "UPDATE rol SET Nom_rol = ? WHERE Rol_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, rol.getNombre());
            ps.setInt(2, rol.getRolID());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Eliminar rol
    public void eliminarRol(int rolID) {
        String sql = "DELETE FROM rol WHERE Rol_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rolID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Obtener ID de rol por nombre
    public int obtenerRolIDPorNombre(String nombreRol) {
        String sql = "SELECT Rol_ID FROM rol WHERE Nom_rol = ?";
        int rolID = -1;

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rolID = rs.getInt("Rol_ID");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rolID;
    }

    // Obtener nombre de rol por ID
    public String obtenerNombrePorRolID(int rolID) {
        String sql = "SELECT Nom_rol FROM rol WHERE Rol_ID = ?";
        String nombre = "";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rolID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("Nom_rol");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombre;
    }

    // Listar todos los roles
    public ArrayList<RolDTO> listarRoles() {
        ArrayList<RolDTO> listaRoles = new ArrayList<>();
        String sql = "SELECT Rol_ID, Nom_rol FROM rol";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RolDTO rol = new RolDTO(rs.getInt("Rol_ID"), rs.getString("Nom_rol"));
                listaRoles.add(rol);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaRoles;
    }

    // Compatibilidad: wrapper que expone el mismo nombre usado por los panels viejos
    public ArrayList<RolDTO> listarTodo() {
        return listarRoles();
    }
}


