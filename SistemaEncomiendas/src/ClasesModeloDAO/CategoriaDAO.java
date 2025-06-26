package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.CategoriaDTO;
import MisInterfaces.CategoriaInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDAO implements CategoriaInterface {
    private Conexion con = new Conexion();

    // Insertar categoría
    public boolean insertar(CategoriaDTO categoria) {
        String query = "INSERT INTO categoria (Categoria_ID, Peso, Ancho, Largo) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setInt(1, categoria.getCategoriaID());
            ps.setString(2, categoria.getPeso());
            ps.setString(3, categoria.getAncho());
            ps.setString(4, categoria.getLargo());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // Listar categorías
    public List<CategoriaDTO> listarCategorias() {
        String sql = "SELECT Categoria_ID, Peso, Ancho, Largo FROM categoria";
        List<CategoriaDTO> categorias = new ArrayList<>();

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                CategoriaDTO c = new CategoriaDTO();
                c.setCategoriaID(rs.getInt("Categoria_ID"));
                c.setPeso(rs.getString("Peso"));
                c.setAncho(rs.getString("Ancho"));
                c.setLargo(rs.getString("Largo"));
                categorias.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categorias;
    }

    // Eliminar categoría por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM categoria WHERE Categoria_ID = ?";

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // Buscar por campo
    public CategoriaDTO buscarCategoriaPor(String tipoCampo, Object valor) {
        String campo;

        switch (tipoCampo.toLowerCase()) {
            case "id":
                campo = "Categoria_ID";
                break;
            case "peso":
                campo = "Peso";
                break;
            case "ancho":
                campo = "Ancho";
                break;
            case "largo":
                campo = "Largo";
                break;
            default:
                throw new IllegalArgumentException("Campo de búsqueda no válido: " + tipoCampo);
        }

        String sql = "SELECT Categoria_ID, Peso, Ancho, Largo FROM categoria WHERE " + campo + " = ?";
        CategoriaDTO categoria = null;

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setObject(1, valor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoria = new CategoriaDTO();
                    categoria.setCategoriaID(rs.getInt("Categoria_ID"));
                    categoria.setPeso(rs.getString("Peso"));
                    categoria.setAncho(rs.getString("Ancho"));
                    categoria.setLargo(rs.getString("Largo"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categoria;
    }
}

