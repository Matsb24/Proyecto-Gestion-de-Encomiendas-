
package dto;

public class ClienteDTO {
    private int clienteID;
    private String nombreCliente;
    private String direccion;
    private String correo;
    private int Ubigeo;

    // Getters y setters
    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
        public int getUbigeo() {
        return Ubigeo;
    }

    public void setUbigeo(int Ubigeo) {
        this.Ubigeo = Ubigeo;
    }
}

