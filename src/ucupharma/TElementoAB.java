package ucupharma;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class TElementoAB<T> implements IElementoAB<T> {

    private Comparable etiqueta;
    private IElementoAB hijoIzq;
    private IElementoAB hijoDer;
    private T datos;

    /**
     * @param unaEtiqueta
     * @param unosDatos
     */
    @SuppressWarnings("unchecked")
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
        hijoIzq = null;
        hijoDer = null;
    }

  
    
    @Override
    public T getDatos() {
        return datos;
    }

    public IElementoAB getHijoIzq() {
        return hijoIzq;
    }

    public IElementoAB getHijoDer() {
        return hijoDer;

    }
    public Comparable getEtiqueta(){
        return etiqueta;
    }
    
    /**
     * @return
     */
    public String imprimir() {
        return (etiqueta.toString());
    }


    @Override
    public void setHijoIzq(IElementoAB elemento) {
        this.hijoIzq = elemento;

    }

    @Override
    public void setHijoDer(IElementoAB elemento) {
        this.hijoDer = elemento;
    }

    /**
     * @param unElemento
     * @return
     */

    
    @Override
    public boolean insertar(IElementoAB<T> unElemento) {
       
        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        } else {
            // ya existe un elemento con la misma etiqueta.-
            return false;
        }
    }
    

    /**
     * @param unaEtiqueta
     * @return
     */
    public IElementoAB buscar(Comparable unaEtiqueta) {

        if (unaEtiqueta.equals(etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return hijoIzq.buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return hijoDer.buscar(unaEtiqueta);
        } else {
            return null;
        }
    }
    
    @Override
    public String inOrden1() {
        StringBuilder tempStr = new StringBuilder();
        if (hijoIzq != null) {
            tempStr.append(hijoIzq.inOrden1());
            tempStr.append(TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS);
        }
        tempStr.append(imprimir());
        if (hijoDer != null) {
            tempStr.append(TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS);
            tempStr.append(hijoDer.inOrden1());
        }

        return tempStr.toString();
    }

    /**
     * @return recorrida en inorden del subArbol que cuelga del elemento actual
     */
    @Override
    public String inOrden2() {
        StringBuilder tempStr = new StringBuilder();
        if (hijoIzq != null) {
            tempStr.append(hijoIzq.inOrden2());
            tempStr.append(TArbolBB.SALTO_DE_LINEA);
        }
        tempStr.append(TArbolBB.ESPACIOS);
        tempStr.append(imprimir());
        if (hijoDer != null) {
            tempStr.append(TArbolBB.SALTO_DE_LINEA);
            tempStr.append(hijoDer.inOrden2());
        }

        return tempStr.toString();
        
    }
    
    public String inOrden() {
        String tempStr =  " ";
        if (hijoIzq != null) {
            hijoIzq.inOrden();
            
        }
        TArbolBB arbol = (TArbolBB)datos;
        System.out.println(etiqueta);
        System.out.println(arbol.inOrden2());
        
        if (hijoDer != null) {
            hijoDer.inOrden();
        }

        return tempStr;
        
    }

    @Override
    public void inOrden(LinkedList<Comparable> unaLista) {
        if (hijoIzq != null) {
            hijoIzq.inOrden(unaLista);

        }
        unaLista.add(this.getEtiqueta());
        if (hijoDer != null) {
            hijoDer.inOrden(unaLista);
        }

    }


   @Override
    public IElementoAB eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.getEtiqueta()) < 0) {
            if (this.hijoIzq != null) {
                this.hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }

        if (unaEtiqueta.compareTo(this.getEtiqueta()) > 0) {
            if (this.hijoDer != null) {
                this.hijoDer = hijoDer.eliminar(unaEtiqueta);

            }
            return this;
        }

        return quitaElNodo();
    }
    
    private IElementoAB quitaElNodo() {
        if (hijoIzq == null) {    // solo tiene un hijo o es hoja
            return hijoDer;
        }

        if (hijoDer == null) { // solo tiene un hijo o es hoja
            return hijoIzq;
        }
// tiene los dos hijos, buscamos el lexicograficamente anterior
        IElementoAB elHijo = hijoIzq;
        IElementoAB elPadre = this;

        while (elHijo.getHijoDer() != null) {
            elPadre = elHijo;
            elHijo = elHijo.getHijoDer();
        }

        if (elPadre != this) {
            elPadre.setHijoDer(elHijo.getHijoIzq());
            elHijo.setHijoIzq(hijoIzq);
        }

        elHijo.setHijoDer(hijoDer);
        setHijoIzq(null);  // para que no queden referencias y ayudar al collector
        setHijoDer(null);
        return elHijo;
    }
    
    
    @Override
    public void indizar(TArbolBB<Producto> indice, Comparable area) {
   
        if (hijoIzq != null) {
            hijoIzq.indizar(indice, area);

        }
        Producto producto = (Producto) datos;
        if (area.equals(producto.getCategoria())) {
            TElementoAB nuevo = new TElementoAB(producto.getDescripcionCorta(), producto.getId());
            indice.insertar(nuevo);
        }
        
        if (hijoDer != null) {
            hijoDer.indizar(indice, area);
        }
    }

    @Override
    public void indizarVentas(Comparable etiquetaProducto) {
        
        if (hijoIzq != null) {
            hijoIzq.indizarVentas(etiquetaProducto);

        }
        Venta venta = (Venta) datos;
        if (etiquetaProducto.equals(venta.getIdProducto())) {
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
            String fecha_venta = ft.format(venta.getFechaVenta());
            System.out.println(venta.getIdProducto() + " " + fecha_venta + " "  + venta.getCantidad() + " "  + venta.getPrecio() + " "  + venta.getTotal());            
        }
        
        if (hijoDer != null) {
            hijoDer.indizarVentas(etiquetaProducto);
        }
    }
    
    @Override
    public void indizarCompras(Comparable etiquetaProducto) {
        
        if (hijoIzq != null) {
            hijoIzq.indizarCompras(etiquetaProducto);

        }
        Stock compra = (Stock) datos;
        if (etiquetaProducto.equals(compra.getIdProducto())) {
            System.out.println(compra.getIdStock() + " "  + compra.getIdProducto()+ " "  + compra.getMonto());            
        }
        
        if (hijoDer != null) {
            hijoDer.indizarCompras(etiquetaProducto);
        }
    }
    
    
    public void indizarVencimientoYStock(int vencimiento) {
        if (hijoIzq != null) {
            hijoIzq.indizarVencimientoYStock(vencimiento);

        }
        Producto producto = (Producto) datos;
        if (vencimiento == producto.getVencimiento() && producto.getStock() != 0) {
            System.out.println(producto.getId() + "      " + producto.getDescripcionCorta() + " " + producto.getVencimiento() + " "  + producto.getStock());            
        }
        
        if (hijoDer != null) {
            hijoDer.indizarVencimientoYStock(vencimiento);
        }
    }
    
    public void eliminarProductoPorCategoria(TArbolBB<Producto> arbolProductos) {
        if (hijoIzq != null) {
            hijoIzq.eliminarProductoPorCategoria(arbolProductos);

        }

        Comparable id = (Comparable)datos;
        arbolProductos.eliminar(id);
        
        if (hijoDer != null) {
            hijoDer.eliminarProductoPorCategoria(arbolProductos);
        }        
    }
    
    public void listaPorDescripcionCorta(String descripcion_corta) {
        String lowerDes = descripcion_corta.toLowerCase();
        
        if (hijoIzq != null) {
            hijoIzq.listaPorDescripcionCorta(descripcion_corta);

        }
        Producto producto = (Producto) datos;
        if (lowerDes == producto.getDescripcionCorta().toLowerCase()) {
            System.out.println(producto.getId().toString() +"   | "  + producto.getPrecio() + "       | "+ producto.getRefrigerado() + "       | " + producto.getRequiereReceta() + "           | " + producto.getEstado() + "   | "  + producto.getDescripcionCorta());            
        }
        
        if (hijoDer != null) {
            hijoDer.listaPorDescripcionCorta(descripcion_corta);
        }    
    }
    
    public void listaPorDescripcionLarga(String descripcion_larga) {
        String lowerDes = descripcion_larga.toLowerCase();
        
        if (hijoIzq != null) {
            hijoIzq.listaPorDescripcionLarga(descripcion_larga);

        }
        Producto producto = (Producto) datos;
        if (lowerDes == producto.getDescripcionCorta().toLowerCase()) {
            System.out.println(producto.getId().toString() +"   | "  + producto.getPrecio() + "       | "+ producto.getRefrigerado() + "       | " + producto.getRequiereReceta() + "           | " + producto.getEstado() + "   | "  + producto.getDescripcionLarga());            
        }
        
        if (hijoDer != null) {
            hijoDer.listaPorDescripcionLarga(descripcion_larga);
        }  
    
    }
    
    public void reporteDeVentasPorFecha(String fecha_desde, String fecha_hasta){
        int total = 0;
        int cantidad = 0;
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        Date desde;
        Date hasta;
        
        Venta venta = (Venta) datos;
        String fecha_venta = ft.format(venta.getFechaVenta());
        
        
        if (!fecha_desde.equals("") && !fecha_hasta.equals("")) {
            
            if (unaEtiqueta.equals(etiqueta)) {
            
                return this;
            } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
                if (hijoIzq != null) {
                    return hijoIzq.listaPorDescripcionLarga(unaEtiqueta);
                } else {
                    return null;
                }
            } else if (hijoDer != null) {
                return hijoDer.buscar(unaEtiqueta);
            } else {
                return null;
            }
            
            
                desde = ft.parse(fecha_desde);
                hasta = ft.parse(fecha_hasta);
                if ((desde.before(venta.getDato().getFechaVenta())) && hasta.before(venta.getDato().getFechaVenta()) || hasta.equals(venta.getDato().getFechaVenta()) || desde.equals(venta.getDato().getFechaVenta())) {                           
                    total += venta.getDato().getTotal();
                    cantidad += venta.getDato().getCantidad();
                    System.out.println(fecha_venta  + "  "  + venta.getDato().getIdProducto().toString() + "        "  + Integer.toString(venta.getDato().getPrecio()) + "    "  + Integer.toString(venta.getDato().getCantidad()) + "         "  + Integer.toString(venta.getDato().getTotal()));
                }
            } else {
                if (!fecha_desde.equals("") && fecha_hasta.equals("")) {
                    desde = ft.parse(fecha_desde);
                    if (desde.before(venta.getDato().getFechaVenta()) || desde.equals(venta.getDato().getFechaVenta())) {
                        total += venta.getDato().getTotal();
                        cantidad += venta.getDato().getCantidad();
                        System.out.println(fecha_venta  + "  "  + venta.getDato().getIdProducto().toString() + "        "  + Integer.toString(venta.getDato().getPrecio()) + "    "  + Integer.toString(venta.getDato().getCantidad()) + "         "  + Integer.toString(venta.getDato().getTotal()));
                    }
                } else if (fecha_desde.equals("") && !fecha_hasta.equals("")) {
                    hasta = ft.parse(fecha_hasta);
                    if (hasta.before(venta.getDato().getFechaVenta()) || hasta.equals(venta.getDato().getFechaVenta())) {
                        total += venta.getDato().getTotal();
                        cantidad += venta.getDato().getCantidad();
                        System.out.println(fecha_venta  + "  "  + venta.getDato().getIdProducto().toString() + "        "  + Integer.toString(venta.getDato().getPrecio()) + "    "  + Integer.toString(venta.getDato().getCantidad()) + "         "  + Integer.toString(venta.getDato().getTotal()));
                    } 
                } else {
                    total += venta.getDato().getTotal();
                    cantidad += venta.getDato().getCantidad();
                    System.out.println(fecha_venta  + "  "  + venta.getDato().getIdProducto().toString() + "        "  + Integer.toString(venta.getDato().getPrecio()) + "     "  + Integer.toString(venta.getDato().getCantidad()) + "         "  + Integer.toString(venta.getDato().getTotal()));
                }   
            }

        
        
        
        
        
        
  
            
            
        
        System.out.println("Cantidad de Productos Vendidos: " + total);
        System.out.println("Total Ventas $: " + cantidad);
    
    }
    
}
