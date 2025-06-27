package interfaces;

import java.util.ArrayList;

import dto.PagoDTO;

public interface PagoInterface {

    void crearPago(PagoDTO pago);

    PagoDTO obtenerPagoPorID(int pagoID);

    void actualizarPago(PagoDTO pago);

    void eliminarPago(int pagoID);

    ArrayList<PagoDTO> listarTodo();
}
