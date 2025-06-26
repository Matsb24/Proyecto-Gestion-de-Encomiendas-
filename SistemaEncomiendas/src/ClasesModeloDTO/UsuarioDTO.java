
package ClasesModeloDTO;

public class UsuarioDTO {
    private int usuarioID;
    private String dniID;
    private int usuarioRol;
    private String telefono;
    private String contrasena;
    private DatosPersonalesDTO datosPersonales; 

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public DatosPersonalesDTO getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonalesDTO datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public int getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(int usuarioRol) {
        this.usuarioRol = usuarioRol;
    }
}
