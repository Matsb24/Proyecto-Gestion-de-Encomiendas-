
package interfaces;

import java.util.List;

import dto.DatosPersonalesDTO;

public interface DatosPersonalesInterface {

    boolean crearDatosPersonales(DatosPersonalesDTO datosPersonales);

    DatosPersonalesDTO buscarDatosPersonalesPor(String campo, Object valor);

    List<DatosPersonalesDTO> listarDatosPersonales();

    boolean actualizarDatosPersonales(DatosPersonalesDTO datosPersonales);

    boolean eliminarDatosPersonales(String dniID);
}
