/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucupharma;

/**
 *
 * @author Kathe
 */
public class Nodo<E> implements INodo<E> {
    private final Comparable etiqueta;
    private E dato;
    private INodo<E> siguiente = null;
    
    public Nodo(Comparable etiqueta, E dato) {
        this.dato = dato;
        this.etiqueta = etiqueta;
    }

    public E getDato() {
        return this.dato;
    }

    public void setDato(E dato) {
        this.dato = dato;

    }

    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    public void setSiguiente(INodo<E> nodo) {
        this.siguiente = nodo;

    }

    public INodo<E> getSiguiente() {
        return this.siguiente;
    }

    public void imprimir() {
        System.out.println(this.dato.toString());
    }

    public void imprimirEtiqueta() {
        System.out.println(this.etiqueta);
    }

    public INodo<E> clonar() {
        return new Nodo<E>(etiqueta, dato);
    }

    public boolean equals(INodo<E> unNodo) {
        return this.dato.equals(unNodo.getDato());
    }

    public int compareTo(Comparable etiqueta) {
        return this.etiqueta.compareTo(etiqueta);
    }
}
