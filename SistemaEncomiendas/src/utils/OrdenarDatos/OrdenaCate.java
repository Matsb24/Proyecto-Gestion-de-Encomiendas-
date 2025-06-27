package utils.OrdenarDatos;


import java.util.ArrayList;

import dto.CategoriaDTO;

public class OrdenaCate {

    
    public void actualizarLista(ArrayList<CategoriaDTO> nuevaLista) {
        lista = nuevaLista;
    }
    
    // Paciente
    private ArrayList<CategoriaDTO> lista;

    public OrdenaCate() {
        lista = new ArrayList<>();
    }

    public ArrayList<CategoriaDTO> getLista() {
        return lista;
    }

    // Busqueda Secuencial//
    public CategoriaDTO busquedaS(int id) {
        CategoriaDTO Paciente = null;

        boolean estado = false;
        int i = 0;

        while (i < lista.size() && estado == false) {
            if (lista.get(i).getCategoriaID()== id) {
                estado = true;
                Paciente = lista.get(i);
            } else {
                i++;
            }
        }
        return Paciente;
    }
}
