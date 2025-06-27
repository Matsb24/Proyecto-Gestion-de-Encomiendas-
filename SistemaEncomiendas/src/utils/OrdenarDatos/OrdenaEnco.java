/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.OrdenarDatos;

import java.util.ArrayList;

import dto.EncomiendaDTO;

/**
 *
 * @author Matias
 */
public class OrdenaEnco {

    
    public void actualizarLista(ArrayList<EncomiendaDTO> nuevaLista) {
        lista = nuevaLista;
    }
    
    // Paciente
    private ArrayList<EncomiendaDTO> lista;

    public OrdenaEnco() {
        lista = new ArrayList<>();
    }

    public ArrayList<EncomiendaDTO> getLista() {
        return lista;
    }

    // Busqueda Secuencial//
    public EncomiendaDTO busquedaS(int id) {
        EncomiendaDTO Paciente = null;

        boolean estado = false;
        int i = 0;

        while (i < lista.size() && estado == false) {
            if (lista.get(i).getEncomiendaID()== id) {
                estado = true;
                Paciente = lista.get(i);
            } else {
                i++;
            }
        }
        return Paciente;
    }
}
