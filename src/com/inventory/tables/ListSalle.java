/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.tables ;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andriawan
 */
public class  ListSalle extends DefaultTableModel {

    public ListSalle() {
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 22; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "code client";
            case 1:
                return "code produit";
            case 2:
                return "prix vende";
            case 3:
                return "Quentite";
            case 4:
                return "NomProd";
            default:
                return null;
        }
    }
}
