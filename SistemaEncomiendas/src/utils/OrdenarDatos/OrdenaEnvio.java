package utils.OrdenarDatos;


import java.util.ArrayList;

import dto.EnvioDTO;

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
