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
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class Compra {
    private int id;
    private String nombre_usuario;
    private int edad;
    private String sexo;
    private int id_product;
    private Date fecha;

    public Compra() {
    }

    public LinkedList<Model.Compra> select(String sql) {
        LinkedList<Model.Compra> compras = new LinkedList<>();
        try (Connection con = ConnectionSql.conexion()) {
            if (sql.isEmpty()) {
                sql = "SELECT * FROM compras;";
            }
            PreparedStatement smt = con.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                compras.add(cargar(rs));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return compras;
    }

    private Model.Compra cargar(ResultSet rs) throws SQLException {
        Model.Compra c = new Model.Compra();
        c.setId(rs.getInt("id"));
        c.setNombre_usuario(rs.getString("nombre_usuario"));
        c.setEdad(rs.getInt("edad"));
        c.setFecha(new Date(rs.getDate("fecha").getTime()));
        c.setId_product(rs.getInt("id_producto"));
        c.setSexo(rs.getString("sexo"));
        return c;
    }

    public boolean insert() throws Exception {
        try (Connection con = ConnectionSql.conexion()) {
            String sql = "INSERT INTO compras(nombre_usuario, edad, fecha, id_producto, sexo) VALUES (?,?,?,?,?);";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, this.getNombre_usuario());
            smt.setInt(2, this.getEdad());
            smt.setDate(3, new java.sql.Date(this.getFecha().getTime()));
            smt.setInt(4, this.getId_product());
            smt.setString(5, this.getSexo());
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

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
