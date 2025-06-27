package MisInterfaces;

import ClasesModeloDTO.PagoDTO;
import java.util.ArrayList;

public interface PagoInterface {

    void crearPago(PagoDTO pago);

    PagoDTO obtenerPagoPorID(int pagoID);

    void actualizarPago(PagoDTO pago);

    void eliminarPago(int pagoID);

    ArrayList<PagoDTO> listarTodo();
}
