/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.Combo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author deidine
 */


public class ComboListener implements ActionListener {
    JComboBox combo;

    Object currentItem;

public    ComboListener(JComboBox combo) {
      this.combo = combo;
      combo.setSelectedIndex(0);
      currentItem = combo.getSelectedItem();
    }

    public void actionPerformed(ActionEvent e) {
      Object tempItem = combo.getSelectedItem();
      if (!( ( ComboItem)tempItem).isEnabled()) {
        combo.setSelectedItem(currentItem);
      } else {
        currentItem = tempItem;
      }
    }
    
}