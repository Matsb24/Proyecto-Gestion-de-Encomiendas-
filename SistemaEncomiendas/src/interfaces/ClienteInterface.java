
package interfaces;

import java.util.ArrayList;

import dto.ClienteDTO;

public interface ClienteInterface {
    
    int crearCliente(ClienteDTO cliente);
    
    ClienteDTO buscarClientePor(String campo, Object valor);
    
    ArrayList<ClienteDTO> listarTodo();
    
    boolean actualizarCliente(ClienteDTO cliente);
    
    boolean eliminarCliente(int clienteID);
}

