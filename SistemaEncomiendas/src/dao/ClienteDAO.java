
package dao;

import dto.ClienteDTO;
import interfaces.ClienteInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conexion;

public class ClienteDAO implements ClienteInterface {

    private Conexion con = new Conexion(); 

    // Crear cliente (retorna ID generado)
    public int crearCliente(ClienteDTO cliente) {
        String query = "INSERT INTO clientes (NombreCliente, Direccion, Correo) VALUES (?, ?, ?)";
        int clienteID = -1;

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getCorreo());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    clienteID = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clienteID;
    }

    // Buscar cliente por campo
    public ClienteDTO buscarClientePor(String campo, Object valor) {
        ClienteDTO cliente = null;
        List<String> camposPermitidos = Arrays.asList("Cliente_ID", "NombreCliente", "Direccion", "Correo", "Codigo_Ubigeo");

        if (!camposPermitidos.contains(campo)) {
            throw new IllegalArgumentException("Campo no permitido: " + campo);
        }

        String sql = "SELECT Cliente_ID, NombreCliente, Direccion, Correo, Codigo_Ubigeo FROM clientes WHERE " + campo + " = ?";

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setObject(1, valor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteDTO();
                    cliente.setClienteID(rs.getInt("Cliente_ID"));
                    cliente.setNombreCliente(rs.getString("NombreCliente"));
                    cliente.setDireccion(rs.getString("Direccion"));
                    cliente.setCorreo(rs.getString("Correo"));
                    cliente.setUbigeo(rs.getInt("Codigo_Ubigeo"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }

    // Listar todos los clientes
    public List<ClienteDTO> listarTodo() {
        String sql = "SELECT Cliente_ID, NombreCliente, Direccion, Correo, Codigo_Ubigeo FROM clientes";
        List<ClienteDTO> clientes = new ArrayList<>();

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                ClienteDTO cli = new ClienteDTO();
                cli.setClienteID(rs.getInt("Cliente_ID"));
                cli.setNombreCliente(rs.getString("NombreCliente"));
                cli.setDireccion(rs.getString("Direccion"));
                cli.setCorreo(rs.getString("Correo"));
                cli.setUbigeo(rs.getInt("Codigo_Ubigeo"));
                clientes.add(cli);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;
    }

    // Actualizar cliente
    public boolean actualizarCliente(ClienteDTO cliente) {
        String sql = "UPDATE clientes SET NombreCliente = ?, Direccion = ?, Correo = ?, Codigo_Ubigeo = ? WHERE Cliente_ID = ?";

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getCorreo());
            ps.setInt(4, cliente.getUbigeo());
            ps.setInt(5, cliente.getClienteID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // Obtener nombre por ID
    public ClienteDTO obtenerNombreClientePorID(int empresaID) {
        String sql = "SELECT Cliente_ID, NombreCliente FROM clientes WHERE Cliente_ID = ?";
        ClienteDTO nombre = null;

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, empresaID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = new ClienteDTO();
                    nombre.setClienteID(rs.getInt("Cliente_ID"));
                    nombre.setNombreCliente(rs.getString("NombreCliente"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombre;
    }
    
    // Obtener correo por ID
    public ClienteDTO obtenerCorreoClientePorID(int empresaID) {
        String sql = "SELECT Cliente_ID, Correo FROM clientes WHERE Cliente_ID = ?";
        ClienteDTO nombre = null;

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, empresaID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = new ClienteDTO();
                    nombre.setClienteID(rs.getInt("Cliente_ID"));
                    nombre.setCorreo(rs.getString("Correo"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombre;
    }
    
    // Eliminar cliente
    public boolean eliminarCliente(int clienteID) {
        String sql = "DELETE FROM clientes WHERE Cliente_ID = ?";

        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, clienteID);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}


