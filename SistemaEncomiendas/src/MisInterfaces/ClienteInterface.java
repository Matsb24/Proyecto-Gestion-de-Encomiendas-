
package MisInterfaces;

import ClasesModeloDTO.ClienteDTO;
import java.util.List;

public interface ClienteInterface {
    
    int crearCliente(ClienteDTO cliente);
    
    ClienteDTO buscarClientePor(String campo, Object valor);
    
    List<ClienteDTO> listarTodo();
    
    boolean actualizarCliente(ClienteDTO cliente);
    
    boolean eliminarCliente(int clienteID);
}

