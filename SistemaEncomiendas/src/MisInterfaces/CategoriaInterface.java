package MisInterfaces;

import ClasesModeloDTO.CategoriaDTO;
import java.util.List;

public interface CategoriaInterface {
    
    boolean insertar(CategoriaDTO categoria);

    List<CategoriaDTO> listarCategorias();

    boolean eliminar(int id);

    CategoriaDTO buscarCategoriaPor(String tipoCampo, Object valor);
}
