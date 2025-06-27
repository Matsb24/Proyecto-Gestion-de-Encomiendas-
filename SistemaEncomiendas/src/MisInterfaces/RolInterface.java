
package MisInterfaces;

import ClasesModeloDTO.RolDTO;
import java.util.ArrayList;

public interface RolInterface {

    void crearRol(RolDTO rol);

    RolDTO obtenerRolPorID(int rolID);

    void actualizarRol(RolDTO rol);

    void eliminarRol(int rolID);

    int obtenerRolIDPorNombre(String nombreRol);

    String obtenerNombrePorRolID(int rolID);

    ArrayList<RolDTO> listarRoles();
}
