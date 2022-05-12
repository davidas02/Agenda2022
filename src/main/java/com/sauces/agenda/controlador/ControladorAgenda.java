/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenda.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.sauces.agenda.modelo.Contacto;
import com.sauces.agenda.modelo.ContactoDao;
import com.sauces.agenda.modelo.DaoException;
import com.sauces.agenda.vista.VentanaAgenda;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1
 */
public class ControladorAgenda {
    private VentanaAgenda ventana;
    private ContactoDao contactoDao;

    public ControladorAgenda(VentanaAgenda ventana, ContactoDao contactoDao) {
        this.ventana = ventana;
        this.contactoDao = contactoDao;
    }
    public void crear(){
        try {
            contactoDao.insertar(new Contacto(ventana.getNombre(),ventana.getTelefono(),ventana.getEmail()));
        } catch (DaoException ex) {
            ventana.mostrarMensaje(ex.getMessage());
        }
    }
    public void editar(){
        
        try {
            contactoDao.modificar(new Contacto(ventana.getNombre(),ventana.getTelefono(),ventana.getEmail()));
        } catch (DaoException ex) {
            ventana.mostrarMensaje(ex.getMessage());
        }
    }
    public void borrar(){
        String nombre =ventana.getNombre();
        try {
            if(contactoDao.borrar(nombre)>0){
                ventana.mostrarMensaje("El contacto "+nombre+" se ha borrado");
            }else{
                ventana.mostrarMensaje("El contacto "+nombre+" no se ha borrado");
            }
            ventana.limpiarCampos();
        } catch (DaoException ex) {
            ventana.mostrarMensaje(ex.getMessage());
        }
    }
    public void buscar(){
        try {
            Contacto c=contactoDao.buscar(ventana.getNombre());
            ventana.mostrarTelefono(c.getTelefono());
            ventana.mostrarEmail(c.getEmail());
        } catch (DaoException ex) {
            ventana.mostrarMensaje(ex.getMessage());
        }
    }
    public void listar(){
        List<Contacto> listado;
        try {
            listado = contactoDao.listar();
            ventana.mostrarContactos(listado);
        } catch (DaoException ex) {
            ventana.mostrarMensaje(ex.getMessage());
        }
        
    }
    public void exportar(){
    java.lang.reflect.Type tipo = new com.google.gson.reflect.TypeToken<List<Contacto>>() {
        }.getType();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(ventana.getArchivo()))) {
            gson.toJson(contactoDao.listar(), tipo, bw);
        } catch (IOException ex) {
            ventana.mostrarMensaje(ex.getMessage());
        } catch (DaoException ex) {
            ventana.mostrarMensaje(ex.getMessage());
        }
    }
    public void importar(){
    Type tipo = new TypeToken<List<Contacto>>() {
        }.getType();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        List<Contacto> listado;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ventana.getArchivo()))) {
            listado = gson.fromJson(br, tipo);
        }catch(JsonSyntaxException | JsonIOException jse){
            ventana.mostrarMensaje(jse.getMessage());
        }catch (IOException ex) {
            ventana.mostrarMensaje(ex.getMessage());
        }
    }
    public void iniciar(){
        ventana.mostrar();
    }
}
