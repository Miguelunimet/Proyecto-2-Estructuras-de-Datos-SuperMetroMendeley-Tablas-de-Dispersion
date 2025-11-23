/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_Estructuras2;

/**
 *
 * @author Miguel Sulbarán
 */
public class mainprueba {

    public static void main(String[] args) {

        ArbolAVL arbol = new ArbolAVL();

        Resumen r1 = new Resumen("Titulo A", "Autor1", "Resumen1", "Clave1");
        Resumen r2 = new Resumen("Titulo B", "autor2", "Resumen2", "Clave2");
        Resumen r3 = new Resumen("Titulo C", "Autor3", "rsumen3", "Clave3");
        
        arbol.Insertar("Alvarez", r1);
        arbol.Insertar("Sulbaran", r2);
        arbol.Insertar("Garcia", r3);

        arbol.InOrden();

        Lista lista = arbol.Buscar("Sulbaran");
        if (lista != null) {
            System.out.println("Resumenes del autor Sulbaran: " + lista.Tamano());
            

        }
    }
}
