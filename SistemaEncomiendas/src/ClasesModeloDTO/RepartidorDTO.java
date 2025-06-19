
package ClasesModeloDTO;

public class RepartidorDTO {
    private int repartidorID;
    private String dniID;
    private String telefono;
    private String vehiculoPlaca;
    private int codigoUbigeo;

    // Getters y setters
    public int getRepartidorID() {
        return repartidorID;
    }

    public void setRepartidorID(int repartidorID) {
        this.repartidorID = repartidorID;
    }

    public String getDniID() {
        return dniID;
    }

    public void setDniID(String dniID) {
        this.dniID = dniID;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getVehiculoPlaca() {
        return vehiculoPlaca;
    }

    public void setVehiculoPlaca(String vehiculoPlaca) {
        this.vehiculoPlaca = vehiculoPlaca;
    }
    
    public int getCodigoUbigeo(){
        return codigoUbigeo;
    }
    
    public void setCodigoUbigeo(int codigoUbigeo){
       this.codigoUbigeo = codigoUbigeo;
    }
}

