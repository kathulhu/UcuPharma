/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucupharma;
import java.util.Date;

/**
 *
 * @author kathe
 */
public class Producto {
         
    private int id;
    /*Fecha formato YYYY/MM/DD hh:mm:ss*/
    private String fecha_creacion;
    /*Fecha formato YYYY/MM/DD hh:mm:ss*/
    private String ultima_actualizacion;
    private int precio;
    private String descripcion_corta;
    private String descripcion_larga;
    private String estado;
    private boolean refrigerado;
    private boolean requiere_receta;
    private int vencimiento;
    private String categoria;
    private int stock;
        
    public Comparable getId(){
        return this.id;
    }
    
    public String getFechaCreacion(){
        return this.fecha_creacion;
    }
    
    public String getUltimaActualizacion(){
        return this.ultima_actualizacion;
    }

    public int getPrecio(){
        return this.precio;
    }
    
    public String getDescripcionCorta(){
        return this.descripcion_corta;
    }
    
    public String getDescripcionLarga(){
        return this.descripcion_larga;
    } 
    
    public String getEstado(){
        return this.estado;
    }
    
    public boolean getRefrigerado(){
        return this.refrigerado;
    }
    
    public boolean getRequiereReceta(){
        return this.requiere_receta;
    }
    
    public int getVencimiento(){
        return this.vencimiento;
    }
    
    public String getCategoria(){
        return this.categoria;
    }
    
    public int getStock(){
        return this.stock;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setFechaCreacion(String fecha_creacion){
        //if (fecha_creacion == ""
        this.fecha_creacion = fecha_creacion.replace("-", "/");
    }
    
    public void setUltimaActualizacion(String ultima_actualizacion){
        this.ultima_actualizacion = ultima_actualizacion.replace("-", "/");
    }
    
    public void setPrecio(int precio){
        this.precio = precio;
    }
    
    public void setDescripcionCorta(String descripcion_corta){
        this.descripcion_corta = descripcion_corta;
    }
    
    public void setDescripcionLarga(String descripcion_larga){
        this.descripcion_larga = descripcion_larga;
    } 
    
    /* Inactivo - Activo - Null */
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public void setRefrigerado(boolean refrigerado){
        this.refrigerado = refrigerado;
    }
    
    public void setRequiereReceta(boolean requiere_receta){
        this.requiere_receta = requiere_receta;
    }
    
    public void setVencimiento(int vencimiento){
        this.vencimiento = vencimiento;
    }
    
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }
    
    /*
    * Contstructor de la clase Producto
    * @param id
    * @param fecha_creacion
    * @param ultima_actualizacion
    * @param precio
    * @param descripcion_corta
    * @param descripcion_larga
    * @param estado
    * @param refrigerado
    * @param requiere_receta
    */
    public Producto(int id, 
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
        this.id = id;
        this.fecha_creacion = fecha_creacion;
        this.ultima_actualizacion = ultima_actualizacion;
        this.precio = precio;
        this.descripcion_corta = descripcion_corta;
        this.descripcion_larga = descripcion_larga;
        this.estado = estado;
        this.refrigerado = refrigerado;
        this.requiere_receta = requiere_receta;
        this.vencimiento = vencimiento;
        this.categoria = categoria;
        this.stock = stock;
    }
}
