package dto;

import java.util.Date;

public class EncomiendaDTO {
    private int encomiendaID;
    private String categoriaID;
    private String destino;
    private int EmpresaID;
    private int estadoID;
    private Date fecha;


    // Agregamos referencias a otros DTOs
    private ClienteDTO cliente;
    private EmpresasDTO empresa;
    private RepartidorDTO repartidor;
    private EstadoDTO estado;
    private PagoDTO pago;

    // Getters y Setters para los nuevos campos
    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public EmpresasDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresasDTO empresa) {
        this.empresa = empresa;
    }
    
    public int getEmpresaID() {
        return EmpresaID;
    }

    public void setEmpresaID(int empresa) {
        this.EmpresaID = empresa;
    }

    public RepartidorDTO getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(RepartidorDTO repartidor) {
        this.repartidor = repartidor;
    }

    public EstadoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }
    
    public int getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(int estado) {
        this.estadoID = estado;
    }

    public PagoDTO getPago() {
        return pago;
    }

    public void setPago(PagoDTO pago) {
        this.pago = pago;
    }

    // Getters y setters originales
    public int getEncomiendaID() {
        return encomiendaID;
    }

    public void setEncomiendaID(int encomiendaID) {
        this.encomiendaID = encomiendaID;
    }

    public String getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(String categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
