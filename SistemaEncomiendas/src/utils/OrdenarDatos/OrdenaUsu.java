package utils.OrdenarDatos;



import java.util.ArrayList;

import dto.UsuarioDTO;

public class OrdenaUsu {

    
    public void actualizarLista(ArrayList<UsuarioDTO> nuevaLista) {
        lista = nuevaLista;
    }
    
    // Paciente
    private ArrayList<UsuarioDTO> lista;

    public OrdenaUsu() {
        lista = new ArrayList<>();
    }

    public ArrayList<UsuarioDTO> getLista() {
        return lista;
    }
}
