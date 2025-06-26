package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.EmpresasDTO;
import MisInterfaces.EmpresasInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpresasDAO implements EmpresasInterface {
    private Conexion con = new Conexion();

    // Insertar nueva empresa
    public boolean insertar(EmpresasDTO empresa) {
        String query = "INSERT INTO empresas (Empresa_ID, Nombre_Empresa) VALUES (?, ?)";

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setInt(1, empresa.getEmpresa_ID());
            ps.setString(2, empresa.getNombre_Empresa());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Listar todas las empresas
    public List<EmpresasDTO> listarEmpresas() {
        String sql = "SELECT Empresa_ID, Nombre_Empresa FROM empresas";
        List<EmpresasDTO> empresas = new ArrayList<>();

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                EmpresasDTO e = new EmpresasDTO();
                e.setEmpresa_ID(rs.getInt("Empresa_ID"));
                e.setNombre_Empresa(rs.getString("Nombre_Empresa"));
                empresas.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empresas;
    }

    // Eliminar empresa por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM empresas WHERE Empresa_ID = ?";

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // Obtener empresa por ID
    public EmpresasDTO obtenerEmpresaPorID(int empresaID) {
        String sql = "SELECT Empresa_ID, Nombre_Empresa FROM empresas WHERE Empresa_ID = ?";
        EmpresasDTO empresa = null;

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, empresaID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    empresa = new EmpresasDTO();
                    empresa.setEmpresa_ID(rs.getInt("Empresa_ID"));
                    empresa.setNombre_Empresa(rs.getString("Nombre_Empresa"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empresa;
    }

    // Obtener ID de empresa por nombre
    public int obtenerEmpresaIDPorNombre(String nombreEmpresa) {
        String sql = "SELECT Empresa_ID FROM empresas WHERE Nombre_Empresa = ?";

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, nombreEmpresa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Empresa_ID");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    // Obtener nombre de empresa por ID
    public String obtenerNombreEmpresaPorID(int empresaID) {
        String sql = "SELECT Nombre_Empresa FROM empresas WHERE Empresa_ID = ?";
        String nombre = "";

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, empresaID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("Nombre_Empresa");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombre;
    }
}

