package MisInterfaces;

import ClasesModeloDTO.EnvioDTO;
import java.util.ArrayList;

public interface EnvioInterface {

    void crearEnvio(EnvioDTO envio);

    ArrayList<EnvioDTO> listarTodo();

    EnvioDTO obtenerEnvioPorID(int envioID);

    void actualizarEnvio(EnvioDTO envio);

    void actualizarEstadoEnvio(int envioID, int nuevoEstadoID);

    void eliminarEnvio(int envioID);
}
