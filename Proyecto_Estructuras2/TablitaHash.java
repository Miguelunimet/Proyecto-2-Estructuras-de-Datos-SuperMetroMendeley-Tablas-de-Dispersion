/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_Estructuras2;

/**
 * Clase TablitaHash
 * 
 * Estructura de datos encargada de almacenar los objetos 
 * "Resumen" utilizando el título de la investigación como punto de referencia, 
 * y tomando en cuenta posibles conflictos
 * 
 * @author Rafael Álvarez
 * @version 1.0 
 */
public class TablitaHash {
    
    private Lista[] tabla;
    private int capacidad;    
    
    /**
     * Constructor de la TablitaHash.
     * @param capacidad Tamaño inicial del arreglo. 
     */
    
    public TablitaHash(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new Lista[capacidad];
        
    for (int i = 0; i < capacidad; i++) {
            this.tabla[i] = new Lista();
        }
    }
    
    /**
     * Función del Hash personalizada, aca se convierte el título del resumen 
     * en un índice válido del arreglo.
     * @param clave El título del resumen.
     * @return Un índice entero entre 0 y capacidad-1.
     */
    
    private int obtenerIndice(String clave) {
        if (clave == null) return 0;
        
        long hash = 0; 
        int primo = 37; 
        
        for (int i = 0; i < clave.length(); i++) {
            char caracter = clave.charAt(i);
            hash = (hash * primo) + caracter;
        }
        
        hash = Math.abs(hash);        
      
        return (int) (hash % capacidad);
    }
}