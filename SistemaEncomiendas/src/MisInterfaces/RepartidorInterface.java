package MisInterfaces;

import ClasesModeloDTO.RepartidorDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RepartidorInterface {

    void crearRepartidor(RepartidorDTO repartidor);

    ArrayList<RepartidorDTO> listarTodo();

    RepartidorDTO obtenerRepartidorPorID(int repartidorID);

    void actualizarRepartidor(RepartidorDTO repartidor);

    void eliminarRepartidor(int repartidorID) throws SQLException;

    String obtenerRepartidorPlacaPorRepartidorID(int repartidorID);
}
