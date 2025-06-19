
package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.ClienteDTO;
import MisInterfaces.ClienteInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO implements ClienteInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<ClienteDTO> client = new ArrayList<>();
    
    // Método para insertar un cliente en la BD
public int crearCliente(ClienteDTO cliente) {
    String query = "INSERT INTO clientes (NombreCliente, Direccion, Correo) VALUES (?, ?, ?)";
    Connection conn = null; // Conexión local
    int clienteID = -1;  // Variable para almacenar el Cliente_ID generado
    try {
        conn = con.getConexion();  // Abre la conexión
        ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  // Prepara la sentencia SQL y solicita las claves generadas
        ps.setString(1, cliente.getNombreCliente());
        ps.setString(2, cliente.getDireccion());
        ps.setString(3, cliente.getCorreo());
        ps.executeUpdate();  // Ejecuta la sentencia SQL
        
        // Captura el Cliente_ID generado automáticamente
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            clienteID = rs.getInt(1);  // Obtiene el Cliente_ID
        }
    } catch (SQLException ex) {
        Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn);  // Cierra los recursos después de la operación
    }
    return clienteID;  // Retorna el Cliente_ID generado
}

    // Método para obtener un cliente por ID
    public ClienteDTO obtenerClientePorID(int clienteID) {
        String sql = "SELECT * FROM clientes WHERE Cliente_ID = ?";
        Connection conn = null; // Conexión local
        ClienteDTO cliente = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, clienteID);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            if (rs.next()) {
                cliente = new ClienteDTO();
                cliente.setClienteID(rs.getInt("Cliente_ID"));
                cliente.setNombreCliente(rs.getString("NombreCliente"));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setCorreo(rs.getString("Correo"));
                cliente.setUbigeo(rs.getInt("Codigo_Ubigeo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
        return cliente;
    }

        // Método para listar todas los clientes
    public ArrayList<ClienteDTO> listarTodo() {
        String sql = "SELECT * FROM clientes";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            rs = ps.executeQuery();  // Ejecuta la consulta
            while (rs.next()) {
                // Crea una nueva categoría con los datos obtenidos de la BD
                ClienteDTO cli = new ClienteDTO();
                cli.setClienteID(rs.getInt("Cliente_ID"));
                cli.setNombreCliente(rs.getString("NombreCliente"));
                cli.setDireccion(rs.getString("Direccion"));
                cli.setCorreo(rs.getString("Correo"));
                cli.setUbigeo(rs.getInt("Codigo_Ubigeo"));
                client.add(cli);  // Añade la categoría a la lista
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
        return client;
    }
    
    
    // Método para actualizar un cliente
    public void actualizarCliente(ClienteDTO cliente) {
        String sql = "UPDATE clientes SET NombreCliente = ?, Direccion = ?, Correo = ?, Codigo_Ubigeo = ? WHERE Cliente_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getCorreo());
            ps.setInt(4, cliente.getClienteID());
            ps.setInt(5, cliente.getUbigeo());
            ps.executeUpdate();  // Ejecuta la sentencia SQL
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
    }

    // Método para eliminar un cliente por ID
    public void eliminarCliente(int clienteID) {
        String sql = "DELETE FROM clientes WHERE Cliente_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, clienteID);  // Establece el parámetro
            ps.executeUpdate();  // Ejecuta la actualización
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos después de la operación
        }
    }

    //Cliente nombre utilizando Cliente ID
    public String obtenerNombrePorClienteID(int ClienteID) {
        String Nombre = "";
        String sql = "SELECT NombreCliente FROM clientes WHERE Cliente_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, ClienteID);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                Nombre = rs.getString("NombreCliente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return Nombre;
    }
    
    //Cliente Correo utilizando Cliente ID
        public String obtenerCorreoPorClienteID(int ClienteID) {
        String Correo = "";
        String sql = "SELECT Correo FROM clientes WHERE Cliente_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, ClienteID);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                Correo = rs.getString("Correo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return Correo;
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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

