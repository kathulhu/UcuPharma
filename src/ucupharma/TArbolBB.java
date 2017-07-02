package ucupharma;
import java.util.LinkedList;

public class TArbolBB<T> implements IArbolBB<T> {

    private IElementoAB<T> raiz;

    /**
     * Separador utilizado entre elemento y elemento al imprimir la lista
     */
    public static final String SALTO_DE_LINEA = "\n";
    public static final String ESPACIOS = "     ";
    public static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    public TArbolBB() {
        raiz = null;
    }
    
    public IElementoAB<T> getRaiz() {
        return raiz;
    }

    /**
     * @param unElemento
     * @return
     */    
    @Override
    public boolean insertar(IElementoAB<T> unElemento) {
       if (esVacio()) {
            raiz = unElemento;
            return true;
        } else {
            return raiz.insertar(unElemento);
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @SuppressWarnings("unchecked")
    public IElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            return raiz.buscar(unaEtiqueta);
        }
    }
    
    public String inOrden() {
        if (esVacio()) {
            return null;
        } else {
            return raiz.inOrden();
        }
    }

    /**
     * @return recorrida en inorden del arbol, null en caso de ser vacío
     */
    public String inOrden1() {
        if (esVacio()) {
            return null;
        } else {
            return raiz.inOrden1();
        }
    }

    public String inOrden2() {
        if (esVacio()) {
            return null;
        } else {
            return raiz.inOrden2();
        }
    }
    
    /**
     * @return recorrida en preOrden del arbol, null en caso de ser vacío
     */
    /**
     * @return
     */
    public boolean esVacio() {
        return (raiz == null);
    }

    /**
     * @return True si habían elementos en el árbol, false en caso contrario
     */
    public boolean vaciar() {
        if (!esVacio()) {
            raiz = null;
            return true;
        }
        return false;
    }

    @Override
    public LinkedList<Comparable> inorden() {
        LinkedList<Comparable> listaInorden = null;
        if (!esVacio()) {
            listaInorden = new LinkedList<>();
            raiz.inOrden(listaInorden);
        }
        return listaInorden;

    }


    
    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (!esVacio()) {
            this.raiz = this.raiz.eliminar(unaEtiqueta);
        }
    }
    
    @Override
    public TArbolBB armarIndice(Comparable area) {
        TArbolBB<Producto> arbol = new TArbolBB();
        arbol.vaciar();
        raiz.indizar(arbol, area);
        return arbol;
    }
    
    @Override
    public void armarIndiceVentas(Comparable etiquetaProducto) {
        raiz.indizarVentas(etiquetaProducto);
    }
    
    @Override
    public void armarIndiceCompras(Comparable etiquetaProducto) {
        raiz.indizarCompras(etiquetaProducto);
    }
    
    public void armarIndiceVencimientoYStock(int vencimiento) {
        raiz.indizarVencimientoYStock(vencimiento);
    }
    
    public TArbolBB<Producto> eliminarProductoPorCategoria(TArbolBB<Producto> arbolProductos){
        raiz.eliminarProductoPorCategoria(arbolProductos);
        return arbolProductos;
    }
    
    public void listaPorDescripcionCorta(String descripcion_corta){
        raiz.listaPorDescripcionCorta(descripcion_corta);
    }
    
    public void listaPorDescripcionLarga(String descripcion_larga){
        raiz.listaPorDescripcionLarga(descripcion_larga);
    }
    
    public void reporteDeVentasPorFecha(String fecha_desde, String fecha_hasta){
        raiz.reporteDeVentasPorFecha(fecha_desde, fecha_hasta);
    }

}
