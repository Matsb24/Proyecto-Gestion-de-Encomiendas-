
package dto;

public class EstadoDTO {

    /**
     * @return the nombre_estado
     */
    public String getNombre_estado() {
        return nombre_estado;
    }

    /**
     * @param nombre_estado the nombre_estado to set
     */
    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }
    private int estadoID;
    private String descripcion;
    protected String nombre_estado;


    // Getters y setters
    public int getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(int estadoID) {
        this.estadoID = estadoID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

