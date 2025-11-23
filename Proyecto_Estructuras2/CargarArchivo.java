/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_Estructuras2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Clase CargarArchivo
 * Utilidad estática encargada de leer los archivos de texto y
 * convertirlos en objetos de la clase Resumen tambien realiza el "parsing" 
 * detectando las secciones del documento insertado
 * @author Rafael Álvarez
 */
public class CargarArchivo {    
    
    /**
     * Método Cargar
     * Es el encargado de poder leer un archivo línea por línea e identificar
     * las secciones del mismo en base al formato dado.
     * @param Archivo El archivo seleccionado por el usuario.
     * @return Un objeto Resumen listo para ser insertado o null en caso de que el 
     * archivo no sea válido.
     */
    
    public static Resumen Cargar(File Archivo) {
        String Titulo = "";
        StringBuilder autores = new StringBuilder();
        StringBuilder Cuerpo_del_Resumen = new StringBuilder();
        String palabrasClaves = "";        

        boolean Leyendo_los_Autores = true;
        boolean Leyendo_los_Resumenes = false;
        
        try (BufferedReader BR = new BufferedReader(new FileReader(Archivo))) {
            String Liniesita;            
            
            if ((Liniesita = BR.readLine()) != null) {
                Titulo = Liniesita.trim();
            }

            while ((Liniesita = BR.readLine()) != null) {
                Liniesita = Liniesita.trim();
                if (Liniesita.isEmpty()) continue; 
                
                if (Liniesita.equalsIgnoreCase("Resumen")) {
                    Leyendo_los_Autores = false;
                    Leyendo_los_Resumenes = true;
                    continue;
                }

               
                if (Liniesita.toLowerCase().startsWith("Palabras Claves:")) {
                    Leyendo_los_Resumenes = false;                
                    palabrasClaves = Liniesita.substring(16).trim();
                    continue;
                }
                
                if (Leyendo_los_Autores) {
                    if (autores.length() > 0) autores.append("\n");
                    autores.append(Liniesita);
                } else if (Leyendo_los_Resumenes) {
                    Cuerpo_del_Resumen.append(Liniesita).append(" ");
                }
            }
           
            if (!Titulo.isEmpty()) {
                return new Resumen(Titulo, autores.toString(), Cuerpo_del_Resumen.toString(), palabrasClaves);
            } else {
                return null;
            }

        } catch (IOException e) {            
            return null;
        }
    }
}
