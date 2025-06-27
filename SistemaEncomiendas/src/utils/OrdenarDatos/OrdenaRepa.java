/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.OrdenarDatos;

import java.util.ArrayList;

import dto.RepartidorDTO;

/**
 *
 * @author Matias
 */
public class OrdenaRepa {

    
    public void actualizarLista(ArrayList<RepartidorDTO> nuevaLista) {
        lista = nuevaLista;
    }
    
    // Paciente
    private ArrayList<RepartidorDTO> lista;

    public OrdenaRepa() {
        lista = new ArrayList<>();
    }

    public ArrayList<RepartidorDTO> getLista() {
        return lista;
    }

    // Busqueda Secuencial//
    public RepartidorDTO busquedaS(int id) {
        RepartidorDTO Paciente = null;

        boolean estado = false;
        int i = 0;

        while (i < lista.size() && estado == false) {
            if (lista.get(i).getRepartidorID()== id) {
                estado = true;
                Paciente = lista.get(i);
            } else {
                i++;
            }
        }
        return Paciente;
    }
}
