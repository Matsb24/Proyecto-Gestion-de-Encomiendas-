
package interfaces;

import java.util.List;

import dto.ClienteDTO;

public interface ClienteInterface {
    
    int crearCliente(ClienteDTO cliente);
    
    ClienteDTO buscarClientePor(String campo, Object valor);
    
    List<ClienteDTO> listarTodo();
    
    boolean actualizarCliente(ClienteDTO cliente);
    
    boolean eliminarCliente(int clienteID);
}

