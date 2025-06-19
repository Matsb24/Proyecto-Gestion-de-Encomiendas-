package ClasesModeloDAO;

import BasedeDatos.Conexion;
import ClasesModeloDTO.ClienteDTO;
import ClasesModeloDTO.EmpresasDTO;
import ClasesModeloDTO.EncomiendaDTO;
import ClasesModeloDTO.EstadoDTO;
import ClasesModeloDTO.PagoDTO;
import ClasesModeloDTO.RepartidorDTO;
import MisInterfaces.EncomiendaInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncomiendaDAO implements EncomiendaInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<EncomiendaDTO> enco = new ArrayList<>();
    
    // Método para insertar una encomienda en la BD
public void crearEncomienda(EncomiendaDTO encomienda) {
    String sql = "INSERT INTO encomiendas (Categoria_ID, Empresa_ID, Destino, Estado_ID, Fecha) VALUES (?, ?, ?, ?, ?)";
    Connection conn = null;
    PreparedStatement ps = null;  // Declaración para la consulta SQL
    
    try {
        conn = con.getConexion();  // Abre la conexión
        ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
        ps.setString(1, encomienda.getCategoriaID());
        ps.setInt(2, encomienda.getEmpresaID());
        ps.setString(3, encomienda.getDestino());
        ps.setInt(4, encomienda.getEstadoID());
        ps.setDate(5, new java.sql.Date(encomienda.getFecha().getTime()));

    } catch (SQLException ex) {
        ex.printStackTrace();  // Muestra el error en la consola
    }
}

    public ArrayList<EncomiendaDTO> listarTodo() {
    String sql = "SELECT * FROM encomiendas";
    ArrayList<EncomiendaDTO> enco = new ArrayList<>(); 
    try (Connection conn = con.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) { 

        while (rs.next()) {
            // Crea una nueva encomienda con los datos obtenidos de la BD
            EncomiendaDTO e = new EncomiendaDTO();
            e.setEncomiendaID(rs.getInt("Encomienda_ID"));
            e.setCategoriaID(rs.getString("Categoria_ID"));
            e.setDestino(rs.getString("Destino"));
            e.setEstadoID(rs.getInt("Estado_ID"));
            e.setFecha(rs.getDate("Fecha"));
            enco.add(e); 
        }
    } catch (SQLException ex) {
        Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return enco; 
}

public ArrayList<EncomiendaDTO> listarEncomiendasCompletas() {
    ArrayList<EncomiendaDTO> listaEncomiendas = new ArrayList<>();

    String sql = "SELECT e.Envio_ID, en.Encomienda_ID, en.Categoria_ID, en.Destino, en.Fecha, " +
                 "c.NombreCliente AS clienteNombre, c.Correo AS clienteCorreo, " +
                 "emp.Nombre_Empresa AS empresaNombre, " +
                 "r.Vehiculo_Placa AS repartidorVehiculo, " +
                 "es.Nombre_Estado AS estadoDescripcion, " +
                 "p.Precio AS pagoMonto, p.Tipo_Pago AS pagoTipo " +
                 "FROM envio e " +  // Envío como tabla central
                 "JOIN encomiendas en ON e.Encomienda_ID = en.Encomienda_ID " +
                 "JOIN clientes c ON e.Cliente_ID = c.Cliente_ID " +   // Relación cliente
                 "JOIN empresas emp ON en.Empresa_ID = emp.Empresa_ID " +  // Relación empresa
                 "JOIN repartidores r ON e.Repartidor_ID = r.Repartidor_ID " +  // Relación repartidor
                 "JOIN estados es ON en.Estado_ID = es.Estado_ID " +  // Relación estado de encomienda
                 "JOIN pagos p ON e.Cliente_ID = p.Cliente_ID";  // Relación pago (si el pago está relacionado con el cliente)

    try (Connection conn = con.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            EncomiendaDTO encomienda = new EncomiendaDTO();
            encomienda.setEncomiendaID(rs.getInt("Encomienda_ID"));
            encomienda.setCategoriaID(rs.getString("Categoria_ID"));
            encomienda.setDestino(rs.getString("Destino"));
            encomienda.setFecha(rs.getDate("Fecha"));

            // Llenar los datos del cliente
            ClienteDTO cliente = new ClienteDTO();
            cliente.setNombreCliente(rs.getString("clienteNombre"));
            cliente.setCorreo(rs.getString("clienteCorreo"));
            encomienda.setCliente(cliente);

            // Llenar los datos de la empresa
            EmpresasDTO empresa = new EmpresasDTO();
            empresa.setNombre_Empresa(rs.getString("empresaNombre"));
            encomienda.setEmpresa(empresa);

            // Llenar los datos del repartidor
            RepartidorDTO repartidor = new RepartidorDTO();
            repartidor.setVehiculoPlaca(rs.getString("repartidorVehiculo"));
            encomienda.setRepartidor(repartidor);

            // Llenar los datos del estado
            EstadoDTO estado = new EstadoDTO();
            estado.setDescripcion(rs.getString("estadoDescripcion"));
            encomienda.setEstado(estado);

            // Llenar los datos del pago
            PagoDTO pago = new PagoDTO();
            pago.setPrecio(rs.getBigDecimal("pagoMonto"));
            pago.setTipoPago(rs.getString("pagoTipo"));
            encomienda.setPago(pago);

            listaEncomiendas.add(encomienda);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaEncomiendas;
}
    
    // Método para obtener una encomienda por ID
public EncomiendaDTO obtenerEncomiendaPorID(int encomiendaID) {
    String sql = "SELECT * FROM encomiendas WHERE Encomienda_ID = ?";
    EncomiendaDTO encomienda = null;
    Connection conn = null; // Conexión local
    try {
        conn = con.getConexion();  // Abre la conexión
        ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
        ps.setInt(1, encomiendaID);
        rs = ps.executeQuery();  // Ejecuta la consulta
        
        if (rs.next()) {
            encomienda = new EncomiendaDTO();
            encomienda.setEncomiendaID(rs.getInt("Encomienda_ID")); // Setea el ID de la encomienda
            encomienda.setCategoriaID(rs.getString("Categoria_ID")); // Setea la categoría
            encomienda.setEmpresaID(rs.getInt("Empresa_ID")); // Setea el ID de la encomienda
            encomienda.setDestino(rs.getString("Destino")); // Setea el destino
            encomienda.setEstadoID(rs.getInt("Estado_ID")); // Setea el estado
            encomienda.setFecha(rs.getDate("Fecha")); // Setea la fecha
        }
    } catch (SQLException ex) {
        Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn);  // Cierra los recursos
    }
    return encomienda; // Devuelve el objeto EncomiendaDTO
}

    // Método para actualizar una encomienda
    public void actualizarEncomienda(EncomiendaDTO encomienda) {
        String sql = "UPDATE encomiendas SET Categoria_ID = ?, Empresa_ID = ?, Destino = ?, Estado_ID = ?, Fecha = ? WHERE Encomienda_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setString(1, encomienda.getCategoriaID());
            ps.setInt(2, encomienda.getEmpresaID());
            ps.setString(3, encomienda.getDestino());
            ps.setInt(4, encomienda.getEstadoID());
            ps.setDate(5, new java.sql.Date(encomienda.getFecha().getTime()));
            ps.setInt(6, encomienda.getEncomiendaID());
            ps.executeUpdate();  // Ejecuta la actualización
        } catch (SQLException ex) {
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
    }

    public void actualizarEstadoEncomienda(int encomiendaID, int estadoID) {
    String sql = "UPDATE encomiendas SET Estado_ID = ? WHERE Encomienda_ID = ?";
    Connection conn = null;
    try {
        conn = con.getConexion();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, estadoID);  // Establece el nuevo Estado_ID
        ps.setInt(2, encomiendaID);  // Establece el Encomienda_ID para actualizar
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cerrarRecursos(conn);
    }
}
    
    // Método para eliminar una encomienda por ID
    public void eliminarEncomienda(int encomiendaID) {
        String sql = "DELETE FROM encomiendas WHERE Encomienda_ID = ?";
        Connection conn = null; // Conexión local
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, encomiendaID);
            ps.executeUpdate();  // Ejecuta la eliminación
        } catch (SQLException ex) {
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);  // Cierra los recursos
        }
    }

        public int obtenerEmpresaIDPorEncomiendaID(int EstadoID) {
        int EmpresaID = 0;
        String sql = "SELECT Empresa_ID FROM encomiendas WHERE Encomienda_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, EstadoID);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                EmpresaID = rs.getInt("Empresa_ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return EmpresaID;
    }
        
        public String obtenerDestinoPorEncomiendaID(int EncomiendaID) {
        String Destino = "";
        String sql = "SELECT Destino FROM encomiendas WHERE Encomienda_ID = ?";
        Connection conn = null;
        try {
            conn = con.getConexion();  // Abre la conexión
            ps = conn.prepareStatement(sql);  // Prepara la sentencia SQL
            ps.setInt(1, EncomiendaID);  // Establece el parámetro
            rs = ps.executeQuery();  // Ejecuta la consulta
            
            if (rs.next()) {
                Destino = rs.getString("Destino");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(conn);
        }
        return Destino;
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
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

