/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.ubigeoDTO;
import MisInterfaces.UbigeoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UbigeoDAO implements UbigeoInterface {
    private final Conexion con = new Conexion();

    // Insertar nuevo ubigeo
    public void insertar(ubigeoDTO ubige) {
        String sql = "INSERT INTO ubigeo (UbiGEO_ID, Departamento, Provincia, Distrito, Codigo_Ubigeo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ubige.getUbiGEO_ID());
            ps.setString(2, ubige.getDepartamento());
            ps.setString(3, ubige.getProvincia());
            ps.setString(4, ubige.getDistrito());
            ps.setString(5, ubige.getCodigoUbigeo());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Listar todos los ubigeos
    public ArrayList<ubigeoDTO> listarTodo() {
        ArrayList<ubigeoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM ubigeo";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ubigeoDTO u = new ubigeoDTO();
                u.setUbiGEO_ID(rs.getInt("UbiGEO_ID"));
                u.setDepartamento(rs.getString("Departamento"));
                u.setProvincia(rs.getString("Provincia"));
                u.setDistrito(rs.getString("Distrito"));
                u.setCodigoUbigeo(rs.getString("Codigo_Ubigeo"));
                lista.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    // Obtener ubigeo por ID
    public ubigeoDTO obtenerUbigeoPorID(String destinoID) {
        String sql = "SELECT * FROM ubigeo WHERE UbiGEO_ID = ?";
        ubigeoDTO ubigeo = null;

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, destinoID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ubigeo = new ubigeoDTO();
                    ubigeo.setUbiGEO_ID(rs.getInt("UbiGEO_ID"));
                    ubigeo.setDepartamento(rs.getString("Departamento"));
                    ubigeo.setDistrito(rs.getString("Distrito"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ubigeo;
    }

    // Eliminar ubigeo por ID
    public void eliminar(int id) {
        String sql = "DELETE FROM ubigeo WHERE UbiGEO_ID = ?";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Listar distritos únicos
    public ArrayList<String> listarDistritos() {
        ArrayList<String> distritos = new ArrayList<>();
        String sql = "SELECT DISTINCT Distrito FROM ubigeo";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                distritos.add(rs.getString("Distrito"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return distritos;
    }

    // Obtener código Ubigeo por nombre de distrito
    public int obtenerUbigeoIDPorDistrito(String nombreDistrito) {
        String sql = "SELECT Codigo_Ubigeo FROM ubigeo WHERE Distrito = ?";
        int codigo = -1;

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreDistrito);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    codigo = rs.getInt("Codigo_Ubigeo");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codigo;
    }

    // Obtener nombre de distrito por código Ubigeo
    public String obtenerDistritoPorCodigoUbigeo(int codigoUbigeo) {
        String sql = "SELECT Distrito FROM ubigeo WHERE Codigo_Ubigeo = ?";
        String distrito = "";

        try (Connection conn = con.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codigoUbigeo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    distrito = rs.getString("Distrito");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return distrito;
    }
}
