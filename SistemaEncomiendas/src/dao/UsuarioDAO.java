package dao;

import dto.UsuarioDTO;
import interfaces.UsuarioInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conexion;

public class UsuarioDAO implements UsuarioInterface {

    private final Conexion con = new Conexion();

    // Crear usuario
    public void crearUsuario(UsuarioDTO usuario) {
        String query = "INSERT INTO usuario (Dni_ID, Rol_ID, Telefono, Contraseña) VALUES (?, ?, ?, ?)";
        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, usuario.getDniID());
            ps.setInt(2, usuario.getUsuarioRol());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getContrasena());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Obtener usuario por ID
    public UsuarioDTO obtenerUsuarioPorID(int usuarioID) {
        String query = "SELECT Usuario_ID, Dni_ID, Rol_ID, Telefono, Contraseña FROM usuario WHERE Usuario_ID = ?";
        UsuarioDTO usuario = null;

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, usuarioID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapearUsuario(rs);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }

    // Login
    public UsuarioDTO obtenerUsuarioPorTelefonoYContraseña(String telefono, String contrasena) {
        String query = "SELECT Usuario_ID, Dni_ID, Rol_ID, Telefono, Contraseña FROM usuario WHERE Telefono = ? AND Contraseña = ?";
        UsuarioDTO usuario = null;

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, telefono);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapearUsuario(rs);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }

    // Listar todos los usuarios
    public ArrayList<UsuarioDTO> listarTodo() {
        String query = "SELECT Usuario_ID, Dni_ID, Rol_ID, Telefono, Contraseña FROM usuario";
        ArrayList<UsuarioDTO> listaUsuarios = new ArrayList<>();

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listaUsuarios.add(mapearUsuario(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUsuarios;
    }

    // Actualizar usuario
    public void actualizarUsuario(UsuarioDTO usuario) {
        String query = "UPDATE usuario SET Telefono = ?, Contraseña = ?, Dni_ID = ?, Rol_ID = ? WHERE Usuario_ID = ?";
        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, usuario.getTelefono());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getDniID());
            ps.setInt(4, usuario.getUsuarioRol());
            ps.setInt(5, usuario.getUsuarioID());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Eliminar usuario
    public void eliminarUsuario(int usuarioID) {
        String query = "DELETE FROM usuario WHERE Usuario_ID = ?";
        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, usuarioID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Mapeo de ResultSet a UsuarioDTO
    private UsuarioDTO mapearUsuario(ResultSet rs) throws SQLException {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioID(rs.getInt("Usuario_ID"));
        usuario.setDniID(rs.getString("Dni_ID"));
        usuario.setUsuarioRol(rs.getInt("Rol_ID"));
        usuario.setTelefono(rs.getString("Telefono"));
        usuario.setContrasena(rs.getString("Contraseña"));
        return usuario;
    }
}
