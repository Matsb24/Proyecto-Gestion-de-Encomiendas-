
package MisInterfaces;

import ClasesModeloDTO.DatosPersonalesDTO;
import java.util.List;

public interface DatosPersonalesInterface {

    boolean crearDatosPersonales(DatosPersonalesDTO datosPersonales);

    DatosPersonalesDTO buscarDatosPersonalesPor(String campo, Object valor);

    List<DatosPersonalesDTO> listarDatosPersonales();

    boolean actualizarDatosPersonales(DatosPersonalesDTO datosPersonales);

    boolean eliminarDatosPersonales(String dniID);
}
