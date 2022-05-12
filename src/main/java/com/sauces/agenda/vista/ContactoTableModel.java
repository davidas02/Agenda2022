/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenda.vista;

import com.sauces.agenda.modelo.Contacto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author daw1
 */
public class ContactoTableModel extends AbstractTableModel{
    private List<Contacto> contactos;
    private String[] columnas;

    public ContactoTableModel() {
        contactos=new ArrayList<>();
        columnas=new String[]{"NOMBRE","TELEFONO","EMAIL"};
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    @Override
    public int getRowCount() {
        return contactos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contacto c=contactos.get(rowIndex);
        Object o=null;
        switch(columnIndex){
            case 0:
                o=c.getNombre();
                break;
            case 1:
                o=c.getTelefono();
                break;
            case 2:
                o=c.getEmail();
                break;
        }
        return o;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
