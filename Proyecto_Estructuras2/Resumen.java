/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_Estructuras2;

/**
 * Clase Resumen
 * 
 * Representa el resumen que se va a almacenar,
 * contiene el título, los autores, el texto del resumen y las palabras claves.
 * 
 * @author Miguel Sulbarán
 * @version 1.0
 */
public class Resumen {
    
    public String titulo;
    public String autores;
    public String resumen;
    public String palabrasclaves;

    /**
     * Constructor de la clase Resumen.
     * 
     * @param titulo            Título del resumen.
     * @param autores           Cadena con los autores.
     * @param resumen           Texto del resumen.
     * @param palabrasclaves   Cadena con las palabras claves.
     */
    public Resumen(String titulo, String autores, String resumen, String palabrasclaves) {
        this.titulo = titulo;
        this.autores = autores;
        this.resumen = resumen;
        this.palabrasclaves = palabrasclaves;
    }   
}