package Proyecto_Estructuras2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * Clase NodoLista
 * 
 * Nodo de una lista simplemente enlazada
 * que almacena los Resumenes
 * 
 * @author Miguel Sulbarán
 * @version 1.0
 */
public class NodoLista {
    protected NodoLista pNext;
    protected Resumen data;


    /**
     * Constructor de la clase Nodo.
     * 
     * @param data Resumen que será almacenado en el nodo.
     */
    public NodoLista(Resumen data) {
        this.pNext = null;
        this.data = data;
    }
}