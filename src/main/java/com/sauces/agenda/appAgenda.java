package com.sauces.agenda;

import com.sauces.agenda.controlador.ControladorAgenda;
import com.sauces.agenda.modelo.ConexionBD;
import com.sauces.agenda.modelo.ContactoDao;
import com.sauces.agenda.modelo.ContactoDaoJdbc;
import com.sauces.agenda.vista.VentanaAgenda;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daw1
 */
public class appAgenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String url, usuario, password;
        ControladorAgenda controlador;
        ContactoDao modelo;
        VentanaAgenda vista;
        Properties propiedades;

        vista = new VentanaAgenda();
        propiedades = new Properties();

        try (InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("conexion.properties");) {
            propiedades.load(is);
            url = propiedades.getProperty("url");
            usuario = propiedades.getProperty("usuario");
            password = propiedades.getProperty("password");

            ConexionBD conexion = new ConexionBD(url, usuario, password);
            modelo = new ContactoDaoJdbc(conexion);

            controlador = new ControladorAgenda(vista, modelo);
            vista.setControlador(controlador);
            controlador.iniciar();
        } catch (IOException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }
}
