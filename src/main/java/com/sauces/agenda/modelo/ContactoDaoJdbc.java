/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenda.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw1
 */
public class ContactoDaoJdbc implements ContactoDao{
    private ConexionBD conexion;

    public ContactoDaoJdbc(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public int insertar(Contacto contacto) throws DaoException {
        int n = 0;
        String sentencia = "insert into contacto values(?,?,?)";
        try (Connection con = conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sentencia);) {
            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getTelefono());
            ps.setString(3, contacto.getEmail());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return n;
    }

    @Override
    public Contacto buscar(String nombre) throws DaoException {
        Contacto c=null;
        String sentencia= "select * from contacto where nombre=?";
        try(Connection con=conexion.getConnection();
            PreparedStatement ps=con.prepareStatement(sentencia);){
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Contacto(rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return c;
    }

    @Override
    public int borrar(String nombre) throws DaoException {
        int n=0;
        String sentencia="delete from contacto where nombre=?";
        try(Connection con=conexion.getConnection();
            PreparedStatement ps=con.prepareStatement(sentencia);){
            ps.setString(1, nombre);
            n=ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return n;
    }

    @Override
    public int modificar(Contacto contacto) throws DaoException {
int n=0; 
        String sentencia="update contacto set telefono =?,email =? where nombre=?;";
        try(Connection con=conexion.getConnection();
            PreparedStatement ps=con.prepareStatement(sentencia);){
            ps.setString(2,contacto.getEmail());
            ps.setString(1,contacto.getTelefono());
            ps.setString(3,contacto.getNombre());
            n=ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return n;    }

    @Override
    public List<Contacto> listar() throws DaoException {
        List<Contacto> listado = null;
        String sentencia = "select * from contacto ";
        try (Connection con = conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sentencia);
                ResultSet rs = ps.executeQuery();) {
            listado = new ArrayList<>();
            while (rs.next()) {
                listado.add(new Contacto(rs.getString("nombre"), rs.getString("telefono"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return listado;
    }

    @Override
    public List<Contacto> listar(int pos,int limite) throws DaoException {
        List<Contacto> listado = null;
        String sentencia = "select * from contacto limit ?,? ";
        try (Connection con = conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sentencia);){
                 ps.setInt(1, pos);
                 ps.setInt(2,limite);
                ResultSet rs = ps.executeQuery();
            listado = new ArrayList<>();
            while (rs.next()) {
                listado.add(new Contacto(rs.getString("nombre"), rs.getString("telefono"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return listado;
    }
    
}
