package MisInterfaces;

import ClasesModeloDTO.ubigeoDTO;
import java.util.ArrayList;

public interface UbigeoInterface {

    void insertar(ubigeoDTO ubige);

    ArrayList<ubigeoDTO> listarTodo();

    ubigeoDTO obtenerUbigeoPorID(String destinoID);

    void eliminar(int id);

    ArrayList<String> listarDistritos();

    int obtenerUbigeoIDPorDistrito(String nombreDistrito);

    String obtenerDistritoPorCodigoUbigeo(int codigoUbigeo);
}
