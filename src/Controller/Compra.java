/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class Compra {
    private Model.Compra oCompra;

    public Compra() {
        oCompra = new Model.Compra();
    }

    public Compra(Model.Compra oCompra) {
        this.oCompra = oCompra;
    }
    
    public boolean insert() throws Exception {
        return oCompra.insert();
    }
    
    public LinkedList<Model.Compra> select(String sql) {
        return oCompra.select(sql);
    }
}
