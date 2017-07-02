/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucupharma;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author kathe
 */

public class Stock {
    private static final AtomicInteger count = new AtomicInteger(0);     
    
    private final int id_stock;
    private int id_producto;
    private int monto;
    
    public int getIdStock(){
        return this.id_stock;
    }
    
    public Comparable getIdProducto(){
        return this.id_producto;
    }
    
    public int getMonto(){
        return this.monto;
    }
    
    public void setMonto(int monto){
        this.monto = monto;
    }
      
    public Stock(int id_producto, int monto){
        this.id_stock = count.incrementAndGet(); 
        this.id_producto = id_producto;
        this.monto = monto;
    }
    
}
