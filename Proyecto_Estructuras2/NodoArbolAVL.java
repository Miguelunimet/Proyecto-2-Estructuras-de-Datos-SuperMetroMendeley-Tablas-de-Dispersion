package Proyecto_Estructuras2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * Clase NodoArbolAVL
 * 
 * Es el nodo del árbol AVL, se usa para guardar autores o palabras
 * claves, cada nodo almacena un String de información, por ejemplo, el nombre
 * del autor. Además de una lista de resumenes donde aparece esa información 
 * y las referencias a sus hijos izquierdo y derecho, además de la altura del 
 * nodo necesaria para mantener el balance del árbol, para luego
 * aplicar las rotaciones explicadas en clase.
 * 
 * @author Miguel Sulbarán
 * @version 1.0
 */
public class NodoArbolAVL {
    
    protected String info;
    protected Lista listaResumenes;
    protected NodoArbolAVL HI;
    protected NodoArbolAVL HD;
    protected int altura;

    /**
     * Constructor de la clase NodoArbolAVL
     * 
     * @param info Cadena que identifica el nodo (nombre del autor o palabra clave).
     * @param resumenInicial Resumen en el que aparece por primera vez esta info.
     */
    public NodoArbolAVL(String info, Resumen resumenInicial) {
        this.info = info;
        this.listaResumenes = new Lista();
        this.listaResumenes.Insertar(resumenInicial);
        this.HI = null;
        this.HD = null;
        this.altura = 1;
    }
}