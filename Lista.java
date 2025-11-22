/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_Estructuras2;

/**
 * Clase Lista
 * 
 * Es una lista simplemente enlazada que guarda los Resumenes.
 * 
 * @author Miguel Sulbarán
 * @version 1.0
 */
public class Lista {
    protected NodoLista pFirst;
    protected int Longitud;

    /**
     * Constructor de la clase Lista.     * 
     * Crea una lista vacía, con pFirst apuntando a null y su longitud igual a 0.
     */
    public Lista() {
        this.pFirst = null;
        this.Longitud = 0;
    }

    /**
     * Funcion Tamano
     * 
     * Retorna el número de elementos de la lista.
     * 
     * @return Cantidad de nodos de la lista.
     */
    public int Tamano() {
        return Longitud;
    }

    /**
     * Funcion EsVacio
     * 
     * Indica si la lista está vacía.
     * 
     * @return true si pFirst es igual a null, false si no.
     */
    public boolean EsVacio() {
        return pFirst == null;
    }

    /**
     * Funcion Ultimo
     * 
     * Retorna el último nodo de la lista.
     * 
     * @return Referencia al último nodo, o null si la lista está vacía.
     */
    public NodoLista Ultimo() {
        return Final();
    }

    /**
     * Funcion Final
     * 
     * Retorna el último nodo de la lista.
     * 
     * @return Nodo final de la lista, o null si está vacía.
     */
    public NodoLista Final() {
        if (pFirst == null) {
            return null;
        } else {
            NodoLista pAux = pFirst;
            while (pAux.pNext != null) {
                pAux = pAux.pNext;
            }
            return pAux;
        }
    }

    /**
     * Metodo Insertar
     * 
     * Inserta un nuevo resumen al final de la lista.
     * 
     * @param resumen Resumen que se desea insertar.
     */
    public void Insertar(Resumen resumen) {
        NodoLista pNew = new NodoLista(resumen);
        if (pFirst == null) {
            this.pFirst = pNew;
            this.Longitud++;
        } else {
            NodoLista pValor = this.Final();
            pNew.pNext = pValor.pNext;
            pValor.pNext = pNew;
            this.Longitud++;
        }
    }

    /**
     * Funcion BuscarTitulo
     * 
     * Busca un resumen en la lista por su título.
     * 
     * @param titulo Título del resumen que se desea buscar.
     * @return El Resumen si se encuentra, o null si no está.
     */
    public Resumen BuscarTitulo(String titulo) {
        NodoLista pAux = pFirst;

        if (pFirst == null) {
            return null;
        }
        while (pAux != null && !pAux.data.titulo.equals(titulo)) {
            pAux = pAux.pNext;
        }
        if (pAux != null) {
            return pAux.data;
        } else {
            return null;
        }
    }

}