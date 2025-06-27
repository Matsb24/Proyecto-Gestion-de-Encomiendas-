package MisInterfaces;

import ClasesModeloDTO.EncomiendaDTO;
import java.util.ArrayList;

public interface EncomiendaInterface {

    void crearEncomienda(EncomiendaDTO encomienda);

    ArrayList<EncomiendaDTO> listarTodo();

    ArrayList<EncomiendaDTO> listarEncomiendasCompletas();

    EncomiendaDTO obtenerEncomiendaPorID(int encomiendaID);

    void actualizarEncomienda(EncomiendaDTO encomienda);

    void actualizarEstadoEncomienda(int encomiendaID, int estadoID);

    Object obtenerCampoEncomiendaPorID(int encomiendaID, String campo);

    void eliminarEncomienda(int encomiendaID);
}
