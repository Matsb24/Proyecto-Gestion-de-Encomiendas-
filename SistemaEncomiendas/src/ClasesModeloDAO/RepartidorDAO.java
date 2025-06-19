/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.RepartidorDTO;
import MisInterfaces.RepartidorInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepartidorDAO implements RepartidorInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<RepartidorDTO> rep = new ArrayList<>();

    // Método para crear un nuevo repartidor
    public void crearRepartidor(RepartidorDTO repartidor) {
        String sql = "INSERT INTO repartidores (Dni_ID, Telefono, Vehiculo_Placa, Codigo_Ubigeo) VALUES (?, ?, ?, ?)";
        try (Connection conn = con.getConexion()) {  
            ps = conn.prepareStatement(sql);  
            ps.setString(1, repartidor.getDniID());
            ps.setString(2, repartidor.getTelefono());
            ps.setString(3, repartidor.getVehiculoPlaca());
            ps.setInt(4, repartidor.getCodigoUbigeo());
            ps.executeUpdate();  
        } catch (SQLException ex) {
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        // Método para listar todas las categorías
    public ArrayList<RepartidorDTO> listarTodo() {
    String sql = "SELECT * FROM repartidores";
    ArrayList<RepartidorDTO> rep = new ArrayList<>(); 
    try (Connection conn = con.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) { 

        while (rs.next()) {
            // Crea una nueva encomienda con los datos obtenidos de la BD
            RepartidorDTO r = new RepartidorDTO();
                    r.setRepartidorID(rs.getInt("Repartidor_ID"));
                    r.setDniID(rs.getString("Dni_ID"));
                    r.setTelefono(rs.getString("Telefono"));
                    r.setVehiculoPlaca(rs.getString("Vehiculo_Placa"));
                    r.setCodigoUbigeo(rs.getInt("Codigo_Ubigeo"));
            rep.add(r); 
        }
    } catch (SQLException ex) {
        Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rep; 
}
    
    
    
    // Método para obtener un repartidor por ID
    public RepartidorDTO obtenerRepartidorPorID(int repartidorID) {
        String sql = "SELECT * FROM repartidores WHERE Repartidor_ID = ?";
        RepartidorDTO repartidor = null;
        try (Connection conn = con.getConexion()) {  
            ps = conn.prepareStatement(sql);  
            ps.setInt(1, repartidorID);
            rs = ps.executeQuery();  
            if (rs.next()) {
                
                repartidor = new RepartidorDTO();
                    repartidor.setRepartidorID(rs.getInt("Repartidor_ID"));
                    repartidor.setDniID(rs.getString("Dni_ID"));
                    repartidor.setTelefono(rs.getString("Telefono"));
                    repartidor.setVehiculoPlaca(rs.getString("Vehiculo_Placa"));
                    repartidor.setCodigoUbigeo(rs.getInt("Codigo_Ubigeo"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repartidor;
    }

    // Método para actualizar un repartidor
    public void actualizarRepartidor(RepartidorDTO repartidor) {
        String sql = "UPDATE repartidores SET Dni_ID = ?, Telefono = ?, Vehiculo_Placa = ?, Codigo_Ubigeo = ? WHERE Repartidor_ID = ?";
        try (Connection conn = con.getConexion()) {  
            ps = conn.prepareStatement(sql);  
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

    
   // Método para eliminar un repartidor por ID
public void eliminarRepartidor(int repartidorID) throws SQLException { // Añadir "throws SQLException"
    String sql = "DELETE FROM repartidores WHERE Repartidor_ID = ?";
    try (Connection conn = con.getConexion()) {  
        ps = conn.prepareStatement(sql);  
        ps.setInt(1, repartidorID);  
        ps.executeUpdate();  
    } catch (SQLException ex) {
        Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        throw ex; // Lanza la excepción para que pueda ser manejada en el controlador
    }
}

    public String obtenerRepartidorPlacaPorRepartidorID(int RepartID) {
        String PlacaRepatidor = "";
        String sql = "SELECT Vehiculo_Placa FROM repartidores WHERE Repartidor_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, RepartID);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                PlacaRepatidor = rs.getString("Vehiculo_Placa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return PlacaRepatidor;
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
            Logger.getLogger(RepartidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        } 

