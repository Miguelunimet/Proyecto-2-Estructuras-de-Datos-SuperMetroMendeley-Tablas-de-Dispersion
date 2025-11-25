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
    
    private Lista[] Tabla;
    private int Capacidad;    
    
    /**
     * Constructor de la TablitaHash.
     * @param capacidad Tamaño inicial del arreglo. 
     */
    
    public TablitaHash(int capacidad) {
        this.Capacidad = capacidad;
        this.Tabla = new Lista[capacidad];
        
    for (int i = 0; i < capacidad; i++) {
            this.Tabla[i] = new Lista();
        }
    }
    
    /**
     * Función del Hash personalizada, aca se convierte el título del resumen 
     * en un índice válido del arreglo.
     * @param clave El título del resumen.
     * @return Un índice entero entre 0 y capacidad-1.
     */
    
    private int Obtener_Indice(String clave) {
        if (clave == null) return 0;
        
        long Hash = 0; 
        int Num_Primo = 37; 
        
        for (int i = 0; i < clave.length(); i++) {
            char caracter = clave.charAt(i);
            Hash = (Hash * Num_Primo) + caracter;
        }
        
        Hash = Math.abs(Hash);        
      
        return (int) (Hash % Capacidad);
    }
    
    /**
     * Método Insertar
     * Se agrega un nuevo resumen a la tabla revisando que no este repetido
     * @param resumen que es el objeto Resumen a guardar
     * @return "true" si se agrego correctamente o "false" si ya existía o el dato no era valido
     */
    
       public boolean Insertar(Resumen resumen) {
        if (resumen == null || resumen.titulo == null) {
            return false;
        }

        int indice = Obtener_Indice(resumen.titulo);
        Resumen encontrado = Tabla[indice].BuscarTitulo(resumen.titulo);
        
        if (encontrado == null) {
            Tabla[indice].Insertar(resumen);
            return true;
        } else {
            return false;
        }               
    }
    
    /**
     * Método Buscar
     * Busca un resumen por su título exacto mediante "Obtener_Indice"
     * @param titulo El título de la investigación que se quiere buscar
     * @return El objeto Resumen si es que existe o en tal caso null si no se encuentra
     */
       
         public Resumen Buscar(String titulo) {
        if (titulo == null) 
            return null;

        int indice = Obtener_Indice(titulo);
        
        return Tabla[indice].BuscarTitulo(titulo);
    }
         
    /**
     * Método Obtener_Estadisticas
     * Se logra generar un reporte del estado actual de la tabla con sus valores,
     * esto es util para mostrar en la interfaz gráfica la estadisticas de memoria
     * @return Un String con la información
     */
         
    public String Obtener_Estadisticas() {
        StringBuilder SB = new StringBuilder();
        int totalResumenes = 0;
        int casillasOcupadas = 0;
        
        SB.append("Estado de la Tabla Hash\n");
        for (int i = 0; i < Capacidad; i++) {
            int tamañoLista = Tabla[i].Tamano();
            if (tamañoLista > 0) {
                casillasOcupadas++;
                totalResumenes += tamañoLista;
            }
        }
        
        SB.append("La capacidad total es: ").append(Capacidad).append("\n");
        SB.append("Las casillas usadas son: ").append(casillasOcupadas).append("\n");
        SB.append("El total de resúmenes es: ").append(totalResumenes).append("\n");
        SB.append("El factor de carga es: ").append(String.format("%.2f", (double)totalResumenes/Capacidad)).append("\n");
        
        return SB.toString();
    }
    
    
    /**
    * Función MostrarTodo
    * 
    * Recorre la tabla completa y muestra todos los resúmenes guardados,
    * incluyendo el título y su información general.
    * 
    * @return Un String con todos los resúmenes.
    */
    public String MostrarTodo() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Capacidad; i++) {
            NodoLista actual = Tabla[i].pFirst;
            while (actual != null) {
                sb.append(actual.data.toString()).append("\n");
                actual = actual.pNext;
            }
        }
        return sb.toString();
        }  
    
/**
  * Método ObtenerTodosLosTitulos
  * 
  * Recorre toda la tabla hash, cuenta cuántos resúmenes hay,
  * guarda sus títulos en un arreglo y luego los ordena alfabéticamente.
  * 
  * @return Arreglo con todos los títulos cargados.
  */
    public String[] ObtenerTodosLosTitulos() {
        int total = 0;        
        for (int i = 0; i < Capacidad; i++) {
            total += Tabla[i].Tamano();
        }
        
        String[] listaTitulos = new String[total];
        int contador = 0;        
        
        for (int i = 0; i < Capacidad; i++) {
            if (!Tabla[i].EsVacio()) {
                
                NodoLista aux = Tabla[i].pFirst; 
                while (aux != null) {
                    listaTitulos[contador] = aux.data.titulo;
                    contador++;
                    aux = aux.pNext;
                }
            }
        }
       for (int i = 0; i < listaTitulos.length - 1; i++) {
            for (int j = 0; j < listaTitulos.length - 1 - i; j++) {
                
                if (listaTitulos[j].compareToIgnoreCase(listaTitulos[j + 1]) > 0) {                    
                    
                    String temporal = listaTitulos[j];
                    listaTitulos[j] = listaTitulos[j + 1];
                    listaTitulos[j + 1] = temporal;
                }
            }
        }
        
        return listaTitulos;
    }
    
    /**
     * Metodo GuardarArchivo
     * 
    * Guarda todos los resúmenes en un archivo de texto para verlo nuevamente.
    * Cada resumen se escribe en un bloque separado.
     * @param ruta
    */
    public void GuardarArchivo(String ruta) {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(ruta)) {

            for (int i = 0; i < Capacidad; i++) {
                NodoLista aux = Tabla[i].pFirst;

                while (aux != null) {
                    Resumen r = aux.data;

                    pw.println(r.titulo);
                    pw.println("Autores:" + r.autores);
                    pw.println("Palabras:" + r.palabrasclaves);
                    pw.println("Resumen:" + r.resumen.replace("\n", "\\n"));
                    pw.println("---");

                    aux = aux.pNext;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    /**
    * Método CargarArchivo
    * 
    * Carga todos los resúmenes desde un archivo previamente guardado.
    * 
    * @param ruta          ruta del archivo a leer.
    * @param arbolAutores  árbol AVL donde se registran los autores.
    * @param arbolPalabras árbol AVL donde se registran las palabras clave.
    */
    public void CargarArchivo(String ruta, ArbolAVL arbolAutores, ArbolAVL arbolPalabras) {
        if (ruta == null) {
            return;
        }
        
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(ruta))) {

            String linea;
            String titulo = "";
            String autores = "";
            String palabras = "";
            String resumen = "";

            while ((linea = br.readLine()) != null) {

                if (linea.equals("---")) {
                    Resumen r = new Resumen(titulo, autores, resumen, palabras);

                    boolean insertado = this.Insertar(r);

                    if (insertado) {
                        if (arbolAutores != null && r.autores != null && r.autores.length() > 0) {
                            String[] arregloAutores = r.autores.split(",");
                            for (int i = 0; i < arregloAutores.length; i++) {
                                String autor = arregloAutores[i].trim();
                                if (autor.length() > 0) {
                                    arbolAutores.Insertar(autor, r);
                                }
                            }
                        }

                        if (arbolPalabras != null && r.palabrasclaves != null && r.palabrasclaves.length() > 0) {
                            String[] arregloPalabras = r.palabrasclaves.split(",");
                            for (int j = 0; j < arregloPalabras.length; j++) {
                                String palabra = arregloPalabras[j].trim();
                                if (palabra.length() > 0) {
                                    arbolPalabras.Insertar(palabra, r);
                                }
                            }
                        }
                    }

                    titulo = "";
                    autores = "";
                    palabras = "";
                    resumen = "";

                } else if (linea.startsWith("Autores:")) {
                    autores = linea.substring(8).trim();

                } else if (linea.startsWith("Palabras:")) {
                    palabras = linea.substring(9).trim();

                } else if (linea.startsWith("Resumen:")) {
                    resumen = linea.substring(8).trim().replace("\\n", "\n");

                } else {
                    titulo = linea.trim();
                }
            }

        } catch (Exception e) {
            System.out.println("Error al cargar archivo: " + e.getMessage());
        }
    }
}