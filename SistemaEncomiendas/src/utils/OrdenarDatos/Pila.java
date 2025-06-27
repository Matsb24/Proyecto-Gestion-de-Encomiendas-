/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.OrdenarDatos;

/**
 *
 * @author Matias
 */
public class Pila {
    private int tope;
    private int max;
    private int cantidad;


   //Insertar a la Pila
    
    public void InsertarPila(){
        
        if(tope >= max){
            
        } else {
            tope ++;
            
        }
        
    }
    
   // Eliminar de la Pila
    
    public void EliminarPila(){
        
        if(tope == -1){
            
        } else {
            tope --;
            
        }
        
    }

    /**
     * @return the tope
     */
    public int getTope() {
        return tope;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
