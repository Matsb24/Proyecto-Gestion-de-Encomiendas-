package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.CategoriaDTO;
import MisInterfaces.CategoriaInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDAO implements CategoriaInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<CategoriaDTO> cat = new ArrayList<>();

    // Método para insertar una categoría en la BD
    public void insertar(CategoriaDTO categoria) {
        String query = "INSERT INTO categoria (Categoria_ID, Peso, Ancho, Largo) VALUES (?, ?, ?, ?)";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(query);  // Prepara la sentencia SQL
            ps.setInt(1, categoria.getCategoriaID());
            ps.setString(2, categoria.getPeso());
            ps.setString(3, categoria.getAncho());
            ps.setString(4, categoria.getLargo());
            ps.executeUpdate();  // Ejecuta la sentencia SQL
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
    }

    // Método para listar todas las categorías
    public ArrayList<CategoriaDTO> listarTodo() {
        String sql = "SELECT * FROM categoria";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            rs = ps.executeQuery();  // Ejecuta la consulta
            while (rs.next()) {
                // Crea una nueva categoría con los datos obtenidos de la BD
                CategoriaDTO c = new CategoriaDTO();
                c.setCategoriaID(rs.getInt("Categoria_ID"));
                c.setPeso(rs.getString("Peso"));
                c.setAncho(rs.getString("Ancho"));
                c.setLargo(rs.getString("Largo"));
                cat.add(c);  // Añade la categoría a la lista
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return cat;
    }

    // Método para eliminar una categoría por ID
public void eliminar(int id) {
    String sql = "DELETE FROM categoria WHERE Categoria_ID = ?";
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = con.getConexion(); // Abre la conexión
        ps = conn.prepareStatement(sql); // Prepara la sentencia SQL
        ps.setInt(1, id); // Establece el ID en la consulta
        ps.executeUpdate(); // Ejecuta la actualización
    } catch (SQLException ex) {
        Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn); // Cierra los recursos
    }
}

public CategoriaDTO obtenerCategoriaPorID(int categoriaID) {
    String sql = "SELECT * FROM categoria WHERE Categoria_ID = ?";
    Connection conn = null;
    CategoriaDTO categoria = null;
    
    try {
        conn = con.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, categoriaID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            categoria = new CategoriaDTO();
            categoria.setCategoriaID(rs.getInt("Categoria_ID"));
            categoria.setPeso(rs.getString("Peso"));
            categoria.setAncho(rs.getString("Ancho"));
            categoria.setLargo(rs.getString("Largo"));
            // Asigna otros atributos según sea necesario
        }
    } catch (SQLException ex) {
        Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn);  // Asegúrate de cerrar la conexión
    }
    return categoria;
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
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
