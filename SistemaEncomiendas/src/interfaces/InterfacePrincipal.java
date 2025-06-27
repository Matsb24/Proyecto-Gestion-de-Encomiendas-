package interfaces;
import java.util.List;

public interface InterfacePrincipal<T, K> {
    
    public boolean insertar(T obj);
    
    public boolean eliminar(K id);
    
    public boolean actualizar(T obj);
    
    public T obtenerPorId(K id);

    public List<T> listarTodo();
}
