package dao;

import dto.ClienteDTO;
import dto.EmpresasDTO;
import dto.EncomiendaDTO;
import dto.EstadoDTO;
import dto.PagoDTO;
import dto.RepartidorDTO;
import interfaces.EncomiendaInterface;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conexion;

public class EncomiendaDAO implements EncomiendaInterface {
    private Conexion con = new Conexion();

    public void crearEncomienda(EncomiendaDTO encomienda) {
        String sql = "INSERT INTO encomiendas (Categoria_ID, Empresa_ID, Destino, Estado_ID, Fecha) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = con.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, encomienda.getCategoriaID());
            ps.setInt(2, encomienda.getEmpresaID());
            ps.setString(3, encomienda.getDestino());
            ps.setInt(4, encomienda.getEstadoID());
            ps.setDate(5, new java.sql.Date(encomienda.getFecha().getTime()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<EncomiendaDTO> listarTodo() {
        ArrayList<EncomiendaDTO> enco = new ArrayList<>();
        String sql = "SELECT Encomienda_ID, Categoria_ID, Empresa_ID, Destino, Estado_ID, Fecha FROM encomiendas";
        
        try (Connection conn = con.getConexion(); 
            PreparedStatement ps = conn.prepareStatement(sql); 
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EncomiendaDTO e = new EncomiendaDTO();
                e.setEncomiendaID(rs.getInt("Encomienda_ID"));
                e.setCategoriaID(rs.getString("Categoria_ID"));
                e.setEmpresaID(rs.getInt("Empresa_ID")); // esta estaba faltando en el original
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
                     "FROM envio e " +
                     "JOIN encomiendas en ON e.Encomienda_ID = en.Encomienda_ID " +
                     "JOIN clientes c ON e.Cliente_ID = c.Cliente_ID " +
                     "JOIN empresas emp ON en.Empresa_ID = emp.Empresa_ID " +
                     "JOIN repartidores r ON e.Repartidor_ID = r.Repartidor_ID " +
                     "JOIN estados es ON en.Estado_ID = es.Estado_ID " +
                     "JOIN pagos p ON e.Cliente_ID = p.Cliente_ID";

        try (Connection conn = con.getConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                EncomiendaDTO encomienda = new EncomiendaDTO();
                encomienda.setEncomiendaID(rs.getInt("Encomienda_ID"));
                encomienda.setCategoriaID(rs.getString("Categoria_ID"));
                encomienda.setDestino(rs.getString("Destino"));
                encomienda.setFecha(rs.getDate("Fecha"));

                ClienteDTO cliente = new ClienteDTO();
                cliente.setNombreCliente(rs.getString("clienteNombre"));
                cliente.setCorreo(rs.getString("clienteCorreo"));
                encomienda.setCliente(cliente);

                EmpresasDTO empresa = new EmpresasDTO();
                empresa.setNombre_Empresa(rs.getString("empresaNombre"));
                encomienda.setEmpresa(empresa);

                RepartidorDTO repartidor = new RepartidorDTO();
                repartidor.setVehiculoPlaca(rs.getString("repartidorVehiculo"));
                encomienda.setRepartidor(repartidor);

                EstadoDTO estado = new EstadoDTO();
                estado.setDescripcion(rs.getString("estadoDescripcion"));
                encomienda.setEstado(estado);

                PagoDTO pago = new PagoDTO();
                pago.setPrecio(rs.getBigDecimal("pagoMonto"));
                pago.setTipoPago(rs.getString("pagoTipo"));
                encomienda.setPago(pago);

                listaEncomiendas.add(encomienda);
            }
        } catch (SQLException e) {
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaEncomiendas;
    }

    public EncomiendaDTO obtenerEncomiendaPorID(int encomiendaID) {
        String sql = "SELECT * FROM encomiendas WHERE Encomienda_ID = ?";
        EncomiendaDTO encomienda = null;
        try (Connection conn = con.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, encomiendaID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    encomienda = new EncomiendaDTO();
                    encomienda.setEncomiendaID(rs.getInt("Encomienda_ID"));
                    encomienda.setCategoriaID(rs.getString("Categoria_ID"));
                    encomienda.setEmpresaID(rs.getInt("Empresa_ID"));
                    encomienda.setDestino(rs.getString("Destino"));
                    encomienda.setEstadoID(rs.getInt("Estado_ID"));
                    encomienda.setFecha(rs.getDate("Fecha"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encomienda;
    }

    public void actualizarEncomienda(EncomiendaDTO encomienda) {
        String sql = "UPDATE encomiendas SET Categoria_ID = ?, Empresa_ID = ?, Destino = ?, Estado_ID = ?, Fecha = ? WHERE Encomienda_ID = ?";
        try (Connection conn = con.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, encomienda.getCategoriaID());
            ps.setInt(2, encomienda.getEmpresaID());
            ps.setString(3, encomienda.getDestino());
            ps.setInt(4, encomienda.getEstadoID());
            ps.setDate(5, new java.sql.Date(encomienda.getFecha().getTime()));
            ps.setInt(6, encomienda.getEncomiendaID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarEstadoEncomienda(int encomiendaID, int estadoID) {
        String sql = "UPDATE encomiendas SET Estado_ID = ? WHERE Encomienda_ID = ?";
        try (Connection conn = con.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, estadoID);
            ps.setInt(2, encomiendaID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object obtenerCampoEncomiendaPorID(int encomiendaID, String campo) {
        List<String> camposPermitidos = Arrays.asList("Destino", "Empresa_ID", "Estado_ID", "Categoria_ID", "Fecha");
        if (!camposPermitidos.contains(campo)) {
            throw new IllegalArgumentException("Campo no permitido: " + campo);
        }

        String sql = "SELECT " + campo + " FROM encomiendas WHERE Encomienda_ID = ?";
        Object resultado = null;

        try (Connection conn = con.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, encomiendaID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resultado = rs.getObject(campo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public void eliminarEncomienda(int encomiendaID) {
        String sql = "DELETE FROM encomiendas WHERE Encomienda_ID = ?";
        try (Connection conn = con.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, encomiendaID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EncomiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
