/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucupharma;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author kathe
 */
public class Farmacia {

    //Lista<Producto> listaProductos = new Lista<>();
    TArbolBB<Producto> arbolProductos = new TArbolBB();
    TArbolBB arbolAreasAplicacion = new TArbolBB();
    TArbolBB<Venta> arbolVentas = new TArbolBB<>();
    TArbolBB<Stock> arbolCompras = new TArbolBB<>();

    /**
     * Dada un a ruta levanta los productos y los pone en una lista
     *
     * @param ruta
     * @return Lista<Producto>
     */
    public void altaProductosCSV(String ruta) throws ParseException {
        String[] archivo = ManejadorArchivosGenerico.leerArchivo(ruta, true);
        System.out.println("===================================================");
        System.out.println("|      Cargando datos de productos, espere...     |");
        System.out.println("===================================================");
        int count = 1;
        for (String linea : archivo) {
            String[] producto = linea.split("\\;");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            String fecha_creacion;
            String fecha_actualizacion;

            if (producto.length == 11 && count < 22000) {
                boolean is_valid = !producto[0].equals("") && !producto[1].equals("") && !producto[2].equals("") && !producto[3].equals("") && !producto[4].equals("") && !producto[5].equals("") && !producto[6].equals("") && !producto[7].equals("") && !producto[8].equals("") && !producto[9].equals("") && !producto[10].equals("");

                if (is_valid) {

                    if (producto[1].equals("")) {
                        fecha_creacion = formatter.format(new Date());
                    } else {
                        fecha_creacion = formatter.format(ft.parse(producto[1].replace("-", "/")));
                    }

                    if (producto[2].equals("")) {
                        fecha_actualizacion = formatter.format(new Date());
                    } else {
                        fecha_actualizacion = formatter.format(ft.parse(producto[2].replace("-", "/")));
                    }
                    
                    count ++;
                    //System.out.println(producto[0]);
                    altaProducto(
                            Integer.parseInt(producto[0]), 
                            fecha_creacion, 
                            fecha_actualizacion, 
                            Integer.parseInt(producto[3]), 
                            producto[4], 
                            producto[5], 
                            producto[6], 
                            Boolean.parseBoolean(producto[7]), 
                            Boolean.parseBoolean(producto[8]), 
                            Integer.parseInt(producto[9]), 
                            producto[10], 
                            0);
                }
            }
        }
        System.out.println(count);
        System.out.println("===================================================");
        System.out.println("|      Productos cargados satisfactoriamente      |");
        System.out.println("===================================================");
    }

    /**
     * Dada un a ruta levanta el stock de los productos y los pone en una lista
     *
     * @param ruta
     * @return Lista<Stock>
     */
    public void altaStockCSV(String ruta) {
        String[] archivo = ManejadorArchivosGenerico.leerArchivo(ruta, true);

        System.out.println("===================================================");
        System.out.println("|      Cargando datos de stock, espere...         |");
        System.out.println("===================================================");
        for (String linea : archivo) {
            String[] stock = linea.split("\\;");
            IElementoAB<Producto> producto = arbolProductos.buscar(Integer.parseInt(stock[0]));
            if (producto != null) {

                producto.getDatos().setStock(Integer.parseInt(stock[1]));

                Stock unStock = new Stock(Integer.parseInt(stock[0]), Integer.parseInt(stock[1]));
                IElementoAB<Stock> nodo = new TElementoAB(unStock.getIdStock(), unStock);
                arbolCompras.insertar(nodo);
            }

        }

        System.out.println("===================================================");
        System.out.println("|      Stock cargado satisfactoriamente           |");
        System.out.println("===================================================");
    }

    /**
     * Dado un producto lo insterta en una lista de Productos
     *
     * @param Producto producto
     * @return Lista<Producto>
     */    
    public void altaProducto(
            int id, 
            String fecha_creacion, 
            String ultima_actualizacion, 
            Integer precio, 
            String descripcion_corta, 
            String descripcion_larga, 
            String estado, 
            boolean refrigerado, 
            boolean requiere_receta, 
            int vencimiento, 
            String categoria, 
            int stock){
        
        String descripcion_c = descripcion_corta.trim();
        String descripcion_l = descripcion_larga.trim();
        String area = categoria.trim();
        
        Producto unProducto = new Producto(
            id, 
            fecha_creacion, 
            ultima_actualizacion, 
            precio, 
            descripcion_c, 
            descripcion_l, 
            estado, 
            refrigerado, 
            requiere_receta, 
            vencimiento, 
            area, 
            stock);

        IElementoAB<Producto> nodoProducto = new TElementoAB(id, unProducto);
        arbolProductos.insertar(nodoProducto);

        IElementoAB nodo = arbolAreasAplicacion.buscar(area);
        IElementoAB descripcionElem = new TElementoAB(descripcion_c, null);
        if (nodo == null) {
            TArbolBB arbolDescripcion = new TArbolBB();
            arbolDescripcion.insertar(descripcionElem);
            IElementoAB arbolArea = new TElementoAB(area, arbolDescripcion);
            arbolAreasAplicacion.insertar(arbolArea);

        } else {
            TArbolBB arbolAux = (TArbolBB)nodo.getDatos();
            arbolAux.insertar(descripcionElem);
        }
    }

    /**
     * Eliminar un cierto producto (UcuPharma no comercializa más ese producto)
     *
     * @param Int id
     * @return void
     */
    public void bajaProducto(int id) {
        IElementoAB<Producto> elemento = arbolProductos.buscar(id);
        if (elemento != null) {
            arbolProductos.eliminar(id);
            IElementoAB area = arbolAreasAplicacion.buscar(elemento.getDatos().getCategoria());
            TArbolBB arbol = (TArbolBB)area.getDatos();
            arbol.eliminar(elemento.getDatos().getDescripcionCorta());
            System.out.println("===================================================");
            System.out.println("|      Se ha eliminado el producto " + id + "      |");
            System.out.println("===================================================");
        } else {
            System.out.println("===================================================");
            System.out.println("|    El producto " + id + " no existe              |");
            System.out.println("===================================================");
        }
    }

    public void ventaProducto(int id_producto, int cantidad) {
        IElementoAB<Producto> nodoPr = arbolProductos.buscar(id_producto);
        if (nodoPr == null) {
            System.out.println("No se puede realizar la venta, este producto no existe");
        } else {
            if (nodoPr.getDatos().getStock() == 0) {
                System.out.println("No se puede realizar la venta, no hay stock para este producto");
            } else {
                int stock_inicial = nodoPr.getDatos().getStock();
                int resta = nodoPr.getDatos().getStock() - cantidad;
                if (resta < 0) {
                    int stock = nodoPr.getDatos().getStock();
                    System.out.println("No se puede realizar la venta, el stock actual es de: " + Integer.toString(stock));
                } else {
                    nodoPr.getDatos().setStock(resta);
                    Venta venta = new Venta(new Date(), nodoPr.getEtiqueta(), nodoPr.getDatos().getPrecio(), cantidad);
                    IElementoAB<Venta> nodoVt = new TElementoAB(venta.getIdVenta(), venta);
                    System.out.println("===================================================");
                    System.out.println("|           Venta realizada con exito             |");
                    System.out.println("===================================================");
                    System.out.print("Producto ID: ");
                    System.out.println(id_producto);
                    System.out.print("Descripcion Corta: ");
                    System.out.println(nodoPr.getDatos().getDescripcionCorta());
                    System.out.print("Cantidad de productos vendidos: ");
                    System.out.println(cantidad);
                    System.out.print("Stock inicial: ");
                    System.out.println(stock_inicial);
                    System.out.print("Stock actual: ");
                    System.out.println(resta);
                    arbolVentas.insertar(nodoVt);
                }

            }
        }
    }

    /**
     * Dado un id de producto y una cantidad, reintegra del stock la cantidad
     * pasada por parametro y genera una devolucion en la lista de ventas
     *
     * @param Int id_producto Int cantidad
     * @return void
     */
    public void reintegroStock(int id_producto, int cantidad) {
        IElementoAB<Producto> nodoPr = arbolProductos.buscar(id_producto);
        int stock_inicial = 0;
        if (nodoPr == null) {
            System.out.println("No se puede realizar el reintegro, este producto no existe");
        } else {

            stock_inicial = nodoPr.getDatos().getStock();
            nodoPr.getDatos().setStock(nodoPr.getDatos().getStock() + cantidad);

            System.out.println("===================================================");
            System.out.println("|      Reintegro de stock realizado con exito     |");
            System.out.println("===================================================");
            System.out.print("Producto ID: ");
            System.out.println(id_producto);
            System.out.print("Descripcion Corta: ");
            System.out.println(nodoPr.getDatos().getDescripcionCorta());
            System.out.print("Cantidad devuelta: ");
            System.out.println(cantidad);
            System.out.print("Stock inicial: ");
            System.out.println(stock_inicial);
            System.out.print("Stock actual: ");
            System.out.println(nodoPr.getDatos().getStock());
            Venta venta = new Venta(new Date(), nodoPr.getEtiqueta(), nodoPr.getDatos().getPrecio(), -cantidad);
            IElementoAB<Venta> nodoVt = new TElementoAB(venta.getIdProducto(), venta);
            arbolVentas.insertar(nodoVt);
        }
    }

    /**
     * Dado un rango de fechas, muestra las ventas generadas. pasada por
     * parametro y genera una devolucion en la lista de ventas
     *
     * @param String fecha_desde, String fecha_hasta
     * @return void
     */
    public void reporteVentas(String fecha_desde, String fecha_hasta) throws ParseException{       
        arbolVentas.reporteDeVentasPorFecha(fecha_desde, fecha_hasta);
        System.out.println("Fecha:    Id Producto:  Precio:  Cantidad:  Total:  " );
    }
    /**
     * Dado un string, devuelve una lista de todos los productos que tienen
     * contenido ese string en su descripcion corta
     *
     * @param String descripcion_corta
     * @return Lista<Producto>
     */
    public void buscarPorDescripcionCorta(String descripcion_corta){
        System.out.println("ID       | Precio UYU | Refrigerado | Requiere receta | Estado   | Descripcion Corta");
        arbolProductos.listaPorDescripcionCorta(descripcion_corta);
    }
    
    /**
     * Busca un producto por su id
     *
     * @param id
     * @return retorna INodo<Producto>
     */
    public IElementoAB<Producto> buscarPorId(Comparable id) {
        return arbolProductos.buscar(id);
    }

    /**
     * Dado un string, devuelve una lista de todos los productos que tienen
     * contenido ese string en su descripcion larga
     *
     * @param String descripcion_larga
     * @return Lista<Producto>
     */
     public void buscarPorDescripcionLarga(String descripcion_larga){
        System.out.println("ID       | Precio UYU | Refrigerado | Requiere receta | Estado   | Descripcion Corta");
        arbolProductos.listaPorDescripcionLarga(descripcion_larga);
    }
    /**
     * Dado un id, devuelve un nodo de stock que tenga esa etiqueta
     *
     * @param Comparable id
     * @return INodo<Stock>
     */
    public int buscarPorIdStock(Comparable id) {
        return arbolProductos.buscar(id).getDatos().getStock();
    }
    
    /*Emitir un listado agrupado y ordenado por áreas de aplicación (el listado deberá estar
    indentado: el nombre del área de aplicación empieza en la columna 1 y los productos
    correspondientes en la 5). Dentro de cada área de aplicación, los productos deben listarse
    ordenados en forma ascendente por nombre de producto (descripción corta).*/
    public void listarProductosPorArea() { 
        System.out.println(arbolAreasAplicacion.inOrden());
    }
    
    /*Dado un producto, listar todas las operaciones de venta del mismo (cada operación debe
    reflejar el código de producto, fecha de venta, cantidad vendida y monto total).*/
    public void listarVentasPorProducto(Comparable etiquetaProducto) {
        System.out.println("VENTAS  ");
        System.out.println("ID Producto  Fecha Venta  Cantidad   Precio  Total  ");
        arbolVentas.armarIndiceVentas(etiquetaProducto);
    }

    public void listarComprasPorProducto(Comparable etiquetaProducto) {
        System.out.println("COMPRAS  ");
        System.out.println("ID Stock  Cantidad   Precio  Total  ");
        arbolVentas.armarIndiceCompras(etiquetaProducto);
    }
    
    /*Dada un cierto año, emitir un listado con todos los productos existentes en el stock que
    vencen en el mismo.*/
    public void listarProductoPorVencimientoYStock(int vencimiento) {
        System.out.println("ID Producto Nombre     Vencimiento  Stock");
        arbolProductos.armarIndiceVencimientoYStock(vencimiento);
    }
    
    /*Eliminar un área de aplicación (UcuPharma no comercializa más
    productos de esta aplicación) impactando sobre todas las estructuras
    existentes.*/
    public void eliminarCategoria(String categoria) {
        IElementoAB area = arbolAreasAplicacion.buscar(categoria);
        TArbolBB arbol = (TArbolBB) area.getDatos();
        arbolProductos = arbol.eliminarProductoPorCategoria(arbolProductos);
        arbolAreasAplicacion.eliminar(categoria);
        System.out.println(arbolProductos.inOrden2());
    }

    /**
     * Dada un a ruta levanta el stock de los productos y los pone en una lista
     *
     * @param ruta
     * @return Lista<Stock>
     */
    public void actualizacionStockCSV(String ruta) {
        String[] archivo = ManejadorArchivosGenerico.leerArchivo(ruta, true);

        System.out.println("===================================================");
        System.out.println("|      Cargando datos de compra, espere...         |");
        System.out.println("===================================================");
        for (String linea : archivo) {
            String[] stock = linea.split("\\;");
            IElementoAB<Producto> nodoPr = arbolProductos.buscar(Integer.parseInt(stock[0]));

            if (nodoPr == null) {
                System.out.println("No se puede incrementar el Stock, este producto no existe");
            } else {

                Stock unStock = new Stock(Integer.parseInt(stock[0]), Integer.parseInt(stock[1]));
                IElementoAB<Stock> nodo = new TElementoAB(unStock.getIdStock(), unStock);
                nodoPr.getDatos().setStock(nodoPr.getDatos().getStock() + nodo.getDatos().getMonto());
                arbolCompras.insertar(nodo);
            }

        }

        System.out.println("===================================================");
        System.out.println("|      Compras cargadas satisfactoriamente        |");
        System.out.println("===================================================");
    }
    
    /*Dada un área de aplicación, emitir un listado de movimientos de todos los productos del
    área (compras y ventas), con identificador, nombre y cantidad de cada operación.*/
    public void listadoDeMovimientosDeProductosPorCategoria(String categoria) {
        
    }

    /*public void listadoDeMovimientosDeProductosPorCategoria(String categoria) {
        IElementoAB area = arbolAreasAplicacion.buscar(categoria);
        while (temp != null) {
            if (temp.getEtiqueta().toString().contains(categoria)) {
                TArbolBB arbol = (TArbolBB) temp.getDato();
                arbolProductos = arbol.eliminarProductoPorCategoria(arbolProductos);
                listaAreasAplicacion.eliminar(temp.getEtiqueta());
            }
            temp = temp.getSiguiente();
        }
    }*/
    
    /* Dado un cierto producto, indicar el promedio de ventas mensuales.*/
    public void promedioDeVentasMensuales(Comparable id_producto) {

    }
}


