/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucupharma;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author kathe
 */
public class UcuPharma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        Farmacia farmacia = new Farmacia();
        farmacia.altaProductosCSV("src/ucupharma/farmacia_articles_small_PARTE2.csv");
        farmacia.altaStockCSV("src/ucupharma/farmacia_stock_small.csv");
 
        int opcion;
        Scanner input = new Scanner(System.in);
        
        do{
            opcion = menu();
            switch (opcion) {
            case 1:
                System.out.println("===================================================");
                System.out.println("|             Ingrese el ID del Producto          |");
                System.out.println("===================================================");
                int id1 = new Scanner(System.in).nextInt();
                IElementoAB<Producto> producto1 = farmacia.buscarPorId(id1);
                if (producto1 == null) {
                    System.out.println("No se encontro el producto con la id: " + id1);
                }else{
                    System.out.print("ID :");
                    System.out.println(producto1.getDatos().getId().toString()); 
                    System.out.print(" Precio UYU :");
                    System.out.println(producto1.getDatos().getPrecio());
                    System.out.print(" Refrigerado :");
                    System.out.println(producto1.getDatos().getRefrigerado());
                    System.out.print(" Requiere receta :");
                    System.out.println(producto1.getDatos().getRequiereReceta());
                    System.out.print(" Descripcion: ");
                    System.out.println(producto1.getDatos().getDescripcionCorta());
                    System.out.print(" Categoria: ");
                    System.out.println(producto1.getDatos().getCategoria());
                    System.out.print(" Stock: ");
                    System.out.println(producto1.getDatos().getStock());
                }
                break;
            case 2:
                System.out.println("===================================================");
                System.out.println("|          Ingrese una Descripcion Corta          |");
                System.out.println("===================================================");
                String descripcion_corta = new Scanner(System.in).nextLine();
        
                farmacia.buscarPorDescripcionCorta(descripcion_corta);
                break;
            case 3:
                System.out.println("===================================================");
                System.out.println("|      Ingrese una Descripcion  Larga             |");
                System.out.println("===================================================");
                String descripcion_larga = new Scanner(System.in).nextLine();
 
                farmacia.buscarPorDescripcionLarga(descripcion_larga);
                break;
            case 4:
                System.out.println("===================================================");
                System.out.println("|          Ingrese el ID del Producto             |");
                System.out.println("===================================================");
                int id4 = new Scanner(System.in).nextInt();
                if (id4 != 0) {
                    System.out.println("===================================================");
                    System.out.println("|          Ingrese una Cantidad                   |");
                    System.out.println("===================================================");
                    int cantidad4 = new Scanner(System.in).nextInt();
                    if (cantidad4 != 0) {
                        farmacia.ventaProducto(id4, cantidad4);
                    }
                }
             
               
                break;
            case 5:
                System.out.println("===================================================");
                System.out.println("|          Ingrese el ID del Producto             |");
                System.out.println("===================================================");
                int id5 = new Scanner(System.in).nextInt();
                if (id5 != 0) {
                    System.out.println("===================================================");
                    System.out.println("|          Ingrese una Cantidad                   |");
                    System.out.println("===================================================");
                    int cantidad5 = new Scanner(System.in).nextInt();
                    if (cantidad5 != 0) {
                        farmacia.reintegroStock(id5, cantidad5);
                    }
                }
   
                break;
            case 6:
                System.out.println("===================================================");
                System.out.println("|          Ingrese el ID del Producto             |");
                System.out.println("===================================================");
                IElementoAB<Producto> producto6;
                int id6;
                do{
                    id6 = new Scanner(System.in).nextInt();
                    producto6 = farmacia.buscarPorId(id6);
                    if (producto6 != null){
                        System.out.println("===================================================");
                        System.out.println("|          Esta id ya existe, ingrese otra         |");
                        System.out.println("===================================================");
                    }
                }while (producto6 != null);

                System.out.println("===================================================");
                System.out.println("|          Ingrese el precio                      |");
                System.out.println("===================================================");
                int precop6 = new Scanner(System.in).nextInt();
                System.out.println("===================================================");
                System.out.println("|          Ingrese su descripcion corta           |");
                System.out.println("===================================================");
                String descripcion_cortap6 = new Scanner(System.in).nextLine();
                System.out.println("===================================================");
                System.out.println("|          Ingrese su descripcion larga           |");
                System.out.println("===================================================");
                String descripcion_larga6 = new Scanner(System.in).nextLine();
                System.out.println("===================================================");
                System.out.println("|          Estado: Activo Inactivo o NA           |");
                System.out.println("===================================================");
                String estado;
                    
                boolean bandera_estado = false;
                do{
                    estado = new Scanner(System.in).nextLine();

                    if (estado.equals("Activo")  || estado.equals("Inactivo") || estado.equals("NA")) {

                        if (estado.equals("NA")) {
                            estado = null;
                        } 
                        bandera_estado = false;
                    } else {
                        bandera_estado = true;
                    }

                    if (bandera_estado){
                        System.out.println("===================================================");
                        System.out.println("|          Ingrese una de las tres opciones       |");
                        System.out.println("===================================================");
                    }
                }while (bandera_estado);
  
                System.out.println("===================================================");
                System.out.println("|          Refrigerado: S o N                     |");
                System.out.println("===================================================");
                String refrigerado6;
                boolean bandera_refrigerado = false;
                boolean refrigerado = true;

                do{
                    refrigerado6 = new Scanner(System.in).nextLine();

                    if (refrigerado6.equals("S")  || refrigerado6.equals("N")) {

                        if (refrigerado6.equals("S")) {
                            refrigerado = true;
                        } else {
                            refrigerado = false;
                        }
                        
                        bandera_refrigerado = false;
                    } else {
                        bandera_refrigerado = true;
                    }

                    if (bandera_refrigerado){
                        System.out.println("===================================================");
                        System.out.println("|          Ingrese una de las dos opciones       |");
                        System.out.println("===================================================");
                    }
                }while (bandera_refrigerado);    
 
                System.out.println("===================================================");
                System.out.println("|          Requeiere receta: S o N                |");
                System.out.println("===================================================");

                String requiere_receta6;
                boolean reseta = true;
                boolean bandera_requiere_receta = false;
                

                do{
                    requiere_receta6 = new Scanner(System.in).nextLine();

                    if (requiere_receta6.equals("S")  || requiere_receta6.equals("N")) {

                        if (requiere_receta6.equals("S")) {
                            reseta = true;
                        } else {
                            reseta = false;
                        }
                        
                        bandera_requiere_receta = false;
                    } else {
                        bandera_requiere_receta = true;
                    }

                    if (bandera_requiere_receta){
                        System.out.println("===================================================");
                        System.out.println("|          Ingrese una de las dos opciones       |");
                        System.out.println("===================================================");
                    }
                }while (bandera_requiere_receta);  
                
                System.out.println("===================================================");
                System.out.println("|                Ingrese categoria                |");
                System.out.println("===================================================");
                String categoria = new Scanner(System.in).nextLine();
                
                System.out.println("===================================================");
                System.out.println("|            Ingrese el vencimiento               |");
                System.out.println("===================================================");
                int vencimiento = new Scanner(System.in).nextInt();
                
                System.out.println("===================================================");
                System.out.println("|               Ingrese el stock                  |");
                System.out.println("===================================================");
                int stock = new Scanner(System.in).nextInt();
                    

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                String fecha_creacion6 = formatter.format(new Date());
                String fecha_actualizacion6 = formatter.format(new Date());
                farmacia.altaProducto(id6, fecha_creacion6, fecha_actualizacion6,precop6, descripcion_cortap6, descripcion_larga6, estado, refrigerado, reseta, vencimiento, categoria, stock);  

                    
          
                break;
            case 7:
                System.out.println("===================================================");
                System.out.println("|             Ingrese el ID del Producto          |");
                System.out.println("===================================================");
                int id_producto7 = new Scanner(System.in).nextInt();
                
                int stock7 = farmacia.buscarPorIdStock(id_producto7);
            
                if (stock7 == 0) {
                    System.out.println("No se encontro stock para el producto: " + id_producto7);
                }else{
                    System.out.print("ID :");
                    System.out.println(id_producto7); 
                    System.out.print("Stock :");
                    System.out.println(stock7);
                }
                
                
                break;
            case 8:
                farmacia.actualizacionStockCSV("src/ucupharma/farmacia_stock_big.csv");
                break;
            case 9:
                System.out.println("===================================================");
                System.out.println("|          Ingrese un Rango de Fechas             |");
                System.out.println("===================================================");
                System.out.println("===================================================");
                System.out.println("|          Fecha desde YYYY/MM/DD                 |");
                System.out.println("===================================================");
                String fecha_desde = new Scanner(System.in).nextLine();
                System.out.println("===================================================");
                System.out.println("|          Fecha hasta YYYY/MM/DD                 |");
                System.out.println("===================================================");
                String fecha_hasta = new Scanner(System.in).nextLine();
      
       
                farmacia.reporteVentas(fecha_desde, fecha_hasta);
                break;
            case 10:
                System.out.println("===================================================");
                System.out.println("| Ingrese ID del producto que desea eliminar      |");
                System.out.println("===================================================");
                int id_producto10 = new Scanner(System.in).nextInt();
                farmacia.bajaProducto(id_producto10);
                break;
            case 11:
                System.out.println("===================================================");
                System.out.println("|       Listado de productos por categoria        |");
                System.out.println("===================================================");
                farmacia.listarProductosPorArea();
                break;
            case 12:
                System.out.println("===================================================");
                System.out.println("|   Ingrese ID del producto para ver sus ventas   |");
                System.out.println("===================================================");
                int id_producto12 = new Scanner(System.in).nextInt();
                farmacia.listarVentasPorProducto(id_producto12);
                break;
            case 13:
                System.out.println("===================================================");
                System.out.println("|           Ingrese Año de vencimiento            |");
                System.out.println("===================================================");
                int vencimiento13 = new Scanner(System.in).nextInt();
                farmacia.listarProductoPorVencimientoYStock(vencimiento13);
                break;
            case 14:
                System.out.println("===================================================");
                System.out.println("|  Ingrese la categoria que desea eliminar        |");
                System.out.println("===================================================");
                String categoria14 = new Scanner(System.in).nextLine();
                farmacia.eliminarCategoria(categoria14);
                break;
            case 15:
                System.out.println("===================================================");
                System.out.println("|  Ingrese la categoria que desea eliminar        |");
                System.out.println("===================================================");
                //String categoria15 = new Scanner(System.in).nextLine();
                //farmacia.eliminarCategoria(categoria15);
                break;
            case 16:
                System.out.println("===================================================");
                System.out.println("|  Ingrese la categoria que desea eliminar        |");
                System.out.println("===================================================");
                String categoria16 = new Scanner(System.in).nextLine();
                farmacia.eliminarCategoria(categoria16);
                break;  
            case 17:
                System.exit(0);
                break;
            
            }
        } while(opcion != 16);
        
    }
    
    public static int menu() {
        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("=========================================================");
        System.out.println("|                     MENU A UCUPHARMA                  |");
        System.out.println("=========================================================");
        System.out.println("| Opciones:                                             |");
        System.out.println("|                                                       |");
        System.out.println("|      1. Buscar producto por ID                        |");
        System.out.println("|      2. Buscar producto por DESCRIPCION CORTA         |");
        System.out.println("|      3. Buscar producto por DESCRIPCION LARGA         |");
        System.out.println("|      4. Realizar una VENTA                            |");
        System.out.println("|      5. Realizar una DEVOLUCION                       |");
        System.out.println("|      6. Dar de ALTA un PRODUCTO                       |");
        System.out.println("|      7. Buscar STOCK                                  |");
        System.out.println("|      8. Actualizar STOCK                              |");
        System.out.println("|      9. LISTADO DE VENTAS por RANGO de FECHAS         |");
        System.out.println("|     10. ELIMINAR producto                             |");
        System.out.println("|     11. Listado de productos por CATEGORIA            |");
        System.out.println("|     12. Listado de VENTAS por ID de PRODUCTO          |");
        System.out.println("|     13. Listado de productos por VENCIMIENTO y STOCK  |");
        System.out.println("|     14. ELIMINAR CATEGORIA                            |");
        System.out.println("|     15. ******************                            |");
        System.out.println("|     16. ******************                            |");
        System.out.println("|     17. Salir                                         |");
        System.out.println("|                                                       |");
        System.out.println("=========================================================");

        selection = input.nextInt();
        return selection;  
    }
    
}
