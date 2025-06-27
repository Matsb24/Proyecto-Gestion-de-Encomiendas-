/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.OrdenarDatos;

import java.util.ArrayList;

import dto.ClienteDTO;

/**
 *
 * @author Matias
 */
public class OrdenaClie {

    
    public void actualizarLista(ArrayList<ClienteDTO> nuevaLista) {
        lista = nuevaLista;
    }
    
    // Paciente
    private ArrayList<ClienteDTO> lista;

    public OrdenaClie() {
        lista = new ArrayList<>();
    }

    public ArrayList<ClienteDTO> getLista() {
        return lista;
    }

}
