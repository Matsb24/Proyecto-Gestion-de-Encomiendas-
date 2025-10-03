package dao;

import dto.DatosPersonalesDTO;
import interfaces.DatosPersonalesInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conexion;

public class DatosPersonalesDAO implements DatosPersonalesInterface {
    private Conexion con = new Conexion();  // Control de la conexión a la BD

    // Método para crear nuevos datos personales
    public boolean crearDatosPersonales(DatosPersonalesDTO datosPersonales) {
    String query = "INSERT INTO datos_personales (Dni_ID, Nombre, Apellido, Direccion) VALUES (?, ?, ?, ?)";
    
    try (
        Connection conn = con.getConexion();
        PreparedStatement ps = conn.prepareStatement(query)
    ) {
        ps.setString(1, datosPersonales.getDniID());
        ps.setString(2, datosPersonales.getNombre());
        ps.setString(3, datosPersonales.getApellido());
        ps.setString(4, datosPersonales.getDireccion());
        return ps.executeUpdate() > 0;
    } catch (SQLException ex) {
        Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}


    public DatosPersonalesDTO buscarDatosPersonalesPor(String campo, Object valor) {
    DatosPersonalesDTO datos = null;

        // Lista de campos válidos para evitar SQL Injection
    List<String> camposPermitidos = Arrays.asList("Dni_ID", "Nombre", "Apellido", "Direccion");

    if (!camposPermitidos.contains(campo)) {
        throw new IllegalArgumentException("Campo no permitido: " + campo);
    }

    String sql = "SELECT Dni_ID, Nombre, Apellido, Direccion FROM datos_personales WHERE " + campo + " = ?";

    try (
        Connection conn = con.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql)
    ) {
        ps.setObject(1, valor);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                datos = new DatosPersonalesDTO();
                datos.setDniID(rs.getString("Dni_ID"));
                datos.setNombre(rs.getString("Nombre"));
                datos.setApellido(rs.getString("Apellido"));
                datos.setDireccion(rs.getString("Direccion"));
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    return datos;
}

    //listar todo
public List<DatosPersonalesDTO> listarDatosPersonales() {
    String sql = "SELECT Dni_ID, Nombre, Apellido, Direccion FROM datos_personales";
    List<DatosPersonalesDTO> datosList = new ArrayList<>();

    try (
        Connection conn = con.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        while (rs.next()) {
            DatosPersonalesDTO datos = new DatosPersonalesDTO();
            datos.setDniID(rs.getString("Dni_ID"));
            datos.setNombre(rs.getString("Nombre"));
            datos.setApellido(rs.getString("Apellido"));
            datos.setDireccion(rs.getString("Direccion"));
            datosList.add(datos);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    return datosList;
}

    // Compatibilidad: exponer listarTodo() para llamadas antiguas desde UI
    public ArrayList<DatosPersonalesDTO> listarTodo() {
        ArrayList<DatosPersonalesDTO> lista = new ArrayList<>();
        for (DatosPersonalesDTO d : listarDatosPersonales()) {
            lista.add(d);
        }
        return lista;
    }

    // Obtener nombre (Nombre) por DNI (compatibilidad)
    public String obtenerNombrePorDNI(String dniID) {
        String nombre = "";
        String sql = "SELECT Nombre FROM datos_personales WHERE Dni_ID = ?";
        try (
            Connection conn = con.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, dniID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("Nombre");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombre;
    }

    // Compatibilidad con nombre antiguo
    public String obtenerDNINombreMoto(String dniID) {
        return obtenerNombrePorDNI(dniID);
    }


    // Método para actualizar los datos personales
    public boolean actualizarDatosPersonales(DatosPersonalesDTO datosPersonales) {
    String query = "UPDATE datos_personales SET Nombre = ?, Apellido = ?, Direccion = ? WHERE Dni_ID = ?";
    
    try (
        Connection conn = con.getConexion();
        PreparedStatement ps = conn.prepareStatement(query)
    ) {
        ps.setString(1, datosPersonales.getNombre());
        ps.setString(2, datosPersonales.getApellido());
        ps.setString(3, datosPersonales.getDireccion());
        ps.setString(4, datosPersonales.getDniID());
        return ps.executeUpdate() > 0;
    } catch (SQLException ex) {
        Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}


    //eliminar dato utilizando dni_id
public boolean eliminarDatosPersonales(String dniID) {
    String query = "DELETE FROM datos_personales WHERE Dni_ID = ?";
    
    try (
        Connection conn = con.getConexion();
        PreparedStatement ps = conn.prepareStatement(query)
    ) {
        ps.setString(1, dniID);
        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException ex) {
        Logger.getLogger(DatosPersonalesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    return false;
}

}
