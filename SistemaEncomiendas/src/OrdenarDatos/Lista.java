package OrdenarDatos;

public class Lista<T> {

    class Nodo {

        Nodo siguiente;

        public Nodo(T dato) {
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private Nodo cola;
    private int tamaño;
    private int TopeCola;
    int FrenteCola;
    private int MaximoCola;
    
    public Lista() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    // Método para agregar al inicio
    public void agregarAlInicio(T elemento) {
        Nodo nuevoNodo = new Nodo(elemento);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        }
        tamaño++;
    }

    // Método para agregar al final
    public void agregarAlFinal(T elemento) {
        Nodo nuevoNodo = new Nodo(elemento);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
        tamaño++;
    }

    // Método para agregar en medio
    public void agregarEnMedio(T elemento, int indice) {
    if (indice < 0 || indice > tamaño) {
        throw new IndexOutOfBoundsException("Índice fuera de rango");
    }
    Nodo nuevoNodo = new Nodo(elemento);
    if (indice == 0) {
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        if (cola == null) {
            cola = nuevoNodo;
        }
    } else {
        Nodo nodoAnterior = null;
        Nodo nodoActual = cabeza;
        for (int i = 0; i < indice; i++) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.siguiente;
        }
        nuevoNodo.siguiente = nodoActual;
        nodoAnterior.siguiente = nuevoNodo;
        if (nodoActual == cola) {
            cola = nuevoNodo;
        }
    }
    tamaño++;
}


    // Método para eliminar el último elemento
    public void eliminarUltimo() {
        if (cabeza == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            Nodo nodoActual = cabeza;
            while (nodoActual.siguiente != cola) {
                nodoActual = nodoActual.siguiente;
            }
            nodoActual.siguiente = null;
            cola = nodoActual;
        }
        tamaño--;
    }

    // Método para eliminar en medio
    public void eliminarEnMedio(int indice) {
    if (indice < 0 || indice >= tamaño) {
        throw new IndexOutOfBoundsException("Índice fuera de rango");
    }
    if (indice == 0) {
        cabeza = cabeza.siguiente;
        if (cabeza == null) {
            cola = null;
        }
    } else {
        Nodo nodoAnterior = null;
        Nodo nodoActual = cabeza;
        for (int i = 0; i < indice; i++) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.siguiente;
        }
        nodoAnterior.siguiente = nodoActual.siguiente;
        if (nodoActual == cola) {
            cola = nodoAnterior;
        }
    }
    tamaño--;
}

}
