
package dto;


public class CategoriaDTO {
    
    private int CategoriaID;
    private String Peso;
    private String Ancho;
    private String Largo;

    // Getters y setters

    public int getCategoriaID() {
        return CategoriaID;
    }

    public void setCategoriaID(int CategoriaID) {
        this.CategoriaID = CategoriaID;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String Peso) {
        this.Peso = Peso;
    }

    public String getAncho() {
        return Ancho;
    }

    public void setAncho(String Ancho) {
        this.Ancho = Ancho;
    }

    public String getLargo() {
        return Largo;
    }

    public void setLargo(String Largo) {
        this.Largo = Largo;
    }
}

