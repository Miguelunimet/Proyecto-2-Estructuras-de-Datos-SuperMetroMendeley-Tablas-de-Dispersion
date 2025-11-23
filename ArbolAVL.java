/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_Estructuras2;

/**
 * Clase ArbolAVL
 * 
 * Es un árbol AVL utilizado para almacenar texto, por ejemplo, autores o palabras clave,
 * Cada nodo contiene además una lista de resúmenes donde aparece la clave,
 * el árbol se mantiene balanceado mediante rotaciones simples y dobles, dependiendo
 * de en donde se encuentre el desbalance, además tiene funciones para insertar claves, 
 * buscar información y recorrer el contenido del árbol en orden alfabético.
 * 
 * @author Miguel Sulbarán
 * @version 1.0
 */
public class ArbolAVL {

    protected NodoArbolAVL raiz;

    /**
     * Constructor de la clase ArbolAVL
     * 
     * Inicializa la raíz en null, creando un árbol vacío.
     */
    public ArbolAVL() {
        this.raiz = null;
    }

    /**
     * Función ObtenerRaiz
     * 
     * Retorna la raíz actual del árbol AVL.
     * 
     * @return NodoArbolAVL que representa la raíz del árbol.
     */
    public NodoArbolAVL ObtenerRaiz() {
        return raiz;
    }

    /**
     * Función ObtenerAltura
     * 
     * Retorna la altura de un nodo dentro del árbol AVL. Si el nodo es null,
     * se considera altura 0.
     * 
     * @param nodo Nodo del cual se desea conocer la altura.
     * @return Entero con la altura del nodo.
     */
    public int ObtenerAltura(NodoArbolAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    /**
     * Función Mayor
     * 
     * Retorna el valor máximo entre dos enteros.
     * 
     * @param a primer valor.
     * @param b segundo valor.
     * @return el mayor entre a y b.
     */
    public int Mayor(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * Función Equilibrio
     * 
     * Calcula el factor de equilibrio de un nodo, definido como la diferencia
     * entre la altura del hijo izquierdo y la del hijo derecho.
     * 
     * @param nodo nodo sobre el cual se calcula el equilibrio.
     * @return entero con el valor de equilibrio.
     */
    public int Equilibrio(NodoArbolAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return ObtenerAltura(nodo.HI) - ObtenerAltura(nodo.HD);
    }

    /**
     * Función RotarDerecha
     * 
     * Aplica una rotación simple hacia la derecha sobre el nodo recibido,
     * se utiliza cuando el árbol presenta un desbalance del tipo IZQ-IZQ.
     * 
     * @param y nodo que actúa como raíz del subárbol desbalanceado.
     * @return nueva raíz del subárbol tras la rotación.
     */
    public NodoArbolAVL RotarDerecha(NodoArbolAVL y) {
        NodoArbolAVL x = y.HI;
        NodoArbolAVL T2 = x.HD;

        x.HD = y;
        y.HI = T2;

        y.altura = Mayor(ObtenerAltura(y.HI), ObtenerAltura(y.HD)) + 1;
        x.altura = Mayor(ObtenerAltura(x.HI), ObtenerAltura(x.HD)) + 1;

        return x;
    }

    /**
     * Función RotarIzquierda
     * 
     * Aplica una rotación simple hacia la izquierda sobre el nodo recibido.
     * Se utiliza cuando se presenta un desbalance del tipo DER-DER.
     * 
     * @param x nodo desbalanceado.
     * @return nueva raíz del subárbol tras la rotación.
     */
    public NodoArbolAVL RotarIzquierda(NodoArbolAVL x) {
        NodoArbolAVL y = x.HD;
        NodoArbolAVL T2 = y.HI;

        y.HI = x;
        x.HD = T2;

        x.altura = Mayor(ObtenerAltura(x.HI), ObtenerAltura(x.HD)) + 1;
        y.altura = Mayor(ObtenerAltura(y.HI), ObtenerAltura(y.HD)) + 1;

        return y;
    }

    /**
     * Método Insertar
     * 
     * Inserta una "clave" (info) en el árbol AVL junto con el resumen asociado.
     * Si la clave ya existe, el resumen se agrega a la lista del nodo existente.
     * 
     * @param info clave a insertar.
     * @param resumen resumen asociado a la clave.
     */
    public void Insertar(String info, Resumen resumen) {
        raiz = InsertarRecursivo(raiz, info, resumen);
    }

    /**
     * Función InsertarRecursivo
     * 
     * Inserta una clave dentro del árbol de manera recursiva, actualiza alturas
     * y aplica rotaciones cuando sea necesario.
     * 
     * @param nodo nodo actual del recorrido.
     * @param info clave a insertar.
     * @param resumen resumen a asociar.
     * @return nodo actualizado después de insertar y balancear.
     */
    private NodoArbolAVL InsertarRecursivo(NodoArbolAVL nodo, String info, Resumen resumen) {
        if (nodo == null) {
            return new NodoArbolAVL(info, resumen);
        }

        int comparacion = info.compareToIgnoreCase(nodo.info);

        if (comparacion < 0) {
            nodo.HI = InsertarRecursivo(nodo.HI, info, resumen);
        } else if (comparacion > 0) {
            nodo.HD = InsertarRecursivo(nodo.HD, info, resumen);
        } else {
            nodo.listaResumenes.Insertar(resumen);
            return nodo;
        }

        nodo.altura = 1 + Mayor(ObtenerAltura(nodo.HI), ObtenerAltura(nodo.HD));

        int balance = Equilibrio(nodo);

        if (balance > 1 && info.compareToIgnoreCase(nodo.HI.info) < 0) {
            return RotarDerecha(nodo);
        }

        if (balance < -1 && info.compareToIgnoreCase(nodo.HD.info) > 0) {
            return RotarIzquierda(nodo);
        }

        if (balance > 1 && info.compareToIgnoreCase(nodo.HI.info) > 0) {
            nodo.HI = RotarIzquierda(nodo.HI);
            return RotarDerecha(nodo);
        }

        if (balance < -1 && info.compareToIgnoreCase(nodo.HD.info) < 0) {
            nodo.HD = RotarDerecha(nodo.HD);
            return RotarIzquierda(nodo);
        }

        return nodo;
    }

    /**
     * Función Buscar
     * 
     * Retorna la lista de resúmenes asociada a una clave.
     * 
     * @param info clave a buscar.
     * @return Lista de resúmenes o null si no existe.
     */
    public Lista Buscar(String info) {
        NodoArbolAVL resultado = BuscarRecursivo(raiz, info);
        if (resultado != null) {
            return resultado.listaResumenes;
        } else {
            return null;
        }
    }

    /**
     * Función BuscarRecursivo
     * 
     * Búsqueda recursiva dentro del árbol AVL.
     * 
     * @param nodo nodo actual.
     * @param info clave buscada.
     * @return nodo que contiene la clave, o null.
     */
    private NodoArbolAVL BuscarRecursivo(NodoArbolAVL nodo, String info) {
        if (nodo == null) {
            return null;
        }

        int comparacion = info.compareToIgnoreCase(nodo.info);

        if (comparacion == 0) {
            return nodo;
        } else if (comparacion < 0) {
            return BuscarRecursivo(nodo.HI, info);
        } else {
            return BuscarRecursivo(nodo.HD, info);
        }
    }

    /**
     * Método InOrden
     * 
     * Imprime en consola las claves del árbol en orden alfabético.
     */
    public void InOrden() {
        InOrdenRecursivo(raiz);
    }

    /**
     * Método InOrdenRecursivo
     * 
     * Recorre el árbol en orden (izquierda, nodo, derecha).
     * 
     * @param nodo nodo actual.
     */
    private void InOrdenRecursivo(NodoArbolAVL nodo) {
        if (nodo != null) {
            InOrdenRecursivo(nodo.HI);
            System.out.println(nodo.info + " (aparece en " + nodo.listaResumenes.Tamano() + " resumenes)");
            InOrdenRecursivo(nodo.HD);
        }
    }
}