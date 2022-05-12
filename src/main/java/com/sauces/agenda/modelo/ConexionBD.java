/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenda.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author daw1
 */
public class ConexionBD {
    private String url;
    private String usuario;
    private String password;

    public ConexionBD(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }
    public Connection getConnection() throws DaoException{
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException sqle) {
            throw new DaoException("Error: " + sqle.getMessage());
        }
        return con;
    }
}
