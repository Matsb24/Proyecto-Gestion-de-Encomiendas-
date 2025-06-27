package interfaces;

import java.util.List;

import dto.CategoriaDTO;

public interface CategoriaInterface {
    
    boolean insertar(CategoriaDTO categoria);

    List<CategoriaDTO> listarCategorias();

    boolean eliminar(int id);

    CategoriaDTO buscarCategoriaPor(String tipoCampo, Object valor);
}
