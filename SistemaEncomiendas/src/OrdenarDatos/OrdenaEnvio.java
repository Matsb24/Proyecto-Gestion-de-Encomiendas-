package OrdenarDatos;


import ClasesModeloDTO.EnvioDTO;
import java.util.ArrayList;

public class OrdenaEnvio {

    
    public void actualizarLista(ArrayList<EnvioDTO> nuevaLista) {
        lista = nuevaLista;
    }
    
    // Paciente
    private ArrayList<EnvioDTO> lista;

    public OrdenaEnvio() {
        lista = new ArrayList<>();
    }

    public ArrayList<EnvioDTO> getLista() {
        return lista;
    }

}
