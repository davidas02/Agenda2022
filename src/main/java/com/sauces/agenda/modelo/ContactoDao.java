/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenda.modelo;

import java.util.List;

/**
 *
 * @author daw1
 */
public interface ContactoDao {
    public int insertar(Contacto contacto) throws DaoException;
    public Contacto buscar(String nombre) throws DaoException;
    public int borrar(String nombre) throws DaoException;
    public int modificar(Contacto contacto) throws DaoException;
    public List<Contacto> listar() throws DaoException;
    public List<Contacto> listar(int pos,int limite) throws DaoException;
}
