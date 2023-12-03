/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.Salles.Utils;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deidine
 */
public class CalculTotal {
    //this function calcule the sum of the item that bought by the client 
    
    public static void totalValue(DefaultTableModel listSalles, JCheckBox TVA, JTextField txtTotal, JTable salesTable) {

        Double totalPaye = 0.0;
        for (int i = 0; i < listSalles.getRowCount(); i++) {
            Double sellPrice = Double.valueOf(listSalles.getValueAt(i, 2).toString());
//            Double totalRevenue = sellPrice;

            Double totalRevenue = sellPrice * Double.valueOf(listSalles.getValueAt(i, 3).toString());
            totalPaye = totalPaye + totalRevenue;

        }
        if (TVA.isSelected()) {
            totalPaye = (totalPaye * 1.6);
            txtTotal.setText(totalPaye.toString());
        } else {
            txtTotal.setText(totalPaye.toString());
        }

//        si le table est vide metre le champ 0
        if (salesTable.getRowCount() == 0) {
            txtTotal.setText("0");
        }
    }

    public static void totalNoTvaValue(DefaultTableModel listSalles, JTextField txtTotal, JTable salesTable) {

        Double totalPaye = 0.0;
        for (int i = 0; i < listSalles.getRowCount(); i++) {
            Double sellPrice = Double.valueOf(listSalles.getValueAt(i, 2).toString());
//            Double totalRevenue = sellPrice;

            Double totalRevenue = sellPrice * Double.valueOf(listSalles.getValueAt(i, 3).toString());
            totalPaye = totalPaye + totalRevenue;

        }
        txtTotal.setText(totalPaye.toString());

//        si le table est vide metre le champ 0
        if (salesTable.getRowCount() == 0) {
            txtTotal.setText("0");
        }
    }

}
