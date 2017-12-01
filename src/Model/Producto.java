/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.ConnectionSql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private String tipo;

    public Producto() {
    }

    public LinkedList<Model.Producto> select(String sql) {
        LinkedList<Model.Producto> producto = new LinkedList<>();
        try (Connection con = ConnectionSql.conexion()) {
            if (sql.isEmpty()) {
                sql = "SELECT * FROM producto WHERE tipo = ?;";
            }
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, this.getTipo());
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                producto.add(cargar(rs));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return producto;
    }

    private Model.Producto cargar(ResultSet rs) throws SQLException {
        Model.Producto u = new Model.Producto();
        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setTipo(rs.getString("tipo"));
        u.setPrecio(rs.getDouble("precio"));
        return u;
    }

    public boolean insert() throws Exception {
        try (Connection con = ConnectionSql.conexion()) {
            String sql = "INSERT INTO producto(nombre, tipo, precio) VALUES (?,?,?);";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, this.getNombre());
            smt.setString(2, this.getTipo());
            smt.setDouble(3, this.getPrecio());
            //smt.setDate(4, new java.sql.Date(v.getFecha().getTime())); fecha normal a fecha sql
            return smt.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
