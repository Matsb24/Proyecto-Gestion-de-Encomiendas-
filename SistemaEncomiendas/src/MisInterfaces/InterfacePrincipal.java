package MisInterfaces;
import java.util.List;

public interface InterfacePrincipal<T, K> {
    public boolean insertar(T obj);   // Insertar una entidad de tipo T
    public boolean eliminar(K id);    // Eliminar por clave primaria de tipo K
    public boolean actualizar(T obj); // Actualizar una entidad de tipo T
    public T obtenerPorId(K id);      // Obtener una entidad por clave primaria de tipo K
    public List<T> listarTodo();      // Listar todas las entidades
}
