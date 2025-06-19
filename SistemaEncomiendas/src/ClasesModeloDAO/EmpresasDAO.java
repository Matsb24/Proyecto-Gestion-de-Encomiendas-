package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.EmpresasDTO;
import MisInterfaces.CategoriaInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpresasDAO implements CategoriaInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<EmpresasDTO> Emp = new ArrayList<>();

    // Método para insertar una categoría en la BD
    public void insertar(EmpresasDTO categoria) {
        String query = "INSERT INTO empresas (Empresa_ID, Nombre_Empresa) VALUES (?, ?)";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(query);  // Prepara la sentencia SQL
            ps.setInt(1, categoria.getEmpresa_ID());
            ps.setString(2, categoria.getNombre_Empresa());
            ps.executeUpdate();  // Ejecuta la sentencia SQL
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
    }

    // Método para listar todas las categorías
    public ArrayList<EmpresasDTO> listarTodo() {
        String sql = "SELECT * FROM empresas";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            rs = ps.executeQuery();  // Ejecuta la consulta
            while (rs.next()) {
                // Crea una nueva categoría con los datos obtenidos de la BD
                EmpresasDTO e = new EmpresasDTO();
                e.setEmpresa_ID(rs.getInt("Empresa_ID"));
                e.setNombre_Empresa(rs.getString("Nombre_Empresa"));
                Emp.add(e);  // Añade la categoría a la lista
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return Emp;
    }

    // Método para eliminar una categoría por ID
public void eliminar(int id) {
    String sql = "DELETE FROM empresas WHERE Empresa_ID = ?"; // Ajusta el nombre de la tabla y columna según sea necesario
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = con.getConexion(); // Abre la conexión
        ps = conn.prepareStatement(sql); // Prepara la sentencia SQL
        ps.setInt(1, id); // Establece el ID en la consulta
        ps.executeUpdate(); // Ejecuta la actualización
    } catch (SQLException ex) {
        Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn); // Cierra los recursos
    }
}

public EmpresasDTO obtenerCategoriaPorID(String categoriaID) {
    String sql = "SELECT * FROM empresas WHERE Empresa_ID = ?";
    Connection conn = null;
    EmpresasDTO empresas = null;
    
    try {
        conn = con.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, categoriaID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            empresas = new EmpresasDTO();
            empresas.setEmpresa_ID(rs.getInt("Empresa_ID"));
            empresas.setNombre_Empresa(rs.getString("Nombre_Empresa"));
            // Asigna otros atributos según sea necesario
        }
    } catch (SQLException ex) {
        Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn);  // Asegúrate de cerrar la conexión
    }
    return empresas;
}

        public int obtenerEmpresaIDPorNombre(String nombreEmpresa) {
        int empresaID = -1; // Valor por defecto si no se encuentra el distrito
        String sql = "SELECT Empresa_ID FROM empresas WHERE Nombre_Empresa = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setString(1, nombreEmpresa);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                empresaID = rs.getInt("Empresa_ID");  // Obtiene el UbiGEO_ID del distrito
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return empresaID;
    }

            public String obtenerNombreEmpporEmpresaID (int EmpresaID) {
        String NomEmpresa = "";
        String sql = "SELECT Nombre_Empresa FROM empresas WHERE Empresa_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, EmpresaID);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                NomEmpresa = rs.getString("Nombre_Empresa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return NomEmpresa;
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
            Logger.getLogger(EmpresasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
