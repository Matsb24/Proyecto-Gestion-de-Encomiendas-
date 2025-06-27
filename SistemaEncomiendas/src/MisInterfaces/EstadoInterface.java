package MisInterfaces;

import ClasesModeloDTO.EstadoDTO;
import java.util.ArrayList;

public interface EstadoInterface {

    void crearEstado(EstadoDTO estado);

    ArrayList<EstadoDTO> listarTodo();

    void eliminar(int estadoID);

    EstadoDTO obtenerEstadoPorID(int estadoID);

    String obtenerEstadoNombrePorEstadoID(int estadoID);

    String obtenerEstadoDescrPorEstadoID(int estadoID);
}
