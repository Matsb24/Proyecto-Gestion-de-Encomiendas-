
package dto;

import java.util.Date;

public class EnvioDTO {
    private int envioID;
    private int encomiendaID;
    private Date fechaEnvio;
    private Date fechaEntrega;
    private int estadoID;
    private int repartidorID;
    private int clienteID;

    // Getters y setters
    public int getEnvioID() {
        return envioID;
    }

    public void setEnvioID(int envioID) {
        this.envioID = envioID;
    }

    public int getEncomiendaID() {
        return encomiendaID;
    }

    public void setEncomiendaID(int encomiendaID) {
        this.encomiendaID = encomiendaID;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(int estadoID) {
        this.estadoID = estadoID;
    }

    public int getRepartidorID() {
        return repartidorID;
    }

    public void setRepartidorID(int repartidorID) {
        this.repartidorID = repartidorID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }
}