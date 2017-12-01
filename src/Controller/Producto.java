/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class Producto {
    private Model.Producto oProducto;

    public Producto() {
        oProducto = new Model.Producto();
    }

    public Producto(Model.Producto oProducto) {
        this.oProducto = oProducto;
    }
    
    public LinkedList<Model.Producto> select(String sql) {
        return oProducto.select(sql);
    }
    
    public void insert() {
        try {
            oProducto.insert();
        } catch (Exception ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
