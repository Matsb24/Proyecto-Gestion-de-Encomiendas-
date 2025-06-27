
package MisInterfaces;

import ClasesModeloDTO.EmpresasDTO;
import java.util.List;

public interface EmpresasInterface {

    boolean insertar(EmpresasDTO empresa);

    List<EmpresasDTO> listarEmpresas();

    boolean eliminar(int id);

    EmpresasDTO obtenerEmpresaPorID(int empresaID);

    int obtenerEmpresaIDPorNombre(String nombreEmpresa);

    String obtenerNombreEmpresaPorID(int empresaID);
}
