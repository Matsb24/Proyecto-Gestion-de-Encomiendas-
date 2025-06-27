package MisInterfaces;

import ClasesModeloDTO.UsuarioDTO;
import java.util.ArrayList;

public interface UsuarioInterface {

    void crearUsuario(UsuarioDTO usuario);

    UsuarioDTO obtenerUsuarioPorID(int usuarioID);

    UsuarioDTO obtenerUsuarioPorTelefonoYContraseña(String telefono, String contrasena);

    ArrayList<UsuarioDTO> listarTodo();

    void actualizarUsuario(UsuarioDTO usuario);

    void eliminarUsuario(int usuarioID);
}
