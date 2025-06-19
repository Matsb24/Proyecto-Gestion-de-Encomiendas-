
package ClasesModeloDTO;

public class RolDTO {
    private int rolID;
    private String nombre;

    public RolDTO(int rolID, String nombre) {
        this.rolID = rolID;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getRolID() {
        return rolID;
    }

    public void setRolID(int rolID) {
        this.rolID = rolID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

