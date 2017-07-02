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
public class Lista<E> implements ILista<E> {
    private INodo<E> primero;
 
    public Lista() {
        primero = null;
    }

    public Lista(INodo<E> unNodo) {
        this.primero = unNodo;
    }
    @Override
    public void insertar(INodo<E> unNodo) {
        if (esVacia()) {
            primero = unNodo;
        } else {
            INodo<E> aux = primero;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(unNodo);
        }
    }
    @Override
    public INodo<E> buscar(Comparable clave) {
        if (esVacia()) {
            return null;
        } else {
            INodo<E> aux = primero;
            while (aux != null) {
                if (aux.getEtiqueta().equals(clave)) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }
    @Override
    public boolean eliminar(Comparable clave) {
        if (esVacia()) {
            return false;
        }
        if (primero.getSiguiente() == null) {
            if (primero.getEtiqueta().equals(clave)) {
                primero = null;
                return true;
            }
        }
        INodo<E> aux = primero;
        if (aux.getEtiqueta().compareTo(clave) == 0) {
            //Eliminamos el primer elemento
            INodo<E> temp1 = aux;
            INodo<E> temp = aux.getSiguiente();
            primero = temp;
            return true;
        }
        while (aux.getSiguiente() != null) {
            if (aux.getSiguiente().getEtiqueta().equals(clave)) {
                INodo<E> temp = aux.getSiguiente();
                aux.setSiguiente(temp.getSiguiente());
                return true;

            }
            aux = aux.getSiguiente();
        }
        return false;
    }
    @Override
    public String imprimir() {
        String aux = "";
        if (!esVacia()) {
            INodo<E> temp = primero;
            while (temp != null) {                
                temp.imprimirEtiqueta();
                aux += temp.getEtiqueta();
                temp = temp.getSiguiente();
            }
        }
        return aux;
    }
    @Override
    public String imprimir(String separador) {
        String aux = "";
        if (esVacia()) {
            return "";
        } else {
            INodo<E> temp = primero;
            aux = "" + temp.getEtiqueta();
            while (temp.getSiguiente() != null) {
                aux = aux + separador + temp.getSiguiente().getEtiqueta();
                temp = temp.getSiguiente();
            }

        }
        return aux;

    }
    @Override
    public int cantElementos() {
        int contador = 0;
        if (esVacia()) {
            System.out.println("Cantidad de elementos 0.");
            return 0;
        } else {
            INodo aux = primero;
            while (aux != null) {
                contador++;
                aux = aux.getSiguiente();
            }
        }
        return contador;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }
    @Override
    public INodo<E> getPrimero() {
        return primero;
    }
    
    @Override
    public void setPrimero(INodo<E> unNodo) {
        this.primero = unNodo;
    }
    
    @Override
    public void diferenciaSimetrica(INodo<E> unNodo) {
        //IConjunto<E> conjunto = (IConjunto<E>) otroConjunto;
       
        //INodo<E> aux = (Nodo<E>) otroConjunto.getPrimero();
        /*
        while(aux!= null){
            if(aux.getEtiqueta()!=null){
                super.insertar(aux);
                aux = aux.getSiguiente();
            }
            else{
                aux=aux.getSiguiente();
            }
        }*/
        
       
    }
    
    
}
