/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.Combo;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author deidine
 */
public  class ComboRenderer extends JLabel implements ListCellRenderer {

    public ComboRenderer() {
      setOpaque(true);
      setBorder(new EmptyBorder(1, 1, 1, 1));
    }

    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {
      if (isSelected) {
        setBackground(list.getSelectionBackground());
        setForeground(list.getSelectionForeground());
      } else {
        setBackground(list.getBackground());
        setForeground(list.getForeground());
      }
      if (!((ComboItem) value).isEnabled()) {
        setBackground(list.getBackground());
        setForeground(UIManager.getColor("Label.disabledForeground"));
      }
      setFont(list.getFont());
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }
