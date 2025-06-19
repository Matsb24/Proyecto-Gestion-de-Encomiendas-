package OrdenarDatos;


import ClasesModeloDTO.ubigeoDTO;
import java.util.ArrayList;

public class OrdenaUbi {

    
    public void actualizarLista(ArrayList<ubigeoDTO> nuevaLista) {
        lista = nuevaLista;
    }
    
    // Paciente
    private ArrayList<ubigeoDTO> lista;

    public OrdenaUbi() {
        lista = new ArrayList<>();
    }

    public ArrayList<ubigeoDTO> getLista() {
        return lista;
    }

    // Busqueda Secuencial//
    public ubigeoDTO busquedaS(int id) {
        ubigeoDTO Paciente = null;

        boolean estado = false;
        int i = 0;

        while (i < lista.size() && estado == false) {
            if (lista.get(i).getUbiGEO_ID()== id) {
                estado = true;
                Paciente = lista.get(i);
            } else {
                i++;
            }
        }
        return Paciente;
    }
}
