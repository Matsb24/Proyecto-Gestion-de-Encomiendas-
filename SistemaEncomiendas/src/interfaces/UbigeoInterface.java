package interfaces;

import java.util.ArrayList;

import dto.ubigeoDTO;

public interface UbigeoInterface {

    void insertar(ubigeoDTO ubige);

    ArrayList<ubigeoDTO> listarTodo();

    ubigeoDTO obtenerUbigeoPorID(String destinoID);

    void eliminar(int id);

    ArrayList<String> listarDistritos();

    int obtenerUbigeoIDPorDistrito(String nombreDistrito);

    String obtenerDistritoPorCodigoUbigeo(int codigoUbigeo);
}
