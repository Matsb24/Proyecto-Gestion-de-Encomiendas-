package OrdenarDatos;



import ClasesModeloDTO.UsuarioDTO;
import java.util.ArrayList;

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
