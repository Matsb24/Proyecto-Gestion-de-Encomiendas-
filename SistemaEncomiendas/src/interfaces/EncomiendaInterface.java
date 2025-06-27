package interfaces;

import java.util.ArrayList;

import dto.EncomiendaDTO;

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
