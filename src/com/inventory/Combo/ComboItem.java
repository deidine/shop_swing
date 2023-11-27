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
public class ComboItem { 
    Object obj;

    boolean isEnable;

  public  ComboItem(Object obj, boolean isEnable) {
      this.obj = obj;
      this.isEnable = isEnable;
    }

  public  ComboItem(Object obj) {
      this(obj, true);
    }

    public boolean isEnabled() {
      return isEnable;
    }

    public void setEnabled(boolean isEnable) {
      this.isEnable = isEnable;
    }

    public String toString() {
      return obj.toString();
    }
 

   

}