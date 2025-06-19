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

/**
 *
 * @author Matias
 */
public class UbigeoDAO implements  UbigeoInterface{
        private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<ubigeoDTO> ubi = new ArrayList<>();
   
    public void insertar(ubigeoDTO ubige) {
        String query = "INSERT INTO ubigeo (UbiGEO_ID, Departamento, Provincia, Distrito, Codigo_Ubigeo) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(query);  // Prepara la sentencia SQL
            ps.setInt(1, ubige.getUbiGEO_ID());
            ps.setString(2, ubige.getDepartamento());
            ps.setString(3, ubige.getProvincia());
            ps.setString(4, ubige.getDistrito());
            ps.setString(5, ubige.getCodigoUbigeo());
            ps.executeUpdate();  // Ejecuta la sentencia SQL
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
    }

    // Método para listar todas las categorías
    public ArrayList<ubigeoDTO> listarTodo() {
        String sql = "SELECT * FROM ubigeo";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            rs = ps.executeQuery();  // Ejecuta la consulta
            while (rs.next()) {
                // Crea una nueva categoría con los datos obtenidos de la BD
                ubigeoDTO u = new ubigeoDTO();
                u.setUbiGEO_ID(rs.getInt("UbiGEO_ID"));
                u.setDepartamento(rs.getString("Departamento"));
                u.setProvincia(rs.getString("Provincia"));
                u.setDistrito(rs.getString("Distrito"));
                u.setCodigoUbigeo(rs.getString("Codigo_Ubigeo"));
                ubi.add(u);  // Añade la categoría a la lista
            }
        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return ubi;
    }

    public ubigeoDTO obtenerUbigeoPorID(String destinoID) {
    ubigeoDTO ubigeo = null;
    String sql = "SELECT * FROM ubigeo WHERE UbiGEO_ID = ?";
    try (Connection conn = con.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, destinoID);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            ubigeo = new ubigeoDTO();
            ubigeo.setUbiGEO_ID(rs.getInt("UbiGEO_ID"));
            ubigeo.setDepartamento(rs.getString("Departamento"));
            ubigeo.setDistrito(rs.getString("Distrito"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ubigeo;
}
    
    // Método para eliminar una categoría por ID
public void eliminar(int id) {
    String sql = "DELETE FROM ubigeo WHERE UbiGEO_ID = ?"; // Ajusta el nombre de la tabla y columna según sea necesario
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = con.getConexion(); // Abre la conexión
        ps = conn.prepareStatement(sql); // Prepara la sentencia SQL
        ps.setInt(1, id); // Establece el ID en la consulta
        ps.executeUpdate(); // Ejecuta la actualización
    } catch (SQLException ex) {
        Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn); // Cierra los recursos
    }
}

public ArrayList<String> listarDistritos() {
    ArrayList<String> distritos = new ArrayList<>();
    String sql = "SELECT DISTINCT Distrito FROM ubigeo";  // Consulta solo los distritos únicos
    Connection conn = null;  // Conexión local
    try {
        conn = con.getConexion();  // Abre la conexión
        ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
        rs = ps.executeQuery();  // Ejecuta la consulta
        while (rs.next()) {
            distritos.add(rs.getString("Distrito"));  // Añade cada distrito a la lista
        }
    } catch (SQLException ex) {
        Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn);  // Cierra los recursos
    }
    return distritos;  // Retorna la lista de distritos
}

    public int obtenerUbigeoIDPorDistrito(String nombreDistrito) {
        int ubigeoID = -1; // Valor por defecto si no se encuentra el distrito
        String sql = "SELECT Codigo_Ubigeo FROM ubigeo WHERE Distrito = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setString(1, nombreDistrito);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                ubigeoID = rs.getInt("Codigo_Ubigeo");  // Obtiene el UbiGEO_ID del distrito
            }
        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return ubigeoID;
    }
    
    public String obtenerDistritoPorCodigoUbigeo(int ubigeo) {
        String EstaID = ""; // Valor por defecto si no se encuentra el distrito
        String sql = "SELECT Distrito FROM ubigeo WHERE Codigo_Ubigeo = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, ubigeo);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                EstaID = rs.getString("Distrito");  // Obtiene el UbiGEO_ID del distrito
            }
        } catch (SQLException ex) {
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return EstaID;
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
            Logger.getLogger(UbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
